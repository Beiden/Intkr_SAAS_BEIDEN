package com.intkr.saas.module.action.sms;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.manager.BaseManager;
import javax.servlet.http.HttpServletRequest;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemPopularBO;
import com.intkr.saas.manager.sms.ItemPopularManager;
import com.intkr.saas.util.PagerUtil;

/**
 * 人气商品
 * 
 * @table item_popular
 * 
 * @author Beiden
 * @date 2020-11-11 23:10:57
 * @version 1.0
 */
public class ItemPopularAction extends BaseAction<ItemPopularBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemPopularManager itemPopularManager = IOC.get(ItemPopularManager.class);

	public ItemPopularBO getBO(HttpServletRequest request) {
		ItemPopularBO itemPopularBO = getParameter(request);
		return itemPopularBO;
	}

	public static ItemPopularBO getParameter(HttpServletRequest request) {
		ItemPopularBO itemPopularBO = new ItemPopularBO();
		itemPopularBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemPopularBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemPopularBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		itemPopularBO.setItemId(RequestUtil.getParam(request, "itemId", Long.class));
		itemPopularBO.setName(RequestUtil.getParam(request, "name", String.class));
		itemPopularBO.setStatus(RequestUtil.getParam(request, "status", String.class));
		itemPopularBO.setNote(RequestUtil.getParam(request, "note", String.class));
		itemPopularBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		itemPopularBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, itemPopularBO);
		return itemPopularBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemPopularManager;
	}

}
