package com.intkr.saas.module.action.payment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.PaymentBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.order.PaymentManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-7-28 上午10:28:03
 * @version 1.0
 */
public class PaymentAction extends BaseAction<PaymentBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private PaymentManager paymentManager = IOC.get("PaymentManager");

	public void updateBO(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PaymentBO bo = getParameter(request);
		paymentManager.update(bo);
		request.setAttribute("result", true);
		request.setAttribute("msg", "更新成功!");
	}

	public void addBO(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PaymentBO bo = getParameter(request);
		Long saasId = SessionClient.getSaasId(request);
		String type = RequestUtil.getParam(request, "type");
		if (paymentManager.getByType(saasId, type) == null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "支付方式已存在!");
			return;
		}
		if ("alipay".equals(type)) {
			bo.setName("支付宝");
		} else if ("wxpay".equals(type)) {
			bo.setName("微信支付");
		} else if ("jdpay".equals(type)) {
			bo.setName("京东钱包");
		} else if ("yuepay".equals(type)) {
			bo.setName("余额支付");
		} else if ("offLinePay".equals(type)) {
			bo.setName("线下支付");
		} else if ("bankpay".equals(type)) {
			bo.setName("银行卡支付");
		}
		paymentManager.insert(bo);
		request.setAttribute("result", true);
		request.setAttribute("msg", "添加成功!");
	}

	public static PaymentBO getParameter(HttpServletRequest request) {
		PaymentBO paymentBO = new PaymentBO();
		paymentBO.setId(RequestUtil.getParam(request, "id", Long.class));
		paymentBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		paymentBO.setName(RequestUtil.getParam(request, "name"));
		paymentBO.setType(RequestUtil.getParam(request, "type"));
		paymentBO.setIsOpen(RequestUtil.getParam(request, "isOpen", Integer.class));
		paymentBO.setCategoryId(RequestUtil.getParam(request, "categoryId", Long.class));

		Map<String, String> map = RequestUtil.getParamMap(request);
		for (String key : map.keySet()) {
			if (key.startsWith("feature_")) {
				String realKey = key.substring(8);
				String value = RequestUtil.getParam(request, key);
				paymentBO.setFeature(realKey, value);
			}
		}
		PagerUtil.initPage(request, paymentBO);
		return paymentBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return paymentManager;
	}

	public void doSet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PaymentBO bo = getParameter(request);
		paymentManager.update(bo);
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功!");
	}

	public PaymentBO getBO(HttpServletRequest request) {
		return getParameter(request);
	}

}
