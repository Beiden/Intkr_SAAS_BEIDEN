package com.intkr.saas.module.screen.payment.jdpay;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.PaymentBO;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.domain.type.order.OrderSubStatus;
import com.intkr.saas.domain.type.user.MoneyAccount;
import com.intkr.saas.facade.wx.model.TradeQueryRes;
import com.intkr.saas.facade.wx.util.RSACoder;
import com.intkr.saas.facade.wx.util.SHAUtil;
import com.intkr.saas.facade.wx.util.SignUtil;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.order.PaymentManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-6-12 下午4:29:12
 * @version 1.0
 */
public class JdpayCallback {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OptionManager optionManager = IOC.get(OptionManager.class);

	private OrderManager orderManager = IOC.get(OrderManager.class);

	private PaymentManager paymentManager = IOC.get(PaymentManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			PaymentBO jdpayConf = paymentManager.getJdpay(SessionClient.getSaasId(request));

			TradeQueryRes tradeQueryRes = new TradeQueryRes();
			tradeQueryRes.setToken(request.getParameter("token"));
			tradeQueryRes.setTradeNum(request.getParameter("tradeNum"));
			tradeQueryRes.setTradeAmount(request.getParameter("tradeAmount"));
			tradeQueryRes.setTradeCurrency(request.getParameter("tradeCurrency"));
			tradeQueryRes.setTradeDate(request.getParameter("tradeDate"));
			tradeQueryRes.setTradeTime(request.getParameter("tradeTime"));
			tradeQueryRes.setTradeNote(request.getParameter("tradeNote"));
			tradeQueryRes.setTradeStatus(request.getParameter("tradeStatus"));
			String sign = request.getParameter("sign");

			String strSourceData = SignUtil.signString(tradeQueryRes, new ArrayList<String>());

			byte[] decryptBASE64Arr = new BASE64Decoder().decodeBuffer(sign);
			String wyPubKey = (String) jdpayConf.getFeature("wangyin_rsa_public_key");

			byte[] decryptArr = RSACoder.decryptByPublicKey(decryptBASE64Arr, wyPubKey);
			String decryptStr = new String(decryptArr, "UTF-8");

			String sha256SourceSignString = SHAUtil.Encrypt(strSourceData, null);

			if (!decryptStr.equals(sha256SourceSignString)) {
				request.setAttribute("errorMsg", "验证签名失败！");
			} else {
				request.setAttribute("errorMsg", tradeQueryRes.getTradeNum() + ":status:" + request.getParameter("tradeStatus"));
				if ("0".equals(tradeQueryRes.getTradeStatus())) {
					OrderBO orderBO = orderManager.get(tradeQueryRes.getTradeNum());
					if (orderBO != null) {
						if ("待付款".equals(orderBO.getStatus())) {
							orderBO.setStatus(OrderStatus.WaitSendOut.getCode());
							orderBO.setSubStatus(OrderSubStatus.JdpayConfirmPay.getCode());
							orderBO.setPayTime(new Date());
							orderBO.setPayment(MoneyAccount.Jdpay.getCode());
							orderManager.update(orderBO);
							request.setAttribute("msg", "你的订单已经支付成功");
						} else {
							request.setAttribute("msg", "你的订单已经支付成功");
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			request.setAttribute("msg", "订单状态异常，请联系客服人员。");
		}
	}

}
