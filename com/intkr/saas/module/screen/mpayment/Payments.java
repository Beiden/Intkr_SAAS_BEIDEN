package com.intkr.saas.module.screen.mpayment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.manager.order.DeliveryAddressManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-8-7 下午11:21:41
 * @version 1.0
 */
public class Payments {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderManager orderManager = IOC.get(OrderManager.class);

	private OrderDetailManager orderDetailManager = IOC.get(OrderDetailManager.class);

	private DeliveryAddressManager deliveryAddressManager = IOC.get(DeliveryAddressManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	/**
	 * 选择支付方式页面：必填参数orderId
	 * 
	 * @param request
	 * @param response
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String orderId = request.getParameter("orderId");
		OrderBO order = orderManager.get(orderId);
		orderDetailManager.fills(order);
		deliveryAddressManager.fill(order);
		UserBO user = SessionClient.getLoginUser(request);
		UserBO userBO = userManager.get(user.getId());
		request.setAttribute("user", userBO);
		request.setAttribute("order", order);
		request.setAttribute("title", "选择支付方式-小贝商城网");
	}

}
