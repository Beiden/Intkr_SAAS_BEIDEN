package com.intkr.saas.module.action.order;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemSkuBO;
import com.intkr.saas.domain.bo.order.ItemCustServBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.domain.bo.order.OrderTimeLineBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.domain.type.order.OrderTimeLineType;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemSkuManager;
import com.intkr.saas.manager.order.ItemCustServManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.order.OrderTimeLineManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class ItemCustServAction extends BaseAction<ItemCustServBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemCustServManager shopItemCustServManager = IOC.get("ShopItemCustServManager");

	private OrderManager orderManager = IOC.get("OrderManager");

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	private OrderTimeLineManager orderTimeLineManager = IOC.get("OrderTimeLineManager");

	private ItemSkuManager itemSkuManager = IOC.get("ItemSkuManager");

	public ItemCustServBO getBO(HttpServletRequest request) {
		ItemCustServBO shopItemCustServBO = getParameter(request);
		return shopItemCustServBO;
	}

	public static ItemCustServBO getParameter(HttpServletRequest request) {
		ItemCustServBO shopItemCustServBO = new ItemCustServBO();
		shopItemCustServBO.setSaasId(SessionClient.getSaas(request).getId());
		shopItemCustServBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		shopItemCustServBO.setOrderId(RequestUtil.getParam(request, "orderId", Long.class));
		shopItemCustServBO.setOrderDetailId(RequestUtil.getParam(request, "orderDetailId", Long.class));
		shopItemCustServBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		shopItemCustServBO.setItemId(RequestUtil.getParam(request, "itemId", Long.class));
		shopItemCustServBO.setStatus(RequestUtil.getParam(request, "status"));
		shopItemCustServBO.setContent(RequestUtil.getParam(request, "content"));
		shopItemCustServBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, shopItemCustServBO);
		return shopItemCustServBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return shopItemCustServManager;
	}

	public void doApply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrderDetailBO orderDetail = orderDetailManager.get(RequestUtil.getParam(request, "orderDetailId"));
		OrderBO order = orderManager.get(orderDetail.getOrderId());
		Long orderId = order.getId();
		UserBO sessionUser = SessionClient.getLoginUser(request);
		if (!sessionUser.getId().equals(order.getUserId())) {
			request.setAttribute("msg", "你没有购买这个商品。");
			request.setAttribute("result", false);
			return;
		}
		if (!OrderStatus.Finished.getCode().equals(order.getStatus())) {
			request.setAttribute("msg", "订单状态异常");
			request.setAttribute("result", false);
			return;
		}
		ItemCustServBO service = new ItemCustServBO();
		service.setId(shopItemCustServManager.getId());
		service.setSaasId(SessionClient.getSaasId(request));
		service.setUserId(sessionUser.getId());
		service.setOrderDetailId(RequestUtil.getParam(request, "orderDetailId", Long.class));
		service.setShopId(orderDetail.getShopId());
		ItemSkuBO sku = itemSkuManager.get(orderDetail.getSkuId());
		service.setItemId(sku.getItemId());
		service.setOrderId(order.getId());
		service.setContent(RequestUtil.getParam(request, "content"));
		shopItemCustServManager.insert(service);

		{// 更新订单时间轴
			OrderTimeLineBO orderTimeLine = new OrderTimeLineBO();
			orderTimeLine.setSaasId(SessionClient.getSaasId(request));
			orderTimeLine.setId(orderTimeLineManager.getId());
			orderTimeLine.setType(OrderTimeLineType.CustServ.getName());
			orderTimeLine.setTime(new Date());
			orderTimeLine.setOrderId(orderId);
			orderTimeLine.setUserId(SessionClient.getLoginUserId(request));
			orderTimeLineManager.insert(orderTimeLine);
		}

		request.setAttribute("msg", "申请成功!");
		request.setAttribute("result", true);
	}

}
