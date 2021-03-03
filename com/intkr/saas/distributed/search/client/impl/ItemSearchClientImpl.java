package com.intkr.saas.distributed.search.client.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.BooleanFilter;
import org.apache.lucene.queries.TermFilter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.FieldValueFilter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeFilter;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.intkr.saas.distributed.search.client.ItemSearchClient;
import com.intkr.saas.distributed.search.lucene.LuceneBase;
import com.intkr.saas.distributed.search.lucene.LuceneUtil;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemCategoryBO;
import com.intkr.saas.domain.bo.item.ItemFcategoryBO;
import com.intkr.saas.domain.bo.item.ItemPropertyBO;
import com.intkr.saas.domain.bo.item.ItemPropertyValueBO;
import com.intkr.saas.domain.bo.item.ItemSpuBO;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateBO;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateValueBO;
import com.intkr.saas.domain.bo.item.ItemSpuValueBO;
import com.intkr.saas.manager.item.ItemCategoryManager;
import com.intkr.saas.manager.item.ItemFcategoryManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.item.ItemSpuManager;
import com.intkr.saas.manager.item.ItemSpuTemplateManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 */
@Repository("ItemSearchClient")
public class ItemSearchClientImpl extends LuceneBase implements ItemSearchClient {

	private static Boolean fullDumping = false;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private ItemManager itemManager;

	@Resource
	private ShopManager shopManager;

	@Resource
	private ItemSpuManager itemSpuManager;

	@Resource
	private ItemCategoryManager itemCategoryManager;

	@Resource
	private ItemFcategoryManager itemFcategoryManager;

	@Resource
	private ItemSpuTemplateManager itemSpuTemplateManager;

	public void fullDump(Long saasId) {
		if (fullDumping) {
			return;
		}
		fullDumping = true;
		try {
			String startTime = DateUtil.formatDateTime(new Date());
			ItemBO query = new ItemBO();
			query.setSaasId(saasId);
			query.set_pageSize(500);
			query.searchDb();
			long count = itemManager.count(query);
			query.set_page(1);
			itemManager.select(query);
			Integer dumpCount = 0;
			while (query.getDatas() != null && query.getDatas().size() > 0) {
				List<ItemBO> list = query.getDatas();
				for (ItemBO item : list) {
					itemCategoryManager.fill(item);
					itemSpuManager.fillFull(item);
				}
				dump(list);
				dumpCount += list.size();
				query.set_page(query.get_page() + 1);
				itemManager.select(query);
				logger.info("fullDump:page=" + query.get_page() + ";pageSize=" + query.get_pageSize());
			}
			logger.info("fullDump:dumpCount=" + dumpCount);

			query.searchLucene();
			query.set_page(1);
			query.set_pageSize(Integer.MAX_VALUE - 1);
			itemManager.selectAndCount(query);
			List<ItemBO> list = query.getDatas();
			query.searchDb();
			Integer deleteCount = 0;
			for (ItemBO item : list) {
				if (itemManager.get(item.getId()) == null) {
					delete(item.getId());
					deleteCount++;
				}
			}
			logger.info("fullDump:deleteCount=" + deleteCount);
			String endTime = DateUtil.formatDateTime(new Date());
			logger.error("fullDump-finished:startTime=" + startTime + ";endTime=" + endTime);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			fullDumping = false;
		}
	}

	public void dumpAllById(List<Long> itemIds) {
		dumpReal(itemIds);
	}

	class DumpRunner implements Runnable {

		List<Long> itemIds;

		public DumpRunner(List<Long> itemIds) {
			this.itemIds = itemIds;
		}

		public void run() {
			dumpReal(itemIds);
		}

	}

	private void dumpReal(List<Long> itemIds) {
		List<ItemBO> list = new ArrayList<ItemBO>();
		for (Long id : itemIds) {
			ItemBO item = itemManager.get(id);
			if (item == null) {
				continue;
			}
			itemCategoryManager.fill(item);
			itemSpuManager.fillFull(item);
			list.add(item);
		}
		dump(list);
	}

	public void dump(Long itemId) {
		List<Long> itemIds = new ArrayList<Long>();
		itemIds.add(itemId);
		dumpReal(itemIds);
	}

	public ItemBO getById(Long itemId) {
		if (itemId == null) {
			return null;
		}
		ItemBO query = new ItemBO();
		query.set_pageSize(1);
		query.setId(itemId);
		search(query);
		if (query.get_count() == 0) {
			return null;
		} else {
			List<ItemBO> list = query.getDatas();
			return list.get(0);
		}
	}

	private void addProperty(List<ItemPropertyBO> categoryPropertyList, ItemPropertyBO property) {
		boolean contain = false;
		for (ItemPropertyBO propertyTmp : categoryPropertyList) {
			if (propertyTmp.getId().equals(property.getId())) {
				contain = true;
				for (ItemPropertyValueBO value : property.getValues()) {
					propertyTmp.addValue(value);
				}
			}
		}
		if (!contain) {
			categoryPropertyList.add(property);
		}
	}

	public synchronized void dump(ItemBO item) {
		try {
			Document doc = toDoc(item);
			IndexWriter writer = getIndexWriter();
			writer.deleteDocuments(NumericRangeQuery.newLongRange("id", item.getId(), item.getId(), true, true));
			writer.addDocument(doc);
			writer.commit();
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

	public synchronized void dump(List<ItemBO> list) {
		try {
			IndexWriter writer = getIndexWriter();
			for (ItemBO item : list) {
				Document doc = toDoc(item);
				writer.deleteDocuments(NumericRangeQuery.newLongRange("id", item.getId(), item.getId(), true, true));
				writer.addDocument(doc);
			}
			writer.commit();
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

	public synchronized void deleteAll() {
		try {
			IndexWriter writer = getIndexWriter();
			writer.deleteAll();
			writer.commit();
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

	public synchronized void delete(Long itemId) {
		if (itemId == null) {
			return;
		}
		try {
			IndexWriter writer = getIndexWriter();
			writer.deleteDocuments(NumericRangeQuery.newLongRange("id", itemId, itemId, true, true));
			writer.commit();
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

	private Document toDoc(ItemBO item) {
		Document doc = new Document();
		{// 商品基础信息
			doc.add(new LongField("id", item.getId(), LongField.TYPE_STORED));
			doc.add(new LongField("saasId", item.getSaasId(), LongField.TYPE_STORED));
			doc.add(new LongField("shopId", item.getShopId(), LongField.TYPE_STORED));
			doc.add(new TextField("name", item.getName(), TextField.Store.YES));
			doc.add(new TextField("slogan", item.getSlogan() + "", TextField.Store.YES));
			if (item.getImgIds() != null) {
				String[] imgIds = item.getImgIds().split(";");
				for (String imgId : imgIds) {
					if (imgId == null || "".equals(imgId)) {
						continue;
					}
					doc.add(new LongField("imgIds", Long.valueOf(imgId), LongField.TYPE_STORED));
				}
			}
			if (item.getTagIds() != null) {
				String[] tagIds = item.getTagIds().split(";");
				for (String tagId : tagIds) {
					if (tagId == null || "".equals(tagId)) {
						continue;
					}
					doc.add(new LongField("tagIds", Long.valueOf(tagId), LongField.TYPE_STORED));
				}
			}
			doc.add(new LongField("price", item.getPrice(), LongField.TYPE_STORED));
			doc.add(new StringField("status", item.getStatus(), StringField.Store.YES));
			doc.add(new TextField("content", item.getContent(), TextField.Store.YES));
			doc.add(new IntField("skuType", item.getSkuType(), IntField.TYPE_STORED));
		}
		{// 后台类目信息
			ItemCategoryBO firstCategory = item.getFirstCategory();
			if (firstCategory != null) {
				doc.add(new LongField("firstCategoryId", firstCategory.getId(), LongField.TYPE_STORED));
				doc.add(new StringField("firstCategoryName", firstCategory.getName(), StringField.Store.YES));
			}
			ItemCategoryBO secondCategory = item.getSecondCategory();
			if (secondCategory != null) {
				doc.add(new LongField("secondCategoryId", secondCategory.getId(), LongField.TYPE_STORED));
				doc.add(new StringField("secondCategoryName", secondCategory.getName(), StringField.Store.YES));
			}
			ItemCategoryBO thirdCategory = item.getThirdCategory();
			if (thirdCategory != null) {
				doc.add(new LongField("thirdCategoryId", thirdCategory.getId(), LongField.TYPE_STORED));
				doc.add(new StringField("thirdCategoryName", thirdCategory.getName(), StringField.Store.YES));
			}
			{// SPU信息
				List<ItemSpuBO> spus = item.getSpus();
				if (spus != null) {
					for (ItemSpuBO spu : spus) {
						doc.add(new LongField("spuId", spu.getPropertyId(), LongField.TYPE_STORED));
						doc.add(new StringField("spuName", spu.getProperty().getName(), StringField.Store.YES));
						for (ItemSpuValueBO value : spu.getSpuValues()) {
							doc.add(new LongField("spuValueId", value.getValueId(), LongField.TYPE_STORED));
							doc.add(new StringField("spuValueName", value.getPropertyValue().getValue(), StringField.Store.YES));
						}
					}
				}
			}
		}
		return doc;
	}

	public ItemBO search(ItemBO itemQuery) {
		try {
			IndexReader reader = getIndexReader();
			IndexSearcher isearcher = new IndexSearcher(reader);
			int start = (itemQuery.get_page() - 1) * itemQuery.get_pageSize();
			int end = itemQuery.get_page() * itemQuery.get_pageSize();
			Filter filter = toQueryFilter(itemQuery);
			Query query = toQuery(itemQuery);
			Sort sort = toQuerySort(itemQuery);
			TopDocs topDocs = null;
			if (sort == null) {
				topDocs = isearcher.search(query, filter, end);
			} else {
				topDocs = isearcher.search(query, filter, end, sort);
			}
			itemQuery.set_count(topDocs.totalHits);
			ScoreDoc[] hits = topDocs.scoreDocs;
			itemQuery.setDatas(new ArrayList<ItemBO>());
			for (; start < hits.length && start < end; start++) {
				Document hitDoc = isearcher.doc(hits[start].doc);
				ItemBO item = toBO(hitDoc);
				itemQuery.getDatas().add(item);
			}
			return itemQuery;
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

	private ItemBO toBO(Document hitDoc) {
		ItemBO item = new ItemBO();
		item.setId(Long.valueOf(hitDoc.get("id")));
		item.setSaasId(Long.valueOf(hitDoc.get("saasId")));
		if (hitDoc.get("firstCategoryId") != null) {
			item.setFirstCategoryId(Long.valueOf(hitDoc.get("firstCategoryId")));
		}
		if (hitDoc.get("secondCategoryId") != null) {
			item.setSecondCategoryId(Long.valueOf(hitDoc.get("secondCategoryId")));
		}
		if (hitDoc.get("thirdCategoryId") != null) {
			item.setThirdCategoryId(Long.valueOf(hitDoc.get("thirdCategoryId")));
		}
		item.setShopId(Long.valueOf(hitDoc.get("shopId")));
		item.setPrice(Long.valueOf(hitDoc.get("price")));
		item.setName(hitDoc.get("name"));
		item.setSlogan(hitDoc.get("slogan"));
		item.setStatus(hitDoc.get("status"));
		item.setContent(hitDoc.get("content"));
		item.setSkuType(Integer.valueOf(hitDoc.get("skuType")));
		{
			String[] imgIdsArray = hitDoc.getValues("imgIds");
			String imgIds = "";
			if (imgIdsArray != null) {
				for (String imgId : imgIdsArray) {
					if ("".equals(imgIds)) {
						imgIds = imgId;
					} else {
						imgIds += ";" + imgId;
					}
				}
			}
			item.setImgIds(imgIds);
		}
		{
			String[] tagIdsArray = hitDoc.getValues("tagIds");
			String tagIds = "";
			if (tagIdsArray != null) {
				for (String imgId : tagIdsArray) {
					if ("".equals(tagIds)) {
						tagIds = imgId;
					} else {
						tagIds += ";" + imgId;
					}
				}
			}
			item.setTagIds(tagIds);
		}
		return item;
	}

	private Filter toQueryFilter(ItemBO itemQuery) {
		BooleanFilter booleanFilter = null;
		if (itemQuery.getSaasId() != null) {
			if (booleanFilter == null) {
				booleanFilter = new BooleanFilter();
			}
			NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("saasId", itemQuery.getSaasId(), itemQuery.getSaasId(), true, true);
			booleanFilter.add(filter, Occur.MUST);
		}
		if (itemQuery.getId() != null) {
			if (booleanFilter == null) {
				booleanFilter = new BooleanFilter();
			}
			NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("id", itemQuery.getId(), itemQuery.getId(), true, true);
			booleanFilter.add(filter, Occur.MUST);
		}
		if (itemQuery.getShopId() != null) {
			if (booleanFilter == null) {
				booleanFilter = new BooleanFilter();
			}
			NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("shopId", itemQuery.getShopId(), itemQuery.getShopId(), true, true);
			booleanFilter.add(filter, Occur.MUST);
		}
		if (itemQuery.getQuery("categoried") != null) {
			if (booleanFilter == null) {
				booleanFilter = new BooleanFilter();
			}
			Filter filter = null;
			if ("Yes".equalsIgnoreCase((String) itemQuery.getQuery("categoried"))) {
				filter = new FieldValueFilter("firstCategoryId", false);
			} else {
				filter = new FieldValueFilter("firstCategoryId", true);
			}
			booleanFilter.add(filter, Occur.MUST);
		}
		if (itemQuery.getFirstCategoryId() != null && itemQuery.getFirstCategoryId() != -1l) {
			if (booleanFilter == null) {
				booleanFilter = new BooleanFilter();
			}
			Long firstCategoryId = itemQuery.getFirstCategoryId();
			NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("firstCategoryId", firstCategoryId, firstCategoryId, true, true);
			booleanFilter.add(filter, Occur.MUST);
		}
		if (itemQuery.getSecondCategoryId() != null && itemQuery.getSecondCategoryId() != -1l) {
			if (booleanFilter == null) {
				booleanFilter = new BooleanFilter();
			}
			Long secondCategoryId = itemQuery.getSecondCategoryId();
			NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("secondCategoryId", secondCategoryId, secondCategoryId, true, true);
			booleanFilter.add(filter, Occur.MUST);
		}
		if (itemQuery.getThirdCategoryId() != null && itemQuery.getThirdCategoryId() != -1l) {
			if (booleanFilter == null) {
				booleanFilter = new BooleanFilter();
			}
			Long thirdCategoryId = itemQuery.getThirdCategoryId();
			NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("thirdCategoryId", thirdCategoryId, thirdCategoryId, true, true);
			booleanFilter.add(filter, Occur.MUST);
		}
		if (itemQuery.getStatus() != null) {
			if (booleanFilter == null) {
				booleanFilter = new BooleanFilter();
			}
			Filter filter = new TermFilter(new Term("status", itemQuery.getStatus()));
			booleanFilter.add(filter, Occur.MUST);
		}
		if (itemQuery.getQuery("categoryId") != null) {
			if (booleanFilter == null) {
				booleanFilter = new BooleanFilter();
			}
			Long categoryId = Long.valueOf(itemQuery.getQuery("categoryId").toString());
			BooleanFilter categoryFilter = new BooleanFilter();
			{
				NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("firstCategoryId", categoryId, categoryId, true, true);
				categoryFilter.add(filter, Occur.SHOULD);
			}
			{
				NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("secondCategoryId", categoryId, categoryId, true, true);
				categoryFilter.add(filter, Occur.SHOULD);
			}
			{
				NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("thirdCategoryId", categoryId, categoryId, true, true);
				categoryFilter.add(filter, Occur.SHOULD);
			}
			booleanFilter.add(categoryFilter, Occur.MUST);
		}
		if (itemQuery.getQuery("tagId") != null) {
			Long tagId = (Long) itemQuery.getQuery("tagId");
			if (booleanFilter == null) {
				booleanFilter = new BooleanFilter();
			}
			NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("tagIds", tagId, tagId, true, true);
			booleanFilter.add(filter, Occur.MUST);
		}
		if (itemQuery.getQuery("fcategoryId") != null) {
			if (booleanFilter == null) {
				booleanFilter = new BooleanFilter();
			}
			ItemFcategoryManager itemFcategoryManager = IOC.get(ItemFcategoryManager.class);
			ItemFcategoryBO itemFcategory = itemFcategoryManager.get(itemQuery.getQuery("fcategoryId"));
			if (itemQuery.getQuery("searchWord") == null) {
				itemQuery.setQuery("searchWord", itemFcategory.getName());
			}
			itemFcategoryManager.fillParent(itemFcategory);
			itemFcategoryManager.fillChild(itemFcategory);
			itemFcategoryManager.fillSbiling(itemFcategory);
			itemQuery.setQuery("itemFcategory", itemFcategory);
			ItemCategoryManager itemCategoryManager = IOC.get(ItemCategoryManager.class);
			itemCategoryManager.fill(itemFcategory);
			BooleanFilter categoryFilter = new BooleanFilter();
			if (itemFcategory.getIbcs() != null) {
				List<ItemPropertyBO> categoryPropertyList = new ArrayList<ItemPropertyBO>();
				for (ItemCategoryBO ibc : itemFcategory.getIbcs()) {
					if (ibc.getLevel() == 1) {
						NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("firstCategoryId", ibc.getId(), ibc.getId(), true, true);
						categoryFilter.add(filter, Occur.SHOULD);
					} else if (ibc.getLevel() == 2) {
						NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("secondCategoryId", ibc.getId(), ibc.getId(), true, true);
						categoryFilter.add(filter, Occur.SHOULD);
					} else if (ibc.getLevel() == 3) {
						NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("thirdCategoryId", ibc.getId(), ibc.getId(), true, true);
						categoryFilter.add(filter, Occur.SHOULD);
					}
					// SPU汇总
					ItemSpuTemplateManager shopSpuTemplateManager = IOC.get(ItemSpuTemplateManager.class);
					shopSpuTemplateManager.fillFull(ibc);
					for (ItemSpuTemplateBO bo : ibc.getSpuTemplateList()) {
						ItemPropertyBO property = bo.getProperty();
						List<ItemSpuTemplateValueBO> values = bo.getSpuTemplateValueList();
						for (ItemSpuTemplateValueBO value : values) {
							// property.addValue(value.getPropertyValue());
						}
						// addProperty(categoryPropertyList, property);
					}
				}
				itemQuery.setQuery("categoryPropertyList", categoryPropertyList);
				itemQuery.setQuery("itemCategory", itemFcategory.getIbcIds());
			}
			booleanFilter.add(categoryFilter, Occur.MUST);
		}
		return booleanFilter;
	}

	private Sort toQuerySort(ItemBO itemQuery) {
		Sort sort = null;
		if (itemQuery.getQuery("orderBy") != null && itemQuery.getQuery("order") != null) {
			String orderBy = (String) itemQuery.getQuery("orderBy");
			String order = (String) itemQuery.getQuery("order");
			sort = new Sort();
			SortField.Type type = SortField.Type.STRING;
			if ("id".equalsIgnoreCase(orderBy)) {
				type = SortField.Type.LONG;
			} else if ("price".equalsIgnoreCase(orderBy)) {
				type = SortField.Type.LONG;
			}
			if (itemQuery.getQuery("searchWord") != null) {
				sort = Sort.RELEVANCE;
			} else {
				SortField sortField = new SortField(orderBy, type, "DESC".equalsIgnoreCase(order));
				sort.setSort(sortField);
			}
		}
		return sort;
	}

	private Query toQuery(ItemBO itemQuery) throws ParseException {
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser parser = new QueryParser("name", analyzer);
		String queryString = "";
		if (itemQuery.getQuery("searchWord") != null) {
			queryString += "name:" + LuceneUtil.filterSpe(itemQuery.getQuery("searchWord").toString()) + " AND ";
		}
		if (queryString.length() >= 4) {
			queryString = queryString.substring(0, queryString.length() - 4);
		} else {
			queryString = "*:*";
		}
		Query query = parser.parse(queryString);
		return query;
	}

	public String getDataDirectory() {
		return "item_luence_index";
	}

}
