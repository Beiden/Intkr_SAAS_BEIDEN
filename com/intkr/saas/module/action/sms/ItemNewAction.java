package com.intkr.saas.module.action.sms;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.manager.BaseManager;
import javax.servlet.http.HttpServletRequest;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemNewBO;
import com.intkr.saas.manager.sms.ItemNewManager;
import com.intkr.saas.util.PagerUtil;

/**
 * 新品推荐
 * 
 * @table item_new
 * 
 * @author Beiden
 * @date 2020-11-11 23:11:23
 * @version 1.0
 */
public class ItemNewAction extends BaseAction<ItemNewBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemNewManager itemNewManager = IOC.get(ItemNewManager.class);

	public ItemNewBO getBO(HttpServletRequest request) {
		ItemNewBO itemNewBO = getParameter(request);
		return itemNewBO;
	}

	public static ItemNewBO getParameter(HttpServletRequest request) {
		ItemNewBO itemNewBO = new ItemNewBO();
		itemNewBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemNewBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemNewBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		itemNewBO.setItemId(RequestUtil.getParam(request, "itemId", Long.class));
		itemNewBO.setName(RequestUtil.getParam(request, "name", String.class));
		itemNewBO.setStatus(RequestUtil.getParam(request, "status", String.class));
		itemNewBO.setNote(RequestUtil.getParam(request, "note", String.class));
		itemNewBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		itemNewBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, itemNewBO);
		return itemNewBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemNewManager;
	}

}
