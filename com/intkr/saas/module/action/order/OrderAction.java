package com.intkr.saas.module.action.order;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.distributed.session.SessionClientDistImpl;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.MoneyUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-4-27 下午3:09:44
 * @version 1.0
 */
public class OrderAction extends BaseAction<OrderBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderManager orderManager = IOC.get("OrderManager");

	public OrderBO getBO(HttpServletRequest request) {
		OrderBO orderBO = getParameter(request);
		return orderBO;
	}

	public static OrderBO getParameter(HttpServletRequest request) {
		OrderBO orderBO = new OrderBO();
		orderBO.setId(RequestUtil.getParam(request, "id", Long.class));
		orderBO.setPid(RequestUtil.getParam(request, "pid", Long.class));
		orderBO.setSaasId(SessionClientDistImpl.getSaas(request).getId());
		orderBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		orderBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		orderBO.setName(RequestUtil.getParam(request, "name"));
		orderBO.setStatus(RequestUtil.getParam(request, "status"));
		if (RequestUtil.existParam(request, "deliveryFee")) {
			orderBO.setDeliveryFee(MoneyUtil.parse(RequestUtil.getParam(request, "deliveryFee")));
		}
		if (RequestUtil.existParam(request, "price")) {
			orderBO.setPrice(MoneyUtil.parse(RequestUtil.getParam(request, "price")));
		}
		orderBO.setPayment(RequestUtil.getParam(request, "payment"));
		orderBO.setDeliAddrId(RequestUtil.getParam(request, "deliAddrId", Long.class));
		orderBO.setNote(RequestUtil.getParam(request, "note"));
		orderBO.setFeature(RequestUtil.getParam(request, "feature"));
		orderBO.setPayTime(DateUtil.parse(RequestUtil.getParam(request, "payTime")));
		orderBO.setSendOutTime(DateUtil.parse(RequestUtil.getParam(request, "sendOutTime")));
		orderBO.setCommentTime(DateUtil.parse(RequestUtil.getParam(request, "commentTime")));
		PagerUtil.initPage(request, orderBO);
		return orderBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return orderManager;
	}

}
