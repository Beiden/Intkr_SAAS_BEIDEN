package com.intkr.saas.module.toolbox.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.shop.ShopAccuBO;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.shop.ShopAccuManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.module.action.shop.ShopAccuAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

public class ShopAccuDS extends BaseToolBox {

	private ItemManager itemManager = IOC.get("ItemManager");

	private OrderManager orderManager = IOC.get("OrderManager");

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	private ShopManager shopManager = IOC.get("ShopManager");

	private ShopAccuManager tradeEvaluateManager = IOC.get("ShopAccuManager");

	/**
	 * 查询列表
	 */
	public ShopAccuBO select(HttpServletRequest request, HttpServletResponse response) {
		try {
			ShopAccuBO query = ShopAccuAction.getParameter(request);
			query.setSaasId(SessionClient.getSaasId(request));
			query.setShopId(SessionClient.getLoginUser(request).getShop().getId());
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			tradeEvaluateManager.selectAndCount(query);
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

	public ShopAccuBO getById(Object articleIdObject) {
		Long articleId = null;
		if (articleIdObject instanceof String) {
			articleId = Long.valueOf((String) articleIdObject);
		} else {
			articleId = (Long) articleId;
		}
		if (!IdEngine.isValidate(articleId)) {
			return null;
		}
		ShopAccuBO item = tradeEvaluateManager.get(articleId);
		if (item == null) {
			return null;
		}
		return item;
	}

	public ShopAccuBO getFull(Object articleIdObject) {
		Long articleId = null;
		if (articleIdObject instanceof String) {
			articleId = Long.valueOf((String) articleIdObject);
		} else {
			articleId = (Long) articleId;
		}
		if (!IdEngine.isValidate(articleId)) {
			return null;
		}
		ShopAccuBO item = tradeEvaluateManager.get(articleId);
		return item;
	}

}
