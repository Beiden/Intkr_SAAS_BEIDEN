package com.intkr.saas.module.screen.admin.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.order.OrderEvaluateAction;
import com.intkr.saas.domain.bo.order.OrderEvaluateBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.order.OrderEvaluateManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.util.claz.IOC;

/**
 * 订单评价
 * 
 * @table order_evaluate
 * 
 * @author Beiden
 * @date 2020-11-14 17:02:55
 * @version 1.0
 */
public class OrderEvaluateMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderEvaluateManager manager = IOC.get(OrderEvaluateManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		OrderEvaluateBO query = OrderEvaluateAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
