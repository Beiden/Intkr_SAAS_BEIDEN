package com.intkr.saas.module.screen.admin.shopping.payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.order.PaymentBO;
import com.intkr.saas.manager.order.PaymentManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-13 下午8:00:23
 * @version 1.0
 */
public class JdPayConf {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private PaymentManager paymentManager = IOC.get("PaymentManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			PaymentBO paymentBO = paymentManager.get(id);
			request.setAttribute("dto", paymentBO);
		} else {
			request.setAttribute("addId", paymentManager.getId());
		}
	}

}
