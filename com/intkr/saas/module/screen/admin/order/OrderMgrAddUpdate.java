package com.intkr.saas.module.screen.admin.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2015-4-17 下午11:41:26
 * @version 1.0
 */
public class OrderMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderManager orderManager = IOC.get("OrderManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			OrderBO orderBO = orderManager.get(id);
			request.setAttribute("dto", orderBO);
		} else {
			request.setAttribute("addId", orderManager.getId());
		}
	}

}
