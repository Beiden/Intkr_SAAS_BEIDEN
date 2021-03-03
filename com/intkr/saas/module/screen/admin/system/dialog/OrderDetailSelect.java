package com.intkr.saas.module.screen.admin.system.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.order.OrderDetailAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-23 下午3:03:29
 * @version 1.0
 */
public class OrderDetailSelect {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	private UserManager userManager = IOC.get("UserManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		OrderDetailBO query = OrderDetailAction.getParameter(request);
		query = orderDetailManager.selectAndCount(query);
		query.setDatas(userManager.fill(query.getDatas()));
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}