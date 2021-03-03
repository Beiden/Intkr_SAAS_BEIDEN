package com.intkr.saas.module.action.order;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemSkuBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.domain.bo.order.OrderEvaluateBO;
import com.intkr.saas.domain.bo.order.OrderTimeLineBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.domain.type.order.OrderTimeLineType;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemSkuManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.order.OrderEvaluateManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.order.OrderTimeLineManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-21 下午1:10:51
 * @version 1.0
 */
public class OrderEvaluateAction extends BaseAction<OrderEvaluateBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderManager orderManager = IOC.get("OrderManager");

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	private OrderEvaluateManager tradeEvaluateManager = IOC.get("OrderItemEvaluateManager");

	private OrderTimeLineManager orderTimeLineManager = IOC.get("OrderTimeLineManager");

	private ItemSkuManager itemSkuManager = IOC.get("ItemSkuManager");

	public OrderEvaluateBO getBO(HttpServletRequest request) {
		OrderEvaluateBO shopOrderItemEvaluateBO = getParameter(request);
		return shopOrderItemEvaluateBO;
	}

	public static OrderEvaluateBO getParameter(HttpServletRequest request) {
		OrderEvaluateBO shopOrderItemEvaluateBO = new OrderEvaluateBO();
		shopOrderItemEvaluateBO.setSaasId(SessionClient.getSaas(request).getId());
		shopOrderItemEvaluateBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		shopOrderItemEvaluateBO.setOrderId(RequestUtil.getParam(request, "orderId", Long.class));
		shopOrderItemEvaluateBO.setOrderDetailId(RequestUtil.getParam(request, "orderDetailId", Long.class));
		shopOrderItemEvaluateBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		shopOrderItemEvaluateBO.setItemId(RequestUtil.getParam(request, "itemId", Long.class));
		shopOrderItemEvaluateBO.setEvaluate(RequestUtil.getParam(request, "evaluate", Integer.class));
		shopOrderItemEvaluateBO.setDescribeMatchGrade(RequestUtil.getParam(request, "describeMatchGrade", Double.class));
		shopOrderItemEvaluateBO.setServeAttitudeGrade(RequestUtil.getParam(request, "serveAttitudeGrade", Double.class));
		shopOrderItemEvaluateBO.setDeliverySpeedGrade(RequestUtil.getParam(request, "deliverySpeedGrade", Double.class));
		shopOrderItemEvaluateBO.setContent(RequestUtil.getParam(request, "content"));
		shopOrderItemEvaluateBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, shopOrderItemEvaluateBO);
		return shopOrderItemEvaluateBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return tradeEvaluateManager;
	}

	/**
	 * 评价
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrderDetailBO orderDetail = orderDetailManager.get(RequestUtil.getParam(request, "orderDetailId"));
		if (orderDetail.getIsEvaluate() == 1) {
			request.setAttribute("msg", "您已经评论过了。");
			request.setAttribute("result", false);
			return;
		}
		OrderBO order = orderManager.get(orderDetail.getOrderId());
		Long orderId = order.getId();
		UserBO sessionUser = SessionClient.getLoginUser(request);
		if (!sessionUser.getId().equals(order.getUserId())) {
			request.setAttribute("msg", "你没有购买这个商品。");
			request.setAttribute("result", false);
			return;
		}
		if (!OrderStatus.WaitComment.getCode().equals(order.getStatus())) {
			request.setAttribute("msg", "订单状态异常");
			request.setAttribute("result", false);
			return;
		}
		OrderEvaluateBO evaluate = new OrderEvaluateBO();
		evaluate.setId(tradeEvaluateManager.getId());
		evaluate.setSaasId(SessionClient.getSaasId(request));
		evaluate.setUserId(sessionUser.getId());
		evaluate.setOrderDetailId(RequestUtil.getParam(request, "orderDetailId", Long.class));
		evaluate.setShopId(orderDetail.getShopId());
		ItemSkuBO sku = itemSkuManager.get(orderDetail.getSkuId());
		evaluate.setItemId(sku.getItemId());
		evaluate.setOrderId(order.getId());
		evaluate.setEvaluate(RequestUtil.getParam(request, "evaluate", Integer.class, 2));
		evaluate.setDescribeMatchGrade(RequestUtil.getParam(request, "describeMatchGrade", Double.class, 5D));
		evaluate.setServeAttitudeGrade(RequestUtil.getParam(request, "serveAttitudeGrade", Double.class, 5D));
		evaluate.setDeliverySpeedGrade(RequestUtil.getParam(request, "deliverySpeedGrade", Double.class, 5D));
		evaluate.setContent(RequestUtil.getParam(request, "content"));
		tradeEvaluateManager.insert(evaluate);

		orderDetail.setIsEvaluate(1);
		orderDetailManager.update(orderDetail);

		OrderDetailBO query = new OrderDetailBO();
		query.setOrderId(order.getId());
		query.setIsEvaluate(2);
		if (!orderDetailManager.exist(query)) {
			order.setStatus(OrderStatus.Finished.getCode());
			order.setCommentTime(new Date());
			orderManager.update(order);
		}

		{// 更新订单时间轴
			OrderTimeLineBO orderTimeLine = new OrderTimeLineBO();
			orderTimeLine.setId(orderTimeLineManager.getId());
			orderTimeLine.setSaasId(SessionClient.getSaasId(request));
			orderTimeLine.setType(OrderTimeLineType.Comment.getName());
			orderTimeLine.setTime(new Date());
			orderTimeLine.setOrderId(orderId);
			orderTimeLine.setUserId(SessionClient.getLoginUserId(request));
			orderTimeLineManager.insert(orderTimeLine);
		}

		request.setAttribute("msg", "评价成功");
		request.setAttribute("result", true);
	}

}
