package com.intkr.saas.module.toolbox.buyer;

import java.util.List;

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

public class BuyerAccuDS extends BaseToolBox {

	private ItemManager itemManager = IOC.get("ItemManager");

	private OrderManager orderManager = IOC.get("OrderManager");

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	private ShopManager shopManager = IOC.get("ShopManager");

	private ShopAccuManager shopAccuManager = IOC.get("ShopAccuManager");

	/**
	 * 查询列表
	 */
	public ShopAccuBO select(HttpServletRequest request, HttpServletResponse response) {
		try {
			ShopAccuBO query = ShopAccuAction.getParameter(request);
			query.setSaasId(SessionClient.getSaasId(request));
			query.setUserId(SessionClient.getLoginUserId(request));
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			shopAccuManager.selectAndCount(query);
			List<ShopAccuBO> list = query.getDatas();
			shopManager.fill(list);
			itemManager.fill(list);
			orderManager.fill(list);
			orderDetailManager.fill(list);
			return query;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	public ShopAccuBO getById(Object accuIdObject) {
		Long articleId = null;
		if (accuIdObject instanceof String) {
			articleId = Long.valueOf((String) accuIdObject);
		} else {
			articleId = (Long) accuIdObject;
		}
		if (!IdEngine.isValidate(articleId)) {
			return null;
		}
		ShopAccuBO item = shopAccuManager.get(articleId);
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
		ShopAccuBO item = shopAccuManager.get(articleId);
		return item;
	}

}
