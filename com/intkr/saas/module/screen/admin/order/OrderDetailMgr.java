package com.intkr.saas.module.screen.admin.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.domain.type.order.OrderDetailType;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.order.OrderDetailAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-7-28 上午8:06:49
 * @version 1.0
 */
public class OrderDetailMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderManager orderManager = IOC.get("OrderManager");

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	private UserManager userManager = IOC.get("UserManager");

	private ItemManager itemManager = IOC.get("ItemManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Long orderId = RequestUtil.getParam(request, "orderId", Long.class);
		OrderBO order = orderManager.get(orderId);
		OrderDetailBO query = OrderDetailAction.getParameter(request);
		orderDetailManager.selectAndCount(query);
		userManager.fill(query.getDatas());
		itemManager.fill(query.getDatas());
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("order", order);
		request.setAttribute("OrderStatus", OrderStatus.Error);
		request.setAttribute("OrderDetailType", OrderDetailType.Error);
	}

}
