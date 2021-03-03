package com.intkr.saas.module.screen.admin.shopping.payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.order.PaymentBO;
import com.intkr.saas.manager.order.PaymentManager;
import com.intkr.saas.module.action.payment.PaymentAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-7-28 上午10:50:15
 * @version 1.0
 */
public class PaymentMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private PaymentManager paymentManager = IOC.get("PaymentManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		PaymentBO query = PaymentAction.getParameter(request);
		query = paymentManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
