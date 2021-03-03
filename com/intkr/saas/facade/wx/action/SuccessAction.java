package com.intkr.saas.facade.wx.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

import com.intkr.saas.facade.wx.model.TradeQueryRes;
import com.intkr.saas.facade.wx.util.PropertyUtils;
import com.intkr.saas.facade.wx.util.RSACoder;
import com.intkr.saas.facade.wx.util.SHAUtil;
import com.intkr.saas.facade.wx.util.SignUtil;

public class SuccessAction {
	// @RequestMapping(value = "/success.htm")
	public String success(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TradeQueryRes tradeQueryRes = new TradeQueryRes();
		String token = request.getParameter("token");
		tradeQueryRes.setToken(token);

		String tradeNum = request.getParameter("tradeNum");
		tradeQueryRes.setTradeNum(tradeNum);

		String tradeAmount = request.getParameter("tradeAmount");
		tradeQueryRes.setTradeAmount(tradeAmount);

		String tradeCurrency = request.getParameter("tradeCurrency");
		tradeQueryRes.setTradeCurrency(tradeCurrency);

		String tradeDate = request.getParameter("tradeDate");
		tradeQueryRes.setTradeDate(tradeDate);

		String tradeTime = request.getParameter("tradeTime");
		tradeQueryRes.setTradeTime(tradeTime);
		String tradeNote = request.getParameter("tradeNote");
		tradeQueryRes.setTradeNote(tradeNote);
		String tradeStatus = request.getParameter("tradeStatus");
		tradeQueryRes.setTradeStatus(tradeStatus);
		String sign = request.getParameter("sign");

		String strSourceData = SignUtil.signString(tradeQueryRes, new ArrayList<String>());

		byte[] decryptBASE64Arr = new BASE64Decoder().decodeBuffer(sign);
		String wyPubKey = PropertyUtils.getProperty("wepay.wangyin.rsaPublicKey");

		byte[] decryptArr = RSACoder.decryptByPublicKey(decryptBASE64Arr, wyPubKey);
		String decryptStr = new String(decryptArr, "UTF-8");

		String sha256SourceSignString = SHAUtil.Encrypt(strSourceData, null);

		if (!decryptStr.equals(sha256SourceSignString)) {
			request.setAttribute("errorMsg", "验证签名失败！");
		} else {
			request.setAttribute("errorMsg", tradeQueryRes.getTradeNum() + ":status:" + tradeStatus);
		}
		return "queryResult";
	}
}
