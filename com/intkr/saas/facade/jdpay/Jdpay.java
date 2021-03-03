package com.intkr.saas.facade.jdpay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.PaymentBO;
import com.intkr.saas.facade.wx.BasePayOrderInfo;
import com.intkr.saas.facade.wx.client.SignHelper;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.order.PaymentManager;
import com.intkr.saas.manager.saas.SaasClientManager;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-6-12 下午4:28:39
 * @version 1.0
 */
public class Jdpay {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OptionManager optionManager = IOC.get(OptionManager.class);

	private OrderManager orderManager = IOC.get(OrderManager.class);

	private PaymentManager paymentManager = IOC.get(PaymentManager.class);
	
	private static SaasClientManager saasClientManager = IOC.get(SaasClientManager.class);

	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			String orderId = request.getParameter("orderId");
			OrderBO order = orderManager.get(orderId);
			String websiteUrl = "http://" + saasClientManager.getSaasDomain(SessionClient.getSaasId(request));
			PaymentBO jdpayConf = paymentManager.getJdpay(SessionClient.getSaasId(request));
			BasePayOrderInfo basePayOrderInfo = new BasePayOrderInfo();
			basePayOrderInfo.setToken(RequestUtil.getParam(request, "token", ""));
			basePayOrderInfo.setMerchantNum((String) jdpayConf.getFeature("merchant_num"));
			basePayOrderInfo.setMerchantRemark("备注");
			basePayOrderInfo.setTradeNum(order.getId() + "");
			basePayOrderInfo.setTradeName(order.getName());
			basePayOrderInfo.setTradeDescription(order.getName());
			basePayOrderInfo.setTradeTime(DateUtil.formatDateTime(order.getGmtCreated()));
			basePayOrderInfo.setTradeAmount(order.getPrice() + "");
			basePayOrderInfo.setCurrency("CNY");
			basePayOrderInfo.setNotifyUrl(websiteUrl + "/trade/payment/jdpay/async/jdpayCallbackAsync.html");
			basePayOrderInfo.setSuccessCallbackUrl(websiteUrl + "/trade/payment/jdpay/jdpayCallback.html");
			basePayOrderInfo.setVersion("1.1.5");
			basePayOrderInfo.setIp("127.0.0.1");
			String priKey = (String) jdpayConf.getFeature("merchant_rsa_private_key");
			basePayOrderInfo.setMerchantSign(SignHelper.getSign(basePayOrderInfo, priKey));
			request.setAttribute("payOrderInfo", basePayOrderInfo);
			request.setAttribute("payUrl", "https://plus.jdpay.com/nPay.htm");
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
