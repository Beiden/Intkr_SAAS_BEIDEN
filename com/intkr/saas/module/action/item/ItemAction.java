package com.intkr.saas.module.action.item;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.aop.StartTransaction;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.distributed.search.client.ItemSearchClient;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemSkuBO;
import com.intkr.saas.domain.bo.item.ItemSkuPropertyBO;
import com.intkr.saas.domain.bo.item.ItemSkuPropertyValueBO;
import com.intkr.saas.domain.bo.item.ItemSkuValueBO;
import com.intkr.saas.domain.bo.item.ItemSpuBO;
import com.intkr.saas.domain.bo.item.ItemSpuValueBO;
import com.intkr.saas.domain.bo.item.ItemTagBO;
import com.intkr.saas.domain.bo.item.ItemTagRelatedBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.domain.type.item.ItemStatus;
import com.intkr.saas.facade.item.JdItemCollect;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.item.ItemSkuManager;
import com.intkr.saas.manager.item.ItemSkuPropertyManager;
import com.intkr.saas.manager.item.ItemSkuPropertyValueManager;
import com.intkr.saas.manager.item.ItemSkuValueManager;
import com.intkr.saas.manager.item.ItemSpuManager;
import com.intkr.saas.manager.item.ItemSpuValueManager;
import com.intkr.saas.manager.item.ItemTagManager;
import com.intkr.saas.manager.item.ItemTagRelatedManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.util.MoneyUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-12 上午11:24:16
 * @version 1.0
 */
public class ItemAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ImgManager imgManager = IOC.get("ImgManager");

	private ItemManager itemManager = IOC.get("ItemManager");

	private ShopManager shopManager = IOC.get("ShopManager");

	private static ItemTagManager tagManager = IOC.get("ItemTagManager");

	private ItemTagRelatedManager itemTagRelatedManager = IOC.get("ItemTagRelatedManager");

	private MsgManager msgManager = IOC.get("MsgManager");

	private ItemSpuManager itemSpuManager = IOC.get("ItemSpuManager");

	private ItemSkuPropertyManager itemSkuPropertyManager = IOC.get("ItemSkuPropertyManager");

	private ItemSpuValueManager itemSpuValueManager = IOC.get("ItemSpuValueManager");

	private ItemSkuPropertyValueManager itemSkuPropertyValueManager = IOC.get("ItemSkuPropertyValueManager");

	private ItemSkuManager itemSkuManager = IOC.get("ItemSkuManager");

	private ItemSkuValueManager itemSkuValueManager = IOC.get("ItemSkuValueManager");

	private ItemSearchClient itemSearchClient = IOC.get("ItemSearchClient");

	@StartTransaction
	public void doAddTag(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String itemId = RequestUtil.getParam(request, "itemId");
		String tagName = RequestUtil.getParam(request, "tagName");
		Long saasId = SessionClient.getSaasId(request);
		ItemBO item = itemManager.get(itemId);
		ItemTagBO tag = tagManager.getByName(saasId, tagName);
		if (tag == null) {
			tag = new ItemTagBO();
			tag.setSaasId(saasId);
			tag.setId(tagManager.getId());
			tag.setName(tagName);
			tagManager.insert(tag);
		}
		if (!item.containsTagId(tag.getId())) {
			item.addTagId(tag.getId());
			itemManager.update(item);
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "打标成功");
	}

	@StartTransaction
	public void doRemoveTag(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String itemId = RequestUtil.getParam(request, "itemId");
		String tagName = RequestUtil.getParam(request, "tagName");
		Long tagId = RequestUtil.getParam(request, "tagId", Long.class);
		Long saasId = SessionClient.getSaasId(request);
		ItemBO item = itemManager.get(itemId);
		if (tagId == null) {
			ItemTagBO tag = tagManager.getByName(saasId, tagName);
			tagId = tag.getId();
		}
		if (item.containsTagId(tagId)) {
			item.removeTagId(tagId);
			itemManager.update(item);
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "删除标签成功");
	}

	public void doCollect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JdItemCollect.start();
		request.setAttribute("result", true);
		request.setAttribute("msg", "操作成功！");
	}

	@StartTransaction
	public void doApprove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String itemId = RequestUtil.getParam(request, "itemId");
		ItemBO item = itemManager.get(itemId);
		item.setStatus(ItemStatus.Approve.getCode());
		itemManager.update(item);
		request.setAttribute("result", true);
		request.setAttribute("msg", "审核通过");
		// msgManager.sendSysMsg(item.getUserId(), "商品审核结果", "亲，恭喜您，您发布的商品（" +
		// item.getName() + "）已通过审核，已可上线进行交易。有任何疑问请随时联系您的专属客服。");
	}

	@StartTransaction
	public void doApplyAudit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String itemId = RequestUtil.getParam(request, "itemId");
		ItemBO item = itemManager.get(itemId);
		item.setStatus(ItemStatus.WaitAudit.getCode());
		itemManager.update(item);
		request.setAttribute("result", true);
		request.setAttribute("msg", "申请审核成功");
	}

	@StartTransaction
	public void doUpShelf(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String itemId = RequestUtil.getParam(request, "itemId");
		ItemBO item = itemManager.get(itemId);
		item.setStatus(ItemStatus.UpShelf.getCode());
		itemManager.update(item);
		request.setAttribute("result", true);
		request.setAttribute("msg", "上架成功");
	}

	@StartTransaction
	public void doDownShelf(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String itemId = RequestUtil.getParam(request, "itemId");
		ItemBO item = itemManager.get(itemId);
		item.setStatus(ItemStatus.DownShelf.getCode());
		itemManager.update(item);
		request.setAttribute("result", true);
		request.setAttribute("msg", "下架成功");
	}

	public void doFullDump(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long saasId = SessionClient.getSaasId(request);
		itemSearchClient.fullDump(saasId);
		request.setAttribute("result", true);
		request.setAttribute("msg", "操作成功！");
	}

	@StartTransaction
	public void doUnapprove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String itemId = RequestUtil.getParam(request, "itemId");
		ItemBO item = itemManager.get(itemId);
		item.setStatus(ItemStatus.Unapprove.getCode());
		itemManager.update(item);
		request.setAttribute("result", true);
		request.setAttribute("msg", "审核驳回");
		// msgManager.sendSysMsg(item.getUserId(), "商品审核结果", "亲，您好，您发布的商品（" +
		// item.getName() + "）因信息疏漏未能通过审核，请重新录入。有任何疑问请随时联系您的专属客服。");
	}

	public static ItemBO getParameter(HttpServletRequest request) {
		ItemBO itemBO = new ItemBO();
		itemBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		itemBO.setFirstCategoryId(RequestUtil.getParam(request, "firstCategoryId", Long.class));
		itemBO.setSecondCategoryId(RequestUtil.getParam(request, "secondCategoryId", Long.class));
		itemBO.setThirdCategoryId(RequestUtil.getParam(request, "thirdCategoryId", Long.class));
		itemBO.setImgIds(RequestUtil.getParam(request, "imgIds"));
		itemBO.setTagIds(RequestUtil.getParam(request, "tagIds"));
		itemBO.setPrice(MoneyUtil.parse(RequestUtil.getParam(request, "price")));
		itemBO.setName(RequestUtil.getParam(request, "name"));
		itemBO.setSlogan(RequestUtil.getParam(request, "slogan"));
		itemBO.setStatus(RequestUtil.getParam(request, "status"));
		itemBO.setContent(RequestUtil.getParam(request, "content"));
		itemBO.setFeature(RequestUtil.getParam(request, "feature"));
		itemBO.setSkuType(RequestUtil.getParam(request, "skuType", Integer.class));
		itemBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		itemBO.setDeliveryFeeId(RequestUtil.getParam(request, "deliveryFeeId", Long.class));
		if (RequestUtil.existParam(request, "fcategoryId")) {// 前台类目ID
			itemBO.setQuery("fcategoryId", RequestUtil.getParam(request, "fcategoryId"));
		}
		if (RequestUtil.existParam(request, "categoryId")) {// 后台类目ID
			itemBO.setQuery("categoryId", RequestUtil.getParam(request, "categoryId"));
		}
		if (RequestUtil.existParam(request, "searchWord")) {// 关键字
			itemBO.setQuery("searchWord", RequestUtil.getParam(request, "searchWord"));
			itemBO.setQuery("searchWordSQL", "%" + RequestUtil.getParam(request, "searchWord") + "%");
		}
		if (RequestUtil.existParam(request, "tagIdLike")) {// 标签ID
			itemBO.setQuery("tagIdLike", RequestUtil.getParam(request, "tagIdLike"));
			itemBO.setQuery("tagIdLikeSQL", "%;" + RequestUtil.getParam(request, "tagIdLike") + ";%");
		}
		if (RequestUtil.existParam(request, "categoried")) {// 是否已分配置后台类目
			itemBO.setQuery("categoried", RequestUtil.getParam(request, "categoried"));
		}
		if (RequestUtil.existParam(request, "propertyIds")) {// 属性ID
			itemBO.setQuery("propertyIds", request.getParameterValues("propertyIds"));
		}
		if (RequestUtil.existParam(request, "propertyNames")) { // 属性名搜索
			itemBO.setQuery("propertyNames", request.getParameterValues("propertyNames"));
		}
		if (RequestUtil.existParam(request, "searchData")) {// 搜索数据源：DB/ES
			itemBO.setQuery("searchData", RequestUtil.getParam(request, "searchData"));
		}
		if (RequestUtil.existParam(request, "tagId")) {// 标签ID
			itemBO.setQuery("tagId", RequestUtil.getParam(request, "tagId"));
		}
		if (RequestUtil.existParam(request, "tagName")) {// 标签名称
			String tabName = RequestUtil.getParam(request, "tagName");
			itemBO.setQuery("tagName", RequestUtil.getParam(request, "tagName"));
			ItemTagBO tag = tagManager.getByName(SessionClient.getSaasId(request), tabName);
			if (tag == null) {
				itemBO.setQuery("tagId", -10l);
			} else {
				itemBO.setQuery("tagId", tag.getId());
			}
		}
		PagerUtil.initPage(request, itemBO);
		return itemBO;
	}

	@StartTransaction
	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ItemBO item = getParameter(request);
		item.setFirstCategoryId(RequestUtil.getParam(request, "firstCategoryId", Long.class, -1l));
		item.setSecondCategoryId(RequestUtil.getParam(request, "secondCategoryId", Long.class, -1l));
		item.setThirdCategoryId(RequestUtil.getParam(request, "thirdCategoryId", Long.class, -1l));
		if (item.getId() == null) {
			item.setId(itemManager.getId());
		}
		ShopBO shop = shopManager.getByUserId(SessionClient.getLoginUserId(request));
		item.setShopId(shop.getId());
		if (!RequestUtil.existParam(request, "setStatus")) {
			item.setStatus(ItemStatus.WaitAudit.getCode());
		}
		String tagIds = insertTags(request, item);
		item.setTagIds(tagIds);
		insertSkuProperty(request, item);
		insertSpu(request, item);
		List<ItemSkuBO> insertSkuList = getNewSku(request);
		insertSku(insertSkuList);
		caculateItemPrice(item, insertSkuList);
		itemManager.insert(item);
		itemSearchClient.dump(item.getId());
		request.setAttribute("result", true);
		request.setAttribute("msg", "发布成功，请等待审核结果！");
	}

	@StartTransaction
	public void doDeleteOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long itemId = RequestUtil.getParam(request, "deleteId", Long.class);
		ItemBO item = itemManager.get(itemId);
		if (!SessionClient.isSaasData(request, item)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "商品不存在！");
			return;
		}
		itemManager.delete(itemId);
		request.setAttribute("result", true);
		request.setAttribute("msg", "删除成功");
	}

	@StartTransaction
	public void doDeleteAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idsString = RequestUtil.getParam(request, "deleteIds");
		String[] idsStrings = idsString.split(",");
		for (String idString : idsStrings) {
			Long itemId = Long.valueOf(idString);
			ItemBO item = itemManager.get(itemId);
			if (!SessionClient.isSaasData(request, item)) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "商品[" + itemId + "]不存在！");
				return;
			}
			itemManager.delete(itemId);
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "批量删除成功");
	}

	public void doSelectOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ItemBO query = getParameter(request);
		ItemBO item = itemManager.selectOne(query);
		request.setAttribute("data", item);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功");
	}

	public void doSelectOneFull(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ItemBO query = getParameter(request);
		ItemBO item = itemManager.selectOne(query);
		itemSpuManager.fillFull(item);
		itemSkuManager.fillFull(item);
		request.setAttribute("data", item);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功");
	}

	@StartTransaction
	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ItemBO item = getParameter(request);
		item.setFirstCategoryId(RequestUtil.getParam(request, "firstCategoryId", Long.class, -1l));
		item.setSecondCategoryId(RequestUtil.getParam(request, "secondCategoryId", Long.class, -1l));
		item.setThirdCategoryId(RequestUtil.getParam(request, "thirdCategoryId", Long.class, -1l));
		deleteItemTagRelated(item);
		String tagIds = insertTags(request, item);
		item.setTagIds(tagIds);
		deleteSpu(item);
		insertSpu(request, item);

		deleteSkuProperty(item);
		insertSkuProperty(request, item);

		List<ItemSkuBO> newSkuList = updateSku(request, item);

		caculateItemPrice(item, newSkuList);

		itemManager.update(item);
		itemSearchClient.dump(item.getId());

		request.setAttribute("result", true);
		request.setAttribute("msg", "更新成功");
	}

	private void caculateItemPrice(ItemBO item, List<ItemSkuBO> newSkuList) {
		item.setPrice(0L);
		for (ItemSkuBO sku : newSkuList) {
			if (item.getPrice() < sku.getPrice()) {
				item.setPrice(sku.getPrice());
			}
		}
	}

	private List<ItemSkuBO> updateSku(HttpServletRequest request, ItemBO item) {
		List<ItemSkuBO> newSkuList = getNewSku(request);
		itemSkuManager.fill(item);
		itemSkuValueManager.fill(item.getSkus());
		List<ItemSkuBO> oldSkuList = item.getSkus();
		List<ItemSkuBO> insertSkuList = getInsertSkuList(newSkuList, oldSkuList);
		List<ItemSkuBO> deleteSkuList = getDeleteSkuList(newSkuList, oldSkuList);
		List<ItemSkuBO> updateSkuList = getUpdateSkuList(newSkuList, oldSkuList);

		deleteSku(deleteSkuList);
		insertSku(insertSkuList);
		updateSku(updateSkuList);
		return newSkuList;
	}

	private List<ItemSkuBO> getInsertSkuList(List<ItemSkuBO> newSkuList, List<ItemSkuBO> oldSkuList) {
		List<ItemSkuBO> insertSkuList = new ArrayList<ItemSkuBO>();
		for (ItemSkuBO sku : newSkuList) {
			if (sku.getId() <= 0l) {
				insertSkuList.add(sku);
			}
		}
		return insertSkuList;
	}

	private List<ItemSkuBO> getDeleteSkuList(List<ItemSkuBO> newSkuList, List<ItemSkuBO> oldSkuList) {
		List<ItemSkuBO> deleteSkuList = new ArrayList<ItemSkuBO>();
		for (ItemSkuBO oldSku : oldSkuList) {
			if (!contains(newSkuList, oldSku)) {
				deleteSkuList.add(oldSku);
			}
		}
		return deleteSkuList;
	}

	private List<ItemSkuBO> getUpdateSkuList(List<ItemSkuBO> newSkuList, List<ItemSkuBO> oldSkuList) {
		List<ItemSkuBO> updateSkuList = new ArrayList<ItemSkuBO>();
		for (ItemSkuBO oldSku : oldSkuList) {
			if (contains(newSkuList, oldSku)) {
				updateSkuList.add(oldSku);
			}
		}
		return updateSkuList;
	}

	private boolean contains(List<ItemSkuBO> newSkuList, ItemSkuBO oldSku) {
		for (ItemSkuBO newSku : newSkuList) {
			if (oldSku.getId().equals(newSku.getId())) {
				oldSku.setProperty("newSku", newSku);
				return true;
			}
		}
		return false;
	}

	private void deleteSpu(ItemBO bo) {
		itemSpuManager.fill(bo);
		itemSpuValueManager.fill(bo.getSpus());
		for (ItemSpuBO spu : bo.getSpus()) {
			for (ItemSpuValueBO spuValue : spu.getSpuValues()) {
				itemSpuValueManager.delete(spuValue.getId());
			}
			itemSpuManager.delete(spu.getId());
		}
	}

	private void deleteSkuProperty(ItemBO bo) {
		itemSkuPropertyManager.fill(bo);
		itemSkuPropertyValueManager.fill(bo.getSpus());
		for (ItemSpuBO spu : bo.getSpus()) {
			for (ItemSpuValueBO spuValue : spu.getSpuValues()) {
				itemSkuPropertyValueManager.delete(spuValue.getId());
			}
			itemSkuPropertyManager.delete(spu.getId());
		}
	}

	private void deleteSku(List<ItemSkuBO> deleteSkuList) {
		for (ItemSkuBO sku : deleteSkuList) {
			for (ItemSkuValueBO skuValue : sku.getSkuValues()) {
				itemSkuValueManager.delete(skuValue.getId());
			}
			itemSkuManager.delete(sku.getId());
		}
	}

	private void insertSpu(HttpServletRequest request, ItemBO item) {
		List<ItemSpuBO> resultList = getNewSpu(request);
		for (ItemSpuBO spu : resultList) {
			spu.setId(itemSpuManager.getId());
			spu.setItemId(item.getId());
			for (ItemSpuValueBO spuValue : spu.getSpuValues()) {
				spuValue.setId(itemSpuValueManager.getId());
				spuValue.setSaasId(SessionClient.getSaas(request).getId());
				spuValue.setSpuId(spu.getId());
				itemSpuValueManager.insert(spuValue);
			}
			itemSpuManager.insert(spu);
		}
	}

	private void insertSkuProperty(HttpServletRequest request, ItemBO bo) {
		List<ItemSkuPropertyBO> resultList = getNewSkuProperty(request);
		for (ItemSkuPropertyBO spu : resultList) {
			spu.setId(itemSkuPropertyManager.getId());
			spu.setItemId(bo.getId());
			for (ItemSkuPropertyValueBO spuValue : spu.getSkuPropertyValues()) {
				spuValue.setId(itemSkuPropertyValueManager.getId());
				spuValue.setSaasId(SessionClient.getSaas(request).getId());
				spuValue.setSkuPropertyId(spu.getId());
				itemSkuPropertyValueManager.insert(spuValue);
			}
			itemSkuPropertyManager.insert(spu);
		}
	}

	private void insertSku(List<ItemSkuBO> insertSkuList) {
		for (ItemSkuBO sku : insertSkuList) {
			sku.setId(itemSkuManager.getId());
			for (ItemSkuValueBO skuValue : sku.getSkuValues()) {
				skuValue.setId(itemSkuValueManager.getId());
				skuValue.setSkuId(sku.getId());
				itemSkuValueManager.insert(skuValue);
			}
			itemSkuManager.insert(sku);
		}
	}

	private void updateSku(List<ItemSkuBO> updateSkuList) {
		for (ItemSkuBO sku : updateSkuList) {
			itemSkuManager.update(sku);
			// for (ItemSkuValueBO skuValue : sku.getSkuValues()) {
			// skuValue.setId(itemSkuValueManager.getId());
			// skuValue.setSkuId(sku.getId());
			// itemSkuValueManager.insert(skuValue);
			// }
		}
	}

	private void deleteItemTagRelated(ItemBO bo) {
		List<ItemTagRelatedBO> list = itemTagRelatedManager.selectByItemId(bo.getId());
		for (ItemTagRelatedBO itr : list) {
			itemTagRelatedManager.delete(itr.getId());
		}
	}

	private String insertTags(HttpServletRequest request, ItemBO bo) {
		String tagIds = "";
		String[] tags = request.getParameterValues("tags");
		if (tags != null) {
			for (String tagName : tags) {
				ItemTagBO tag = getTagWithInsert(SessionClient.getSaasId(request), tagName);
				insertItemTagRelated(request, bo, tag);
				tagIds += ";" + tag.getId();
			}
		}
		if (tagIds.length() > 1) {
			tagIds += ";";
		}
		return tagIds;
	}

	private void insertItemTagRelated(HttpServletRequest request, ItemBO bo, ItemTagBO tag) {
		ItemTagRelatedBO itr = new ItemTagRelatedBO();
		itr.setId(itemTagRelatedManager.getId());
		itr.setSaasId(SessionClient.getSaasId(request));
		itr.setTagId(tag.getId());
		itr.setRelatedId(bo.getId());
		itemTagRelatedManager.insert(itr);
	}

	// 如果Tag不存在，会先保存到数据库
	private ItemTagBO getTagWithInsert(Long saasId, String tagName) {
		ItemTagBO tag = tagManager.getByName(saasId, tagName);
		if (tag == null) {
			tag = new ItemTagBO();
			tag.setSaasId(saasId);
			tag.setId(tagManager.getId());
			tag.setName(tagName);
			tagManager.insert(tag);
		}
		return tag;
	}

	private List<ItemSpuBO> getNewSpu(HttpServletRequest request) {
		List<ItemSpuBO> resultList = new ArrayList<ItemSpuBO>();
		String[] propertyIds = request.getParameterValues("propertyIds");
		if (propertyIds != null) {
			for (String propertyId : propertyIds) {
				ItemSpuBO spu = new ItemSpuBO();
				spu.setPropertyId(Long.valueOf(propertyId));
				String[] propertyValueIds = request.getParameterValues(propertyId + "-propertyValueIds");
				List<ItemSpuValueBO> spuValues = new ArrayList<ItemSpuValueBO>();
				if (propertyValueIds != null) {
					for (String propertyValueId : propertyValueIds) {
						ItemSpuValueBO value = new ItemSpuValueBO();
						value.setValueId(Long.valueOf(propertyValueId));
						spuValues.add(value);
					}
				}
				spu.setSpuValues(spuValues);
				resultList.add(spu);
			}
		}
		return resultList;
	}

	private List<ItemSkuPropertyBO> getNewSkuProperty(HttpServletRequest request) {
		List<ItemSkuPropertyBO> resultList = new ArrayList<ItemSkuPropertyBO>();
		String[] propertyIds = request.getParameterValues("skuPropertyIds");
		if (propertyIds != null) {
			for (String propertyId : propertyIds) {
				ItemSkuPropertyBO spu = new ItemSkuPropertyBO();
				spu.setPropertyId(Long.valueOf(propertyId));
				String[] propertyValueIds = request.getParameterValues(propertyId + "-skuPropertyValueIds");
				List<ItemSkuPropertyValueBO> spuValues = new ArrayList<ItemSkuPropertyValueBO>();
				if (propertyValueIds != null) {
					for (String propertyValueId : propertyValueIds) {
						ItemSkuPropertyValueBO value = new ItemSkuPropertyValueBO();
						value.setValueId(Long.valueOf(propertyValueId));
						spuValues.add(value);
					}
				}
				spu.setSkuPropertyValues(spuValues);
				resultList.add(spu);
			}
		}
		return resultList;
	}

	private List<ItemSkuBO> getNewSku(HttpServletRequest request) {
		List<ItemSkuBO> resultList = new ArrayList<ItemSkuBO>();
		String[] skuIds = request.getParameterValues("skuIds");
		if (skuIds != null) {
			for (String skuId : skuIds) {
				String skuStatus = request.getParameter(skuId + "-skuStatus");
				String skuIsDefault = request.getParameter(skuId + "-skuIsDefault");
				String skuName = request.getParameter(skuId + "-skuName");
				String skuImgId = request.getParameter(skuId + "-skuImgId");
				String skuColor = request.getParameter(skuId + "-skuColor");
				String skuInventory = request.getParameter(skuId + "-skuInventory");
				String skuPrice = request.getParameter(skuId + "-skuPrice");
				ItemSkuBO sku = new ItemSkuBO();
				sku.setId(Long.valueOf(skuId));
				sku.setItemId(RequestUtil.getParam(request, "id", Long.class));
				sku.setSaasId(SessionClient.getSaasId(request));
				sku.setStatus(skuStatus);
				sku.setIsDefault(Integer.valueOf(skuIsDefault));
				sku.setName(skuName);
				sku.setImgId(Long.valueOf(skuImgId));
				sku.setColor(skuColor);
				sku.setInventory(Integer.valueOf(skuInventory));
				sku.setPrice(MoneyUtil.parse(skuPrice));
				String[] skuPropertyValueIds = request.getParameterValues(skuId + "-skuPropertyValueIds");
				List<ItemSkuValueBO> spuValues = new ArrayList<ItemSkuValueBO>();
				if (skuPropertyValueIds != null) {
					for (String skuValueId : skuPropertyValueIds) {
						ItemSkuValueBO value = new ItemSkuValueBO();
						value.setSaasId(SessionClient.getSaasId(request));
						value.setValueId(Long.valueOf(skuValueId));
						spuValues.add(value);
					}
				}
				sku.setSkuValues(spuValues);
				resultList.add(sku);
			}
		}
		return resultList;
	}

}
