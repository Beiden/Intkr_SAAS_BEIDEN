package com.intkr.saas.module.action.order;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.distributed.session.SessionClientDistImpl;
import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.MoneyUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-7-28 上午7:57:52
 * @version 1.0
 */
public class OrderDetailAction extends BaseAction<OrderDetailBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	public OrderDetailBO getBO(HttpServletRequest request) {
		OrderDetailBO orderDetailBO = getParameter(request);
		return orderDetailBO;
	}

	public static OrderDetailBO getParameter(HttpServletRequest request) {
		OrderDetailBO orderDetailBO = new OrderDetailBO();
		orderDetailBO.setSaasId(SessionClientDistImpl.getSaas(request).getId());
		orderDetailBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		orderDetailBO.setOrderId(RequestUtil.getParam(request, "orderId", Long.class));
		orderDetailBO.setSkuId(RequestUtil.getParam(request, "skuId", Long.class));
		if (RequestUtil.existParam(request, "unitPrice")) {
			orderDetailBO.setUnitPrice(MoneyUtil.parse(RequestUtil.getParam(request, "unitPrice")));
		}
		orderDetailBO.setCount(RequestUtil.getParam(request, "count", Integer.class));
		if (RequestUtil.existParam(request, "price")) {
			orderDetailBO.setPrice(MoneyUtil.parse(RequestUtil.getParam(request, "price")));
		}
		orderDetailBO.setFeature(RequestUtil.getParam(request, "feature"));
		orderDetailBO.setIsEvaluate(RequestUtil.getParam(request, "isEvaluate", Integer.class));
		PagerUtil.initPage(request, orderDetailBO);
		return orderDetailBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return orderDetailManager;
	}

}
