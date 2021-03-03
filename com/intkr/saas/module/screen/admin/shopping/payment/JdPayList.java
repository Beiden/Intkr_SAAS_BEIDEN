package com.intkr.saas.module.screen.admin.shopping.payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.distributed.session.SessionClientDistImpl;
import com.intkr.saas.manager.order.PaymentManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-13 下午10:34:29
 * @version 1.0
 */

public class JdPayList {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private PaymentManager paymentManager = IOC.get("PaymentManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("jdPayConf", paymentManager.getJdpay(SessionClientDistImpl.getSaas(request).getId()));
	}

}
