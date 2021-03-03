package com.intkr.saas.module.action.seller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.aop.StartTransaction;
import com.intkr.saas.client.TimerClient;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.fs.MediaBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.domain.bo.order.OrderTimeLineBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.domain.type.order.OrderTimeLineType;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.fs.MediaManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.order.DeliveryAddressManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.order.OrderTimeLineManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.timer.job.WaitReceiptOrderAutoReceiptProcessor;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-4-27 下午3:09:44
 * @version 1.0
 */
public class OrderAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemManager itemManager = IOC.get(ItemManager.class);

	private OrderManager orderManager = IOC.get(OrderManager.class);

	private ShopManager shopManager = IOC.get(ShopManager.class);

	private OrderDetailManager orderDetailManager = IOC.get(OrderDetailManager.class);

	private DeliveryAddressManager deliveryAddressManager = IOC.get(DeliveryAddressManager.class);

	private OrderTimeLineManager orderTimeLineManager = IOC.get("OrderTimeLineManager");

	private MsgManager msgManager = IOC.get("MsgManager");

	private TimerClient timerClient = IOC.get("TimerClient");

	private OptionManager optionManager = IOC.get("OptionManager");

	private MediaManager mediaManager = IOC.get("MediaManager");

	public void doQuery(HttpServletRequest request, HttpServletResponse response) {
		Long userId = SessionClient.getLoginUserId(request);
		ShopBO shop = shopManager.getByUserId(userId);
		OrderBO query = com.intkr.saas.module.action.order.OrderAction.getParameter(request);
		query.setShopId(shop.getId());
		orderManager.selectAndCount(query);
		List<OrderBO> orderList = query.getDatas();
		orderDetailManager.fills(orderList);
		for (OrderBO order : orderList) {
			for (OrderDetailBO orderDetail : order.getOrderDetails()) {
				itemManager.fill(orderDetail);
			}
		}
		request.setAttribute("data", query);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功！");
	}

	public void doGetDetail(HttpServletRequest request, HttpServletResponse response) {
		OrderBO order = orderManager.get(request.getParameter("orderId"));
		if (!checkAuth(request, order)) {
			RequestUtil.forward(request, "/_common/page/noRight.html");
			return;
		}
		orderDetailManager.fills(order);
		deliveryAddressManager.fill(order);
		for (OrderDetailBO orderDetail : order.getOrderDetails()) {
			itemManager.fill(orderDetail);
		}
		request.setAttribute("order", order);
	}

	private boolean checkAuth(HttpServletRequest request, OrderBO order) {
		if (order == null) {
			return false;
		}
		Long userId = SessionClient.getLoginUserId(request);
		ShopBO shop = shopManager.getByUserId(userId);
		if (shop.getId().equals(order.getShopId())) {
			return true;
		}
		return false;
	}

	/**
	 * 发货
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@StartTransaction
	public void doSendOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long orderId = RequestUtil.getParam(request, "orderId", Long.class);
		OrderBO order = orderManager.get(orderId);
		if (order == null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "订单不存在");
			return;
		}
		if (!OrderStatus.WaitSendOut.getCode().equals(order.getStatus())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "订单状态异常");
			return;
		}
		order.setStatus(OrderStatus.WaitReceipt.getCode());
		order.setSendOutTime(new Date());
		orderManager.update(order);

		{// 更新订单时间轴
			OrderTimeLineBO orderTimeLine = new OrderTimeLineBO();
			orderTimeLine.setId(orderTimeLineManager.getId());
			orderTimeLine.setSaasId(SessionClient.getSaasId(request));
			orderTimeLine.setType(OrderTimeLineType.SendOut.getName());
			orderTimeLine.setTime(new Date());
			orderTimeLine.setOrderId(orderId);
			orderTimeLine.setUserId(SessionClient.getLoginUserId(request));
			if (RequestUtil.existParam(request, "sendOutImgId")) {
				String sendOutImgId = RequestUtil.getParam(request, "sendOutImgId");
				orderTimeLine.setFeature("imgId", sendOutImgId);
				MediaBO media = mediaManager.get(sendOutImgId);
				orderTimeLine.setFeature("imgUrl", media.getUri());
			}
			orderTimeLineManager.insert(orderTimeLine);
		}

		setWaitReceiptOrderAutoReceiptTimer(SessionClient.getSaas(request).getId(), orderId);
		msgManager.sendSysMsg(order.getUserId(), "订单发货提醒", "亲，您好，您的订单【" + order.getId() + "】已经发货，请您关注。有任何疑问请随时联系您的专属客服");
		request.setAttribute("result", true);
		request.setAttribute("msg", "发货成功!");
	}

	private void setWaitReceiptOrderAutoReceiptTimer(Long saasId, Long orderId) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, optionManager.getWaitPayOrderAutoCloseTime(saasId));
		Date time = calendar.getTime();
		String action = WaitReceiptOrderAutoReceiptProcessor.class.getName();
		timerClient.schedule(time, action, orderId + "");
	}

}
