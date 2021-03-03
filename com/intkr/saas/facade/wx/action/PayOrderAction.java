package com.intkr.saas.facade.wx.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.facade.wx.BasePayOrderInfo;
import com.intkr.saas.facade.wx.client.SignHelper;
import com.intkr.saas.facade.wx.util.PropertyUtils;

public class PayOrderAction {

	// @RequestMapping(value = "/payStart.htm")
	public String queryIndex(HttpServletRequest httpServletRequest) {
		String merchantNum = PropertyUtils.getProperty("wepay.merchant.num");
		httpServletRequest.setAttribute("merchantNum", merchantNum);
		return "payStart";
	}

	// @RequestMapping(value = "/clientOrder.htm")
	public String pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String version = request.getParameter("version");
		String token = request.getParameter("token");
		String merchantRemark = request.getParameter("merchantRemark");
		String tradeNum = request.getParameter("tradeNum");

		String tradeName = request.getParameter("tradeName");
		String tradeDescription = request.getParameter("tradeDescription");
		String tradeTime = request.getParameter("tradeTime");

		String tradeAmount = request.getParameter("tradeAmount");
		String currency = request.getParameter("currency");
		String successCallbackUrl = request.getParameter("successCallbackUrl");
		String notifyUrl = request.getParameter("notifyUrl");

		String ip = request.getParameter("ip");
		String merchantNum = request.getParameter("merchantNum");

		BasePayOrderInfo basePayOrderInfo = new BasePayOrderInfo();
		basePayOrderInfo.setToken(token);
		basePayOrderInfo.setMerchantNum(merchantNum);
		basePayOrderInfo.setMerchantRemark(merchantRemark);
		basePayOrderInfo.setTradeNum(tradeNum);
		basePayOrderInfo.setTradeName(tradeName);
		basePayOrderInfo.setTradeDescription(tradeDescription);
		basePayOrderInfo.setTradeTime(tradeTime);
		basePayOrderInfo.setTradeAmount(tradeAmount);
		basePayOrderInfo.setCurrency(currency);
		basePayOrderInfo.setNotifyUrl(notifyUrl);
		basePayOrderInfo.setSuccessCallbackUrl(successCallbackUrl);
		basePayOrderInfo.setVersion(version);
		basePayOrderInfo.setIp(ip);

		String serverUrl = PropertyUtils.getProperty("wepay.server.pay.url");
		String oriUrl = serverUrl + "/nPay.htm";
		String priKey = PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
		basePayOrderInfo.setMerchantSign(SignHelper.getSign(basePayOrderInfo, priKey));
		request.setAttribute("payOrderInfo", basePayOrderInfo);
		request.setAttribute("payUrl", oriUrl);

		return "autoSubmit";

	}

	public static String urlEncode(String input) {
		try {
			return URLEncoder.encode(input, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

}
