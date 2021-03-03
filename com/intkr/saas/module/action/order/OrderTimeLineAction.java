package com.intkr.saas.module.action.order;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderTimeLineBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.order.OrderTimeLineManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.util.MoneyUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-10 上午10:36:23
 * @version 1.0
 */
public class OrderTimeLineAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderTimeLineManager orderTimeLineManager = IOC.get("OrderTimeLineManager");

	private OrderManager orderManager = IOC.get("OrderManager");

	private ShopManager shopManager = IOC.get("ShopManager");

	private MsgManager msgManager = IOC.get("MsgManager");

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (orderTimeLineManager.get(RequestUtil.getParam(request, "id")) == null) {
			OrderTimeLineBO orderTimeLineLogBO = new OrderTimeLineBO();
			orderTimeLineLogBO.setOrderId(RequestUtil.getParam(request, "orderId", Long.class));
			OrderBO order = orderManager.get(orderTimeLineLogBO.getOrderId());
			if (!order.getUserId().equals(SessionClient.getLoginUserId(request))) {
				msgManager.sendSysMsg(order.getUserId(), "订单时间轴变化提醒", "您好，你的订单：" + order.getId() + " 有新的动态，请查看！");
			}
			ShopBO shop = shopManager.get(order.getShopId());
			if (!shop.getUserId().equals(SessionClient.getLoginUserId(request))) {
				msgManager.sendSysMsg(shop.getUserId(), "订单时间轴变化提醒", "您好，你的订单：" + order.getId() + " 有新的动态，请查看！");
			}
			orderTimeLineLogBO.setUserId(SessionClient.getLoginUserId(request));
			orderTimeLineLogBO.setType(RequestUtil.getParam(request, "type"));
			if (RequestUtil.existParam(request, "long1")) {
				orderTimeLineLogBO.setLong1(MoneyUtil.parse(RequestUtil.getParam(request, "long1")));
			}
			orderTimeLineLogBO.setNote(RequestUtil.getParam(request, "note"));
			if (RequestUtil.existParam(request, "imgs")) {
				String[] imgIdsArray = request.getParameterValues("imgs");
				orderTimeLineLogBO.setFeature("imgs", imgIdsArray);
			}
			orderTimeLineLogBO.setTime(new Date());
			orderTimeLineManager.insert(orderTimeLineLogBO);
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "发布成功");
	}

}
