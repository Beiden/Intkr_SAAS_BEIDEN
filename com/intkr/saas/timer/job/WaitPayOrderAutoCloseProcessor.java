package com.intkr.saas.timer.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.domain.bo.timer.TimerBO;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.manager.item.ItemSkuManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.timer.callback.Processor;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 为付款订单自动关闭功能
 * 
 * @author Beiden
 * @date 2016-6-3 上午10:45:38
 * @version 1.0
 */
public class WaitPayOrderAutoCloseProcessor extends Processor {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSkuManager itemSkuManager = IOC.get("ItemSkuManager");

	private OrderManager orderManager = IOC.get("OrderManager");

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	private MsgManager msgManager = IOC.get("MsgManager");

	public void process(TimerBO timer) {
		String orderId = timer.getCode();
		OrderBO order = orderManager.get(orderId);
		if (order == null || !OrderStatus.WaitPay.getCode().equals(order.getStatus())) {
			return;
		}
		orderDetailManager.fills(order);
		order.setStatus(OrderStatus.Cancel.getCode());
		order.setFeature("closeTime", DateUtil.formatDateTime(new Date()));
		orderManager.update(order);
		for (OrderDetailBO detail : order.getOrderDetails()) {
			itemSkuManager.increaseInventory(detail.getSkuId(), detail.getCount());
		}
		msgManager.sendSysMsg(order.getUserId(), "订单自动关闭提醒", "亲，您未在时限内支付您的订单" + order.getId() + "，交易已取消。有任何疑问请随时联系客服!");
	}

}
