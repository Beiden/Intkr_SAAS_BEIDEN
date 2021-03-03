package com.intkr.saas.module.toolbox.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.OrderEvaluateBO;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.order.OrderEvaluateManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.module.action.order.OrderEvaluateAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

public class BuyerOrderEvaluateDS extends BaseToolBox {

	private ItemManager itemManager = IOC.get("ItemManager");

	private OrderManager orderManager = IOC.get("OrderManager");

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	private ShopManager shopManager = IOC.get("ShopManager");

	private OrderEvaluateManager tradeEvaluateManager = IOC.get("OrderItemEvaluateManager");

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public OrderEvaluateBO select(HttpServletRequest request, HttpServletResponse response) {
		try {
			OrderEvaluateBO query = OrderEvaluateAction.getParameter(request);
			query.setUserId(SessionClient.getLoginUserId(request));
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			tradeEvaluateManager.selectAndCount(query);
			List<OrderEvaluateBO> list = query.getDatas();
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

	public OrderEvaluateBO getById(Object articleIdObject) {
		Long articleId = null;
		if (articleIdObject instanceof String) {
			articleId = Long.valueOf((String) articleIdObject);
		} else {
			articleId = (Long) articleId;
		}
		if (!IdEngine.isValidate(articleId)) {
			return null;
		}
		OrderEvaluateBO item = tradeEvaluateManager.get(articleId);
		if (item == null) {
			return null;
		}
		return item;
	}

	public OrderEvaluateBO getFull(Object articleIdObject) {
		Long articleId = null;
		if (articleIdObject instanceof String) {
			articleId = Long.valueOf((String) articleIdObject);
		} else {
			articleId = (Long) articleId;
		}
		if (!IdEngine.isValidate(articleId)) {
			return null;
		}
		OrderEvaluateBO item = tradeEvaluateManager.get(articleId);
		shopManager.fill(item);
		itemManager.fill(item);
		orderManager.fill(item);
		orderDetailManager.fill(item);
		return item;
	}

}
