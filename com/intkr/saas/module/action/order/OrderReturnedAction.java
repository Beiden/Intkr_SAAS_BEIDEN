package com.intkr.saas.module.action.order;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderReturnedBO;
import com.intkr.saas.domain.bo.order.OrderTimeLineBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.domain.type.order.OrderTimeLineType;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.order.OrderReturnedManager;
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
public class OrderReturnedAction extends BaseAction<OrderReturnedBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderReturnedManager shopOrderReturnedManager = IOC.get("ShopOrderReturnedManager");

	private OrderManager orderManager = IOC.get("OrderManager");

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	private OrderTimeLineManager orderTimeLineManager = IOC.get("OrderTimeLineManager");

	public OrderReturnedBO getBO(HttpServletRequest request) {
		OrderReturnedBO shopOrderReturnedBO = getParameter(request);
		return shopOrderReturnedBO;
	}

	public static OrderReturnedBO getParameter(HttpServletRequest request) {
		OrderReturnedBO shopOrderReturnedBO = new OrderReturnedBO();
		shopOrderReturnedBO.setSaasId(SessionClient.getSaas(request).getId());
		shopOrderReturnedBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		shopOrderReturnedBO.setOrderId(RequestUtil.getParam(request, "orderId", Long.class));
		shopOrderReturnedBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		shopOrderReturnedBO.setType(RequestUtil.getParam(request, "type"));
		shopOrderReturnedBO.setStatus(RequestUtil.getParam(request, "status"));
		shopOrderReturnedBO.setContent(RequestUtil.getParam(request, "content"));
		shopOrderReturnedBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, shopOrderReturnedBO);
		return shopOrderReturnedBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return shopOrderReturnedManager;
	}

	public void doApply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrderBO order = orderManager.get(RequestUtil.getParam(request, "orderId"));
		Long orderId = order.getId();
		UserBO sessionUser = SessionClient.getLoginUser(request);
		if (!sessionUser.getId().equals(order.getUserId())) {
			request.setAttribute("msg", "你没有购买这个商品。");
			request.setAttribute("result", false);
			return;
		}
		if (!OrderStatus.WaitReceipt.getCode().equals(order.getStatus()) && //
				!OrderStatus.WaitComment.getCode().equals(order.getStatus()) && //
				!OrderStatus.Finished.getCode().equals(order.getStatus())) {
			request.setAttribute("msg", "订单状态异常");
			request.setAttribute("result", false);
			return;
		}
		OrderReturnedBO evaluate = new OrderReturnedBO();
		evaluate.setId(shopOrderReturnedManager.getId());
		evaluate.setSaasId(SessionClient.getSaasId(request));
		evaluate.setUserId(sessionUser.getId());
		evaluate.setShopId(order.getShopId());
		evaluate.setOrderId(order.getId());
		evaluate.setContent(RequestUtil.getParam(request, "content"));
		shopOrderReturnedManager.insert(evaluate);

		{// 更新订单时间轴
			OrderTimeLineBO orderTimeLine = new OrderTimeLineBO();
			orderTimeLine.setId(orderTimeLineManager.getId());
			orderTimeLine.setSaasId(SessionClient.getSaasId(request));
			orderTimeLine.setType(OrderTimeLineType.Returned.getName());
			orderTimeLine.setTime(new Date());
			orderTimeLine.setOrderId(orderId);
			orderTimeLine.setUserId(SessionClient.getLoginUserId(request));
			orderTimeLineManager.insert(orderTimeLine);
		}

		request.setAttribute("msg", "退换申请成功!");
		request.setAttribute("result", true);
	}

}
