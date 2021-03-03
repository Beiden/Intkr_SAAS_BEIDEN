package com.intkr.saas.distributed.search.client.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queries.BooleanFilter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
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

import com.intkr.saas.distributed.search.client.ImgSearchClient;
import com.intkr.saas.distributed.search.lucene.LuceneBase;
import com.intkr.saas.domain.bo.fs.ImgBO;
import com.intkr.saas.engine.thread.ThreadPoolEngine;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.util.DateUtil;

/**
 * 
 * @author Beiden
 */
@Repository("ImgSearchClient")
public class ImgSearchClientImpl extends LuceneBase implements ImgSearchClient {

	private static Boolean fullDumping = false;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private ImgManager imgManager;

	public void fullDump(Long saasId) {
		if (fullDumping) {
			return;
		}
		fullDumping = true;
		try {
			String startTime = DateUtil.formatDateTime(new Date());
			ImgBO query = new ImgBO();
			query.setSaasId(saasId);
			query.searchDb();
			query.set_page(1);
			query.set_pageSize(5000);
			imgManager.select(query);
			Integer dumpCount = 0;
			while (query.getDatas() != null && query.getDatas().size() > 0) {
				List<ImgBO> list = query.getDatas();
				dump(list);
				dumpCount += list.size();
				query.set_page(query.get_page() + 1);
				imgManager.select(query);
				logger.info("fullDump:page=" + query.get_page() + ";pageSize=" + query.get_pageSize());
			}
			logger.info("fullDump:dumpCount=" + dumpCount);

			query.searchLucene();
			query.set_page(1);
			query.set_pageSize(Integer.MAX_VALUE - 1);
			imgManager.selectAndCount(query);
			List<ImgBO> list = query.getDatas();
			query.searchDb();
			Integer deleteCount = 0;
			for (ImgBO img : list) {
				if (imgManager.get(img.getId()) == null) {
					delete(img.getId());
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

	public void dump(Long imgId) {
		ImgBO img = imgManager.get(imgId);
		dump(img);
	}

	public void dump(ImgBO img) {
		ThreadPoolEngine.getExecutor().execute(new DumpRunner(img));
	}

	private synchronized void dumpReal(ImgBO img) {
		Document doc = toDoc(img);
		try {
			IndexWriter writer = getIndexWriter();
			writer.deleteDocuments(NumericRangeQuery.newLongRange("id", img.getId(), img.getId(), true, true));
			writer.addDocument(doc);
			writer.commit();
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

	public ImgBO search(ImgBO imgQuery) {
		try {
			IndexSearcher isearcher = new IndexSearcher(getIndexReader());
			Filter filter = toQueryFilter(imgQuery);
			Query query = toQuery(imgQuery);
			int start = (imgQuery.get_page() - 1) * imgQuery.get_pageSize();
			int end = imgQuery.get_page() * imgQuery.get_pageSize();
			Sort sort = toQuerySort(imgQuery);
			TopDocs topDocs = null;
			if (sort == null) {
				topDocs = isearcher.search(query, filter, end);
			} else {
				topDocs = isearcher.search(query, filter, end, sort);
			}
			imgQuery.set_count(topDocs.totalHits);
			ScoreDoc[] hits = topDocs.scoreDocs;
			imgQuery.setDatas(new ArrayList<ImgBO>());
			for (; start < hits.length && start < end; start++) {
				Document hitDoc = isearcher.doc(hits[start].doc);
				ImgBO item = toBO(hitDoc);
				imgQuery.getDatas().add(item);
			}
			return imgQuery;
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

	private ImgBO toBO(Document hitDoc) {
		ImgBO item = new ImgBO();
		item.setId(Long.valueOf(hitDoc.get("id")));
		if (hitDoc.get("categoryId") != null) {
			item.setCategoryId(Long.valueOf(hitDoc.get("categoryId")));
		}
		if (hitDoc.get("shopId") != null) {
			item.setShopId(Long.valueOf(hitDoc.get("shopId")));
		}
		if (hitDoc.get("userId") != null) {
			item.setUserId(Long.valueOf(hitDoc.get("userId")));
		}
		if (hitDoc.get("saasId") != null) {
			item.setSaasId(Long.valueOf(hitDoc.get("saasId")));
		}
		item.setName(hitDoc.get("name"));
		item.setRealname(hitDoc.get("realname"));
		item.setUri(hitDoc.get("uri"));
		item.setNote(hitDoc.get("note"));
		return item;
	}

	private Query toQuery(ImgBO imgQuery) throws ParseException {
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser parser = new QueryParser("name", analyzer);
		String queryString = "";
		if (imgQuery.getName() != null) {
			queryString += "name:" + imgQuery.getName() + " and ";
		}
		if (imgQuery.getRealname() != null) {
			queryString += "realname:" + imgQuery.getRealname() + " and ";
		}
		if (imgQuery.getUri() != null) {
			queryString += "uri:" + imgQuery.getUri() + " and ";
		}
		if (imgQuery.getNote() != null) {
			queryString += "note:" + imgQuery.getNote() + " and ";
		}
		if (queryString.length() >= 4) {
			queryString = queryString.substring(0, queryString.length() - 4);
		} else {
			queryString = "*:*";
		}
		Query query = parser.parse(queryString);
		return query;
	}

	public void dump(List<ImgBO> list) {
		ThreadPoolEngine.getExecutor().execute(new DumpRunner(list));
	}

	private synchronized void dumpReal(List<ImgBO> list) {
		try {
			IndexWriter writer = getIndexWriter();
			for (ImgBO img : list) {
				Document doc = toDoc(img);
				writer.deleteDocuments(NumericRangeQuery.newLongRange("id", img.getId(), img.getId(), true, true));
				writer.addDocument(doc);
			}
			writer.commit();
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

	private Document toDoc(ImgBO img) {
		Document doc = new Document();
		doc.add(new LongField("id", img.getId(), LongField.TYPE_STORED));
		doc.add(new LongField("saasId", img.getSaasId(), LongField.TYPE_STORED));
		if (img.getShopId() != null) {
			doc.add(new LongField("shopId", img.getShopId(), LongField.TYPE_STORED));
		}
		doc.add(new LongField("userId", img.getUserId(), LongField.TYPE_STORED));
		doc.add(new Field("name", img.getName(), TextField.TYPE_STORED));
		doc.add(new Field("realname", img.getRealname(), TextField.TYPE_STORED));
		doc.add(new Field("uri", img.getUri(), TextField.TYPE_STORED));
		if (img.getNote() != null) {
			doc.add(new Field("note", img.getNote(), TextField.TYPE_STORED));
		}
		if (img.getFeature() != null) {
			doc.add(new Field("feature", img.getFeature(), TextField.TYPE_STORED));
		}
		return doc;
	}

	private Filter toQueryFilter(ImgBO imgQuery) {
		BooleanFilter booleanFilter = null;
		if (imgQuery.getSaasId() != null) {
			if (booleanFilter == null) {
				booleanFilter = new BooleanFilter();
			}
			NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("saasId", imgQuery.getSaasId(), imgQuery.getSaasId(), true, true);
			booleanFilter.add(filter, Occur.MUST);
		}
		if (imgQuery.getId() != null) {
			if (booleanFilter == null) {
				booleanFilter = new BooleanFilter();
			}
			NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("id", imgQuery.getId(), imgQuery.getId(), true, true);
			booleanFilter.add(filter, Occur.MUST);
		}
		if (imgQuery.getShopId() != null) {
			if (booleanFilter == null) {
				booleanFilter = new BooleanFilter();
			}
			NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("shopId", imgQuery.getShopId(), imgQuery.getShopId(), true, true);
			booleanFilter.add(filter, Occur.MUST);
		}
		if (imgQuery.getUserId() != null) {
			if (booleanFilter == null) {
				booleanFilter = new BooleanFilter();
			}
			NumericRangeFilter<Long> filter = NumericRangeFilter.newLongRange("userId", imgQuery.getUserId(), imgQuery.getUserId(), true, true);
			booleanFilter.add(filter, Occur.MUST);
		}
		return booleanFilter;
	}

	private Sort toQuerySort(ImgBO imgQuery) {
		Sort sort = null;
		if (imgQuery.getQuery("orderBy") != null && imgQuery.getQuery("order") != null) {
			String orderBy = (String) imgQuery.getQuery("orderBy");
			String order = (String) imgQuery.getQuery("order");
			sort = new Sort();
			SortField.Type type = SortField.Type.STRING;
			if ("id".equalsIgnoreCase(orderBy)) {
				type = SortField.Type.LONG;
			} else if ("price".equalsIgnoreCase(orderBy)) {
				type = SortField.Type.LONG;
			}
			if (imgQuery.getQuery("searchWord") != null) {
				sort = Sort.RELEVANCE;
			} else {
				SortField sortField = new SortField(orderBy, type, "DESC".equalsIgnoreCase(order));
				sort.setSort(sortField);
			}
		}
		return sort;
	}

	class DumpRunner implements Runnable {

		private ImgBO img;

		private List<ImgBO> imgList;

		public DumpRunner(ImgBO img) {
			this.img = img;
		}

		public DumpRunner(List<ImgBO> imgList) {
			this.imgList = imgList;
		}

		public void run() {
			if (imgList != null) {
				dumpReal(imgList);
			} else {
				dumpReal(img);
			}
		}

		public ImgBO getImg() {
			return img;
		}

		public void setImg(ImgBO img) {
			this.img = img;
		}

		public List<ImgBO> getImgList() {
			return imgList;
		}

		public void setImgList(List<ImgBO> imgList) {
			this.imgList = imgList;
		}

	}

	public synchronized void delete(Long imgId) {
		try {
			if (imgId == null) {
				return;
			}
			try {
				IndexWriter writer = getIndexWriter();
				writer.deleteDocuments(NumericRangeQuery.newLongRange("id", imgId, imgId, true, true));
				writer.commit();
			} catch (Exception e) {
				throw new RuntimeException("", e);
			}
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

	public String getDataDirectory() {
		return "img_luence_index";
	}

}
