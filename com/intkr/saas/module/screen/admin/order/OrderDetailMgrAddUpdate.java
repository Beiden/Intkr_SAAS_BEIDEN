package com.intkr.saas.module.screen.admin.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-7-28 上午8:07:07
 * @version 1.0
 */
public class OrderDetailMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			OrderDetailBO orderDetailBO = orderDetailManager.get(id);
			request.setAttribute("dto", orderDetailBO);
		} else {
			request.setAttribute("addId", orderDetailManager.getId());
		}
	}

}
