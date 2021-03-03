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
 * @date 2011-6-12 下午4:30:25
 * @version 1.0
 */
public class AlipayCallbackAsync {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderManager orderManager = IOC.get(OrderManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (AlipayUtil.isAliapyCallback(request, null) || AlipayUtil.isAliapyCallback(request) || AlipayUtil.isAliapyCallback(request, "GBK")) {// 验证成功
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
				Long orderId = Long.valueOf(out_trade_no);
				if (trade_status.equals("TRADE_FINISHED")) {// 订单已结束，不能再退款了。
					logger.warn("out_trade_no=" + out_trade_no + ";out_trade_no=" + out_trade_no + ";trade_no=" + trade_no + ";trade_status=" + trade_status + ";orderId=" + orderId);
					finishCallBack(trade_no, trade_status, orderId);
				} else if (trade_status.equals("TRADE_SUCCESS")) {// 交易成功
					successCallBack(trade_no, trade_status, orderId);
				}
				logger("Success", request);
				request.setAttribute("result", "success");
			} else {
				logger("AlipayUtil.isAliapyCallback(request):false", request);
				request.setAttribute("result", "fail");
			}
		} catch (Exception e) {
			logger.error("", e);
			logger("Exception", request);
			request.setAttribute("result", "fail");
		}
	}

	private void successCallBack(String trade_no, String trade_status, Long orderId) {
		OrderBO orderBO = orderManager.get(orderId);
		if (orderBO != null) {
			if (OrderStatus.WaitPay.getCode().equals(orderBO.getStatus())) {
				orderBO.setStatus(OrderStatus.WaitSendOut.getCode());
			}
			orderBO.setSubStatus(OrderSubStatus.AlipayAsynConfirmPay.getCode());
			if (orderBO.getPayTime() == null) {
				orderBO.setPayTime(new Date());
				orderBO.setPayment(MoneyAccount.Alipay.getCode());
			}
			orderBO.setFeature("trade_no", trade_no);
			orderBO.setFeature("trade_status", trade_status);
			orderManager.update(orderBO);
		}
	}

	private void finishCallBack(String trade_no, String trade_status, Long orderId) {
		OrderBO orderBO = orderManager.get(orderId);
		orderBO.setFeature("trade_no", trade_no);
		orderBO.setFeature("trade_status", trade_status);
		orderManager.update(orderBO);
	}

	private void logger(String type, HttpServletRequest request) {
		String log = "out_trade_no=" + request.getParameter("out_trade_no") + ";trade_no=" + request.getParameter("trade_no") + ";trade_status=" + request.getParameter("trade_status");
		logger.error("everyAlipayCallbackLog:[" + type + "]" + log);
	}

}
