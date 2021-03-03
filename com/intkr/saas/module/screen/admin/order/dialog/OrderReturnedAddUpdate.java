package com.intkr.saas.module.screen.admin.order.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.order.OrderReturnedBO;
import com.intkr.saas.manager.order.OrderReturnedManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class OrderReturnedAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderReturnedManager manager = IOC.get("ShopOrderReturnedManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String shopOrderReturnedId = RequestUtil.getParam(request, "shopOrderReturnedId");
		OrderReturnedBO shopOrderReturned = manager.get(shopOrderReturnedId);
		request.setAttribute("shopOrderReturned", shopOrderReturned);
		request.setAttribute("addId", manager.getId());
	}

}
