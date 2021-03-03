package com.intkr.saas.module.toolbox.buyer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.domain.bo.order.OrderTimeLineBO;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.order.DeliveryAddressManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.order.OrderTimeLineManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.order.OrderAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.util.map.TwoMap;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:59:08
 * @version 1.0
 */
public class BuyerOrderDS extends BaseToolBox {

	private ItemManager itemManager = IOC.get("ItemManager");

	private UserManager userManager = IOC.get("UserManager");

	private DeliveryAddressManager deliveryAddressManager = IOC.get("DeliveryAddressManager");

	private ShopManager shopManager = IOC.get("ShopManager");

	private OrderManager orderManager = IOC.get("OrderManager");

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	private OrderTimeLineManager orderTimeLineManager = IOC.get("OrderTimeLineManager");

	public OrderBO select(HttpServletRequest request, HttpServletResponse response) {
		try {
			OrderBO query = OrderAction.getParameter(request);
			query.setUserId(SessionClient.getLoginUserId(request));
			query = orderManager.selectAndCount(query);
			List<OrderBO> list = query.getDatas();
			orderDetailManager.fills(list);
			for (OrderBO order : list) {
				order.setShop(shopManager.fill(order).getShop());
				userManager.fill(order);
				for (OrderDetailBO orderDetail : order.getOrderDetails()) {
					OrderDetailBO detailTmp = itemManager.fill(orderDetail);
					orderDetail.setItem(detailTmp.getItem());
				}
			}
			return query;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	public OrderBO getById(Object orderIdObject) {
		Long orderId = null;
		if (orderIdObject instanceof String) {
			orderId = Long.valueOf((String) orderIdObject);
		} else {
			orderId = (Long) orderIdObject;
		}
		if (!IdEngine.isValidate(orderId)) {
			return null;
		}
		OrderBO order = orderManager.get(orderId);
		if (order == null) {
			return null;
		}
		return order;
	}

	public OrderBO getByOrderDetailId(Object orderDetailIdObject) {
		Long orderDetailId = null;
		if (orderDetailIdObject instanceof String) {
			orderDetailId = Long.valueOf((String) orderDetailIdObject);
		} else {
			orderDetailId = (Long) orderDetailId;
		}
		if (!IdEngine.isValidate(orderDetailId)) {
			return null;
		}
		OrderDetailBO detail = orderDetailManager.get(orderDetailId);
		if (detail == null) {
			return null;
		}
		OrderBO order = orderManager.get(detail.getOrderId());
		if (order == null) {
			return null;
		}
		order.setOrderDetails(new ArrayList<OrderDetailBO>());
		itemManager.fill(detail);
		order.getOrderDetails().add(detail);
		return order;
	}

	public OrderBO getFull(Object orderIdObject) {
		Long orderId = null;
		if (orderIdObject instanceof String) {
			orderId = Long.valueOf((String) orderIdObject);
		} else {
			orderId = (Long) orderId;
		}
		if (!IdEngine.isValidate(orderId)) {
			return null;
		}
		OrderBO order = orderManager.get(orderId);
		shopManager.fill(order);
		deliveryAddressManager.fill(order);
		userManager.fill(order.getShop());
		orderDetailManager.fills(order);
		for (OrderDetailBO orderDetail : order.getOrderDetails()) {
			itemManager.fill(orderDetail);
		}
		return order;
	}

	public OrderTimeLineBO getTimeLine(Object orderIdObject) {
		Long orderId = null;
		if (orderIdObject instanceof String) {
			orderId = Long.valueOf((String) orderIdObject);
		} else {
			orderId = (Long) orderId;
		}
		if (!IdEngine.isValidate(orderId)) {
			return null;
		}
		OrderTimeLineBO query = new OrderTimeLineBO();
		query.setOrderId(orderId);
		query.set_pageSize(10000);
		query.setQuery("orderBy", "time");
		query.setQuery("order", "desc");
		orderTimeLineManager.selectAndCount(query);
		List<OrderTimeLineBO> list = query.getDatas();
		userManager.fill(list);
		return query;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.Error;
	}

	public List<TwoMap<String, Integer>> buyerCountByStatus(HttpServletRequest request) {
		return orderManager.buyerCountByStatus(SessionClient.getLoginUserId(request));
	}

}
