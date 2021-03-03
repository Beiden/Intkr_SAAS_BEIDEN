package com.intkr.saas.module.action.buyer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.aop.StartTransaction;
import com.intkr.saas.client.TimerClient;
import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemSkuBO;
import com.intkr.saas.domain.bo.order.DeliveryAddressBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.domain.bo.order.OrderTimeLineBO;
import com.intkr.saas.domain.bo.shop.DeliveryFeeTemplateBO;
import com.intkr.saas.domain.bo.shop.ItemSellRecordBO;
import com.intkr.saas.domain.bo.shopping.ShoppingCartBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.domain.type.order.OrderTimeLineType;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.item.ItemSkuManager;
import com.intkr.saas.manager.order.DeliveryAddressManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.order.OrderTimeLineManager;
import com.intkr.saas.manager.shop.DeliveryFeeTemplateManager;
import com.intkr.saas.manager.shop.ItemSellRecordManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.manager.shopping.ShoppingCartManager;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.timer.job.WaitPayOrderAutoCloseProcessor;
import com.intkr.saas.util.DateUtil;
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

	private OrderDetailManager orderDetailManager = IOC.get(OrderDetailManager.class);

	private DeliveryFeeTemplateManager deliveryFeeTemplateManager = IOC.get(DeliveryFeeTemplateManager.class);

	private DeliveryAddressManager deliveryAddressManager = IOC.get(DeliveryAddressManager.class);

	private OrderTimeLineManager orderTimeLineManager = IOC.get("OrderTimeLineManager");

	private ItemSellRecordManager itemSellRecordManager = IOC.get("ShopItemSellRecordManager");

	private ItemSkuManager itemSkuManager = IOC.get("ItemSkuManager");

	private ShopManager shopManager = IOC.get("ShopManager");

	private MsgManager msgManager = IOC.get("MsgManager");

	private TimerClient timerClient = IOC.get("TimerClient");

	private UserManager userManager = IOC.get("UserManager");

	private ShoppingCartManager shopCartManager = IOC.get("ShoppingCartManager");

	private OptionManager optionManager = IOC.get("OptionManager");

	public void doQuery(HttpServletRequest request, HttpServletResponse response) {
		Long userId = SessionClient.getLoginUserId(request);
		OrderBO query = com.intkr.saas.module.action.order.OrderAction.getParameter(request);
		query.setUserId(userId);
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

	/**
	 * 获取订单基本信息
	 */
	public void doGetDetail(HttpServletRequest request, HttpServletResponse response) {
		OrderBO order = orderManager.get(request.getParameter("orderId"));
		orderDetailManager.fills(order);
		deliveryAddressManager.fill(order);
		for (OrderDetailBO orderDetail : order.getOrderDetails()) {
			itemManager.fill(orderDetail);
		}
		request.setAttribute("data", order);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功！");
	}

	/**
	 * 下单
	 */
	public void doCreateTmpOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] skuIds = request.getParameterValues("skuIds");
		if (skuIds == null || skuIds.length == 0) {
			skuIds = request.getParameterValues("skuIds[]");
		}
		if ((skuIds == null || skuIds.length == 0) && RequestUtil.existParam(request, "skuId")) {
			skuIds = new String[1];
			skuIds[0] = request.getParameter("skuId");
		}
		String[] counts = request.getParameterValues("counts");
		if (counts == null || counts.length == 0) {
			counts = request.getParameterValues("counts[]");
		}
		if ((counts == null || counts.length == 0) && RequestUtil.existParam(request, "count")) {
			counts = new String[1];
			counts[0] = request.getParameter("count");
		}
		if (counts.length != skuIds.length) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "订单异常！");
		}
		request.getSession().setAttribute("source", RequestUtil.getParam(request, "source"));
		request.getSession().setAttribute("tmpOrderId", orderManager.getId());
		request.getSession().setAttribute("skuIds", skuIds);
		request.getSession().setAttribute("counts", counts);
		request.setAttribute("result", true);
		request.setAttribute("msg", "生成临时订单成功！");
	}

	/**
	 * 确认下单
	 */
	@StartTransaction
	public void doOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long orderId = RequestUtil.getParam(request, "orderId", Long.class);
		if (orderManager.get(orderId) != null) {// 防重复下单
			RequestUtil.setParam(request, "orderId", RequestUtil.getParam(request, "orderId"));
			request.setAttribute("msg", "提交订单成功！");
			request.setAttribute("result", true);
			return;
		}

		OrderBO order = new OrderBO();
		order.setId(orderId);
		order.setSaasId(SessionClient.getSaasId(request));
		order.setUserId(SessionClient.getLoginUserId(request));
		order.setStatus(OrderStatus.WaitPay.getCode());
		Long deliveryAddressId = RequestUtil.getParam(request, "deliveryAddressId", Long.class);
		DeliveryAddressBO address = deliveryAddressManager.get(deliveryAddressId);
		Map<String, Object> addressMap = new HashMap<String, Object>();
		addressMap.put("id", address.getId());
		addressMap.put("userId", address.getUserId());
		addressMap.put("province", address.getProvince());
		addressMap.put("city", address.getCity());
		addressMap.put("area", address.getArea());
		addressMap.put("detail", address.getDetail());
		addressMap.put("postalCode", address.getPostalCode());
		addressMap.put("consignee", address.getConsignee());
		addressMap.put("tel", address.getTel());
		order.setFeature("address", addressMap);
		order.setDeliAddrId(deliveryAddressId);
		order.setNote(RequestUtil.getParam(request, "note"));

		String[] skuIds = request.getParameterValues("skuIds");
		String[] counts = request.getParameterValues("counts");
		for (int i = 0; i < skuIds.length; i++) {
			ItemSkuBO sku = itemSkuManager.get(Long.valueOf(skuIds[i]));
			ItemBO item = itemManager.get(sku.getItemId());

			OrderDetailBO orderDetail = new OrderDetailBO();
			orderDetail.setProperty("item", item);
			orderDetail.setProperty("sku", sku);
			orderDetail.setId(orderDetailManager.getId());
			orderDetail.setSaasId(SessionClient.getSaas(request).getId());
			orderDetail.setIsEvaluate(2);
			orderDetail.setOrderId(order.getId());
			orderDetail.setItemId(sku.getItemId());
			orderDetail.setSkuId(sku.getId());
			orderDetail.setCount(Integer.valueOf(counts[i]));
			orderDetail.setUnitPrice(sku.getPrice());
			orderDetail.setPrice(orderDetail.getUnitPrice() * orderDetail.getCount());
			orderDetail.setShopId(item.getShopId());
			order.addOrderDetail(orderDetail);
		}
		caculatePrice(order);
		for (OrderDetailBO detail : order.getOrderDetails()) {
			orderDetailManager.insert(detail);
			boolean result = itemSkuManager.decreaseInventory(detail.getSkuId(), detail.getCount());
			if (!result) {
				request.setAttribute("msg", "下单失败，库存不足！");
				request.setAttribute("result", false);
				for (OrderDetailBO detail2 : order.getOrderDetails()) {
					if (detail2.getProperty("decreaseInventory") != null) {
						itemSkuManager.increaseInventory(detail2.getSkuId(), detail2.getCount());
					}
				}
				return;
			} else {
				detail.setProperty("decreaseInventory", true);
			}
		}
		orderManager.insert(order);

		clearShopCart(request, skuIds);

		setWaitPayOrderAutoCloseTimer(SessionClient.getSaas(request).getId(), orderId);
		{// 更新订单时间轴
			OrderTimeLineBO orderTimeLine = new OrderTimeLineBO();
			orderTimeLine.setId(orderTimeLineManager.getId());
			orderTimeLine.setSaasId(SessionClient.getSaasId(request));
			orderTimeLine.setType(OrderTimeLineType.Order.getName());
			orderTimeLine.setTime(new Date());
			orderTimeLine.setOrderId(orderId);
			orderTimeLine.setUserId(SessionClient.getLoginUserId(request));
			orderTimeLineManager.insert(orderTimeLine);
		}
		userManager.fill(order);
		msgManager.sendSysMsg(order.getUserId(), "提交订单提醒", "亲，您好，（" + order.getUser().getNickName() + "）对您发布的商品（" + order.getName() + "）提交了订单，请您关注。有任何疑问请随时联系您的专属客服");
		msgManager.sendSaasMsg(SessionClient.getSaasId(request), "新订单提醒", "商品（" + order.getName() + "）有新订单[" + order.getId() + "]！");

		RequestUtil.setParam(request, "orderId", RequestUtil.getParam(request, "orderId"));
		request.setAttribute("msg", "提交订单成功！");
		request.setAttribute("result", true);
	}

	// 计算价格
	private void caculatePrice(OrderBO order) {
		List<Long> templateIds = new ArrayList<Long>();
		Integer allCount = 0;
		Long allPrice = 0l;
		for (OrderDetailBO detail : order.getOrderDetails()) {
			ItemBO item = (ItemBO) detail.getProperty("item");
			if (IdEngine.isValidate(item.getDeliveryFeeId())) {
				templateIds.add(item.getDeliveryFeeId());
			}
			allCount += detail.getCount();
			allPrice += detail.getPrice();
		}
		List<DeliveryFeeTemplateBO> templates = select(templateIds);
		Double deliveryFee = 0d;
		for (DeliveryFeeTemplateBO detail : templates) {
			Double price = detail.caculate(allCount);
			if (price > deliveryFee) {
				deliveryFee = price;
			}
		}
		deliveryFee = deliveryFee * 100;
		order.setDeliveryFee(deliveryFee.longValue());
		allPrice += deliveryFee.longValue();
		order.setPrice(allPrice);
	}

	private List<DeliveryFeeTemplateBO> select(List<Long> templateIds) {
		if (templateIds == null || templateIds.isEmpty()) {
			return new ArrayList<DeliveryFeeTemplateBO>();
		}
		DeliveryFeeTemplateBO query = new DeliveryFeeTemplateBO();
		query.setQuery("ids", templateIds);
		List<DeliveryFeeTemplateBO> templates = deliveryFeeTemplateManager.select(query);
		return templates;
	}

	private void clearShopCart(HttpServletRequest request, String[] skuIds) {
		if ("shopCart".equals(request.getSession().getAttribute("source"))) {
			for (String skuId : skuIds) {
				ShoppingCartBO query = new ShoppingCartBO();
				query.setUserId(SessionClient.getLoginUserId(request));
				query.setSkuId(Long.valueOf(skuId));
				ShoppingCartBO cart = shopCartManager.selectOne(query);
				shopCartManager.delete(cart.getId());
			}
		}
	}

	private void setWaitPayOrderAutoCloseTimer(Long saasId, Long orderId) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, optionManager.getWaitPayOrderAutoCloseTime(saasId));
		Date time = calendar.getTime();
		String action = WaitPayOrderAutoCloseProcessor.class.getName();
		timerClient.schedule(time, action, orderId + "");
	}

	/**
	 * 取消订单
	 */
	@StartTransaction
	public void doCancel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long orderId = RequestUtil.getParam(request, "orderId", Long.class);
		Long saasId = SessionClient.getSaasId(request);
		OrderBO order = orderManager.get(orderId);
		if (!OrderStatus.WaitPay.getCode().equals(order.getStatus())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "订单状态异常");
			return;
		}
		orderDetailManager.fills(order);
		order.setStatus(OrderStatus.Cancel.getCode());
		order.setFeature("closeTime", DateUtil.formatDateTime(new Date()));
		for (OrderDetailBO detail : order.getOrderDetails()) {
			itemSkuManager.increaseInventory(detail.getSkuId(), detail.getCount());
		}
		{// 更新订单时间轴
			OrderTimeLineBO orderTimeLine = new OrderTimeLineBO();
			orderTimeLine.setId(orderTimeLineManager.getId());
			orderTimeLine.setSaasId(SessionClient.getSaasId(request));
			orderTimeLine.setType(OrderTimeLineType.Cancel.getName());
			orderTimeLine.setTime(new Date());
			orderTimeLine.setOrderId(orderId);
			orderTimeLine.setUserId(SessionClient.getLoginUserId(request));
			orderTimeLineManager.insert(orderTimeLine);
		}
		msgManager.sendSaasMsg(saasId, "订单取消提醒", "订单（" + order.getName() + "）有新订单取消[" + order.getId() + "]！");
		orderManager.update(order);
		request.setAttribute("result", true);
		request.setAttribute("msg", "订单取消成功!");
	}

	/**
	 * 删除订单
	 */
	@StartTransaction
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long orderId = RequestUtil.getParam(request, "orderId", Long.class);
		Long saasId = SessionClient.getSaasId(request);
		OrderBO order = orderManager.get(orderId);
		{// 更新订单时间轴
			OrderTimeLineBO orderTimeLine = new OrderTimeLineBO();
			orderTimeLine.setId(orderTimeLineManager.getId());
			orderTimeLine.setSaasId(SessionClient.getSaasId(request));
			orderTimeLine.setType(OrderTimeLineType.Delete.getName());
			orderTimeLine.setTime(new Date());
			orderTimeLine.setOrderId(orderId);
			orderTimeLine.setUserId(SessionClient.getLoginUserId(request));
			orderTimeLineManager.insert(orderTimeLine);
		}
		msgManager.sendSaasMsg(saasId, "订单删除提醒", "订单（" + order.getName() + "）有新订单删除[" + order.getId() + "]！");
		request.setAttribute("result", true);
		request.setAttribute("msg", "订单删除成功!");
		orderManager.delete(order.getId());
	}

	/**
	 * 订单确认收货
	 */
	@StartTransaction
	public void doConfirmReceipt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long orderId = RequestUtil.getParam(request, "orderId", Long.class);
		Long saasId = SessionClient.getSaasId(request);
		OrderBO order = orderManager.get(orderId);
		if (order == null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "订单不存在");
			return;
		}
		UserBO userBO = SessionClient.getLoginUser(request);
		if (!userBO.getId().equals(order.getUserId())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "订单不存在");
			return;
		}
		if (!OrderStatus.WaitReceipt.getCode().equals(order.getStatus())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "订单状态异常");
			return;
		}
		order.setStatus(OrderStatus.WaitComment.getCode());
		order.setConfirmReceiptTime(new Date());
		orderManager.update(order);
		orderDetailManager.fills(order);
		for (OrderDetailBO orderDetail : order.getOrderDetails()) {
			ItemSellRecordBO itemSellRecord = new ItemSellRecordBO();
			itemSellRecord.setId(itemSellRecordManager.getId());
			itemSellRecord.setSaasId(SessionClient.getSaasId(request));
			itemSellRecord.setUserId(order.getUserId());
			ItemSkuBO sku = itemSkuManager.get(orderDetail.getSkuId());
			itemSellRecord.setItemId(sku.getItemId());
			itemSellRecord.setPrice(orderDetail.getUnitPrice());
			itemSellRecord.setCount(orderDetail.getCount());
			itemSellRecord.setBuyTime(new Date());
			itemSellRecord.setOrderId(order.getId());
			itemSellRecord.setOrderDetailId(orderDetail.getId());
			itemSellRecordManager.insert(itemSellRecord);
		}

		{// 更新订单时间轴
			OrderTimeLineBO orderTimeLine = new OrderTimeLineBO();
			orderTimeLine.setId(orderTimeLineManager.getId());
			orderTimeLine.setSaasId(SessionClient.getSaasId(request));
			orderTimeLine.setType(OrderTimeLineType.Receipt.getName());
			orderTimeLine.setTime(new Date());
			orderTimeLine.setOrderId(orderId);
			orderTimeLine.setUserId(SessionClient.getLoginUserId(request));
			orderTimeLineManager.insert(orderTimeLine);
		}
		shopManager.fill(order);
		msgManager.sendSysMsg(order.getShop().getUserId(), "订单确认收货提醒", "亲，您好，您的订单【" + order.getId() + "】已经已确认收货，请您关注。有任何疑问请随时联系您的专属客服");
		msgManager.sendSaasMsg(saasId, "订单确认收货提醒", "订单【" + order.getId() + "】已经已确认收货，请您关注。");
		request.setAttribute("result", true);
		request.setAttribute("msg", "确认收货成功！");
	}

	/**
	 * 修改备注
	 */
	@StartTransaction
	public void doChangeMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderId = RequestUtil.getParam(request, "orderId");
		String msg = RequestUtil.getParam(request, "msg");
		OrderBO orderBO = orderManager.get(orderId);
		if (orderBO == null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "订单不存在");
			return;
		}
		UserBO userBO = SessionClient.getLoginUser(request);
		if (!userBO.getId().equals(orderBO.getUserId())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "订单不存在");
			return;
		}
		orderBO.setNote(msg);
		orderManager.update(orderBO);
		request.setAttribute("result", true);
		request.setAttribute("msg", "修改成功");
	}

}
