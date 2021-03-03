package com.intkr.saas.module.screen.admin.shopping.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.type.order.PaymentType;
import com.intkr.saas.manager.order.PaymentManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 支付方式
 * 
 * @table shop_collect
 * 
 * @author Beiden
 * @date 2020-11-14 17:59:25
 * @version 1.0
 */
public class PaymentAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private PaymentManager manager = IOC.get(PaymentManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("PaymentType", PaymentType.Error);
		request.setAttribute("addId", manager.getId());
	}

}
