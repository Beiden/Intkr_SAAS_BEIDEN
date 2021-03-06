package com.intkr.saas.module.action.payment;

import java.text.DecimalFormat;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.MoneyAccountFlowBO;
import com.intkr.saas.facade.wxpay.WxPayEngine;
import com.intkr.saas.facade.wxpay.WxPayEngine.PayRequest;
import com.intkr.saas.facade.wxpay.WxPayEngine.PayResponse;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.user.MoneyAccountFlowManager;
import com.intkr.saas.manager.user.WxAccountManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 商户系统按微信支付协议生成支付二维码，用户扫码支付
 * 
 * @author Beiden
 * @date 2011-4-27 下午3:09:44
 * @version 1.0
 */
public class WxNativePayAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MoneyAccountFlowManager moneyFlowManager = IOC.get("MoneyAccountFlowManager");

	private OptionManager optionManager = IOC.get("OptionManager");

	private OrderManager orderManager = IOC.get(OrderManager.class);

	private WxAccountManager wxAccountManager = IOC.get("WxAccountManager");

	public void doCreatePayInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String moneyFlowId = RequestUtil.getParam(request, "moneyFlowId");
			String orderId = RequestUtil.getParam(request, "orderId");
			MoneyAccountFlowBO moneyFlow = moneyFlowManager.get(moneyFlowId);
			String outOrderNo = (String) moneyFlow.getFeature("out_trade_no");
			if (outOrderNo == null) {
				moneyFlow.setFeature("out_trade_no", moneyFlowId);
				moneyFlowManager.update(moneyFlow);
			}
			PayRequest payRequest = new PayRequest();
			String note = moneyFlow.getNote();
			payRequest.saasId = moneyFlow.getSaasId();
			payRequest.body = note.length() > 10 ? note.substring(0, 10) + ".." : note;
			Random random = new Random();
			DecimalFormat df = new DecimalFormat("000000");
			String code = df.format(random.nextInt(999999)) + "";
			payRequest.out_trade_no = moneyFlowId + "-" + code;
			payRequest.device_info = "";
			payRequest.fee_type = "CNY";
			payRequest.total_fee = moneyFlow.getMoney();
			payRequest.spbill_create_ip = "127.0.0.1";
			payRequest.notify_url = "http://" + RequestUtil.getDomain(request) + "/payment/wxpay/wxpayCallbackAsync-p-moneyFlowId-" + moneyFlowId + "-orderId-" + orderId + ".html";
			payRequest.trade_type = "NATIVE";
			payRequest.product_id = moneyFlowId;
			PayResponse result = WxPayEngine.getNativePayInfo(payRequest);
			if (result.isSuccess()) {
				request.setAttribute("result", true);
				request.setAttribute("msg", "获取成功！");
				request.setAttribute("data", result);
			} else {
				request.setAttribute("result", false);
				request.setAttribute("msg", result.return_msg);
				request.setAttribute("data", result);
			}
		} catch (Exception e) {
			logger.error("", e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常！");
		}
	}

	public void doGetPayInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

	}

}
