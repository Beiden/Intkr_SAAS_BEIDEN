package com.intkr.saas.module.toolbox.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.ItemCustServBO;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.order.ItemCustServManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.module.action.order.ItemCustServAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

public class ItemCustServDS extends BaseToolBox {

	private ItemManager itemManager = IOC.get("ItemManager");

	private OrderManager orderManager = IOC.get("OrderManager");

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	private ShopManager shopManager = IOC.get("ShopManager");

	private ItemCustServManager itemCustServManager = IOC.get("ShopItemCustServManager");

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ItemCustServBO select(HttpServletRequest request, HttpServletResponse response) {
		try {
			ItemCustServBO query = ItemCustServAction.getParameter(request);
			query.setUserId(SessionClient.getLoginUserId(request));
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			itemCustServManager.selectAndCount(query);
			shopManager.fill(query.getDatas());
			itemManager.fill(query.getDatas());
			orderManager.fill(query.getDatas());
			orderDetailManager.fill(query.getDatas());
			return query;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	public ItemCustServBO getById(Object articleIdObject) {
		Long articleId = null;
		if (articleIdObject instanceof String) {
			articleId = Long.valueOf((String) articleIdObject);
		} else {
			articleId = (Long) articleId;
		}
		if (!IdEngine.isValidate(articleId)) {
			return null;
		}
		ItemCustServBO item = itemCustServManager.get(articleId);
		if (item == null) {
			return null;
		}
		return item;
	}

	public ItemCustServBO getFull(Object articleIdObject) {
		Long articleId = null;
		if (articleIdObject instanceof String) {
			articleId = Long.valueOf((String) articleIdObject);
		} else {
			articleId = (Long) articleId;
		}
		if (!IdEngine.isValidate(articleId)) {
			return null;
		}
		ItemCustServBO item = itemCustServManager.get(articleId);
		shopManager.fill(item);
		itemManager.fill(item);
		orderManager.fill(item);
		orderDetailManager.fill(item);
		return item;
	}

}
