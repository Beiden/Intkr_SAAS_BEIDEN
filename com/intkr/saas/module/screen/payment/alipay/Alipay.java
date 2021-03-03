package com.intkr.saas.module.screen.payment.alipay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.PaymentBO;
import com.intkr.saas.facade.alipay.AlipayRequest;
import com.intkr.saas.facade.alipay.lib.AlipayConfig;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.order.PaymentManager;
import com.intkr.saas.manager.saas.SaasClientManager;
import com.intkr.saas.util.MoneyUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-6-12 下午4:28:39
 * @version 1.0
 */
public class Alipay {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OptionManager optionManager = IOC.get(OptionManager.class);

	private OrderManager orderManager = IOC.get(OrderManager.class);

	private PaymentManager paymentManager = IOC.get(PaymentManager.class);
	
	private SaasClientManager saasClientManager = IOC.get(SaasClientManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String orderId = request.getParameter("orderId");
		OrderBO order = orderManager.get(orderId);
		PaymentBO alipayConf = paymentManager.getAlipay(SessionClient.getSaasId(request));
		AlipayConfig.partner = (String) alipayConf.getFeature("partner");
		AlipayConfig.seller_id = (String) alipayConf.getFeature("seller_email");
		AlipayConfig.private_key = (String) alipayConf.getFeature("key");
		String sHtmlText = getPayHtml(order);
		request.setAttribute("sHtmlText", sHtmlText);
	}

	private String getPayHtml(OrderBO orderBO) {
		String websiteUrl = "http://" + saasClientManager.getSaasDomain(orderBO.getSaasId());
		AlipayRequest request = new AlipayRequest();
		request.notifyUrl = websiteUrl + "/trade/payment/alipay/async/alipayCallbackAsync.html";
		request.returnUrl = websiteUrl + "/trade/payment/alipay/alipayCallback.html";
		request.outTradeNo = "" + orderBO.getId();
		request.subject = orderBO.getName();
		request.totalFee = MoneyUtil.format2(orderBO.getPrice());
		request.body = orderBO.getName();
		request.showUrl = websiteUrl + "/member/orderDetail-param-id-" + orderBO.getId() + ".html";
		request.antiPhishingKey = "";
		request.exterInvokeIp = "";
		return request.getPayHtml();
	}

}
