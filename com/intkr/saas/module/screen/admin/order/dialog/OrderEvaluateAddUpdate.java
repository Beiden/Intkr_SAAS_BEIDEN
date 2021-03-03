package com.intkr.saas.module.screen.admin.order.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.order.OrderEvaluateBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.order.OrderEvaluateManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 订单评价
 * 
 * @table order_evaluate
 * 
 * @author Beiden
 * @date 2020-11-14 17:02:56
 * @version 1.0
 */
public class OrderEvaluateAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderEvaluateManager manager = IOC.get(OrderEvaluateManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String orderEvaluateId = RequestUtil.getParam(request, "orderEvaluateId");
		OrderEvaluateBO orderEvaluate = manager.get(orderEvaluateId);
		request.setAttribute("orderEvaluate", orderEvaluate);
		request.setAttribute("addId", manager.getId());
	}

}
