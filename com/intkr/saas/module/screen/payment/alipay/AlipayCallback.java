package com.intkr.saas.module.screen.payment.alipay;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.domain.type.order.OrderSubStatus;
import com.intkr.saas.domain.type.user.MoneyAccount;
import com.intkr.saas.facade.alipay.AlipayUtil;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-6-12 下午4:29:12
 * @version 1.0
 */
public class AlipayCallback {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderManager orderManager = IOC.get(OrderManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (AlipayUtil.isAliapyCallback(request)) {
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					logger.warn("out_trade_no=" + out_trade_no + ";trade_no=" + trade_no + ";trade_status=" + trade_status);
					successCallBack(request, out_trade_no, trade_no, trade_status);
				} else {
					logger("tradeStatus:false", request);
					request.setAttribute("msg", "订单状态异常，请联系客服！1");
				}
			} else {
				logger("AlipayUtil.isAliapyCallback(request):false", request);
				request.setAttribute("msg", "订单状态异常，请联系客服人员！2");
			}
		} catch (Exception e) {
			logger.error("", e);
			logger("Exception", request);
			request.setAttribute("msg", "订单状态异常，请联系客服人员。");
		}
	}

	private void successCallBack(HttpServletRequest request, String out_trade_no, String trade_no, String trade_status) {
		String orderId = out_trade_no;
		OrderBO orderBO = orderManager.get(orderId);
		if (orderBO != null) {
			if (OrderStatus.WaitPay.getCode().equals(orderBO.getStatus())) {
				orderBO.setStatus(OrderStatus.WaitSendOut.getCode());
				orderBO.setSubStatus(OrderSubStatus.AlipayConfirmPay.getCode());
				orderBO.setPayTime(new Date());
				orderBO.setPayment(MoneyAccount.Alipay.getCode());
				orderBO.setFeature("trade_no", trade_no);
				orderBO.setFeature("trade_status", trade_status);
				orderManager.update(orderBO);
				request.setAttribute("msg", "你的订单已经支付成功");
				logger("Success", request);
			} else {
				request.setAttribute("msg", "你的订单已经支付成功");
				logger("Success", request);
			}
		}
	}

	private void logger(String type, HttpServletRequest request) {
		String log = "out_trade_no=" + request.getParameter("out_trade_no") + ";trade_no=" + request.getParameter("trade_no") + ";trade_status=" + request.getParameter("trade_status");
		logger.error("everyAlipayCallbackLog:[" + type + "]" + log);
	}

}
