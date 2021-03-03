package com.intkr.saas.module.screen.admin.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.order.OrderAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-7-27 下午5:53:56
 * @version 1.0
 */
public class OrderMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderManager orderManager = IOC.get("OrderManager");

	private UserManager userManager = IOC.get("UserManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		OrderBO query = OrderAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = orderManager.selectAndCount(query);
		userManager.fill(query.getDatas());
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("OrderStatus", OrderStatus.Error);
	}

}
