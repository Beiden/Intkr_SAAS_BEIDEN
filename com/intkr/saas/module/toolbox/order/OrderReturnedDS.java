package com.intkr.saas.module.toolbox.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.OrderReturnedBO;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.order.OrderReturnedManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.module.action.order.OrderReturnedAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

public class OrderReturnedDS extends BaseToolBox {

	private ItemManager itemManager = IOC.get("ItemManager");

	private OrderManager orderManager = IOC.get("OrderManager");

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	private ShopManager shopManager = IOC.get("ShopManager");

	private OrderReturnedManager orderReturnedManager = IOC.get("ShopOrderReturnedManager");

	/**
	 * 查询列表
	 */
	public OrderReturnedBO select(HttpServletRequest request, HttpServletResponse response) {
		try {
			OrderReturnedBO query = OrderReturnedAction.getParameter(request);
			query.setUserId(SessionClient.getLoginUserId(request));
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			orderReturnedManager.selectAndCount(query);
			List<OrderReturnedBO> list = query.getDatas();
			shopManager.fill(list);
			for (OrderReturnedBO orderReturn : list) {
				orderManager.fill(orderReturn);
				orderDetailManager.fills(orderReturn.getOrder());
				itemManager.fill(orderReturn.getOrder());
			}
			return query;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	public OrderReturnedBO getById(Object articleIdObject) {
		Long articleId = null;
		if (articleIdObject instanceof String) {
			articleId = Long.valueOf((String) articleIdObject);
		} else {
			articleId = (Long) articleId;
		}
		if (!IdEngine.isValidate(articleId)) {
			return null;
		}
		OrderReturnedBO item = orderReturnedManager.get(articleId);
		if (item == null) {
			return null;
		}
		return item;
	}

	public OrderReturnedBO getFull(Object articleIdObject) {
		Long articleId = null;
		if (articleIdObject instanceof String) {
			articleId = Long.valueOf((String) articleIdObject);
		} else {
			articleId = (Long) articleId;
		}
		if (!IdEngine.isValidate(articleId)) {
			return null;
		}
		OrderReturnedBO item = orderReturnedManager.get(articleId);
		shopManager.fill(item);
		orderManager.fill(item);
		return item;
	}

}
