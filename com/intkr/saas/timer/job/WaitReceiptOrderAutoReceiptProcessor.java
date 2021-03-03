package com.intkr.saas.timer.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.timer.TimerBO;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.timer.callback.Processor;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @author Beiden
 * @date 2016-6-4 下午1:59:43
 * @version 1.0
 */
public class WaitReceiptOrderAutoReceiptProcessor extends Processor {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderManager orderManager = IOC.get("OrderManager");

	public void process(TimerBO timer) {
		String orderId = timer.getCode();
		OrderBO order = orderManager.get(orderId);
		if (order == null || !OrderStatus.WaitReceipt.getCode().equals(order.getStatus())) {
			return;
		}
		order.setStatus(OrderStatus.WaitComment.getCode());
		orderManager.update(order);
	}

}
