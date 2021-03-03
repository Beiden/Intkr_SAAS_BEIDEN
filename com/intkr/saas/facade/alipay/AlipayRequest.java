package com.intkr.saas.facade.alipay;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.facade.alipay.lib.AlipayConfig;
import com.intkr.saas.facade.alipay.lib.AlipaySubmit;

/**
 * 
 * @author Beiden
 * @date 2011-6-4 下午3:37:56
 * @version 1.0
 */
public class AlipayRequest {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 支付类型
	 * 
	 * 必填，不能修改
	 */
	private String paymentType = "1";

	/**
	 * 服务器异步通知页面路径
	 * 
	 * 需http://格式的完整路径，不能加?id=123这类自定义参数
	 */
	public String notifyUrl;

	/**
	 * 页面跳转同步通知页面路径
	 * 
	 * 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
	 */
	public String returnUrl;

	/**
	 * 商户订单号
	 * 
	 * 商户网站订单系统中唯一订单号，必填
	 */
	public String outTradeNo;

	/**
	 * 订单名称 , 必填
	 */
	public String subject;

	/**
	 * 付款金额 , 必填
	 */
	public String totalFee;

	/**
	 * 订单描述
	 */
	public String body;

	/**
	 * 商品展示地址
	 * 
	 * 需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html
	 */
	public String showUrl;

	/**
	 * 防钓鱼时间戳
	 * 
	 * 若要使用请调用类文件submit中的query_timestamp函数
	 */
	public String antiPhishingKey;

	/**
	 * 客户端的IP地址
	 * 
	 * 非局域网的外网IP地址，如：221.0.0.1
	 */
	public String exterInvokeIp;

	private void check() {
		if (paymentType == null || "".equals(paymentType)) {
			throw new RuntimeException("paymentType is null");
		}
		if (notifyUrl == null || "".equals(notifyUrl)) {
			throw new RuntimeException("notifyUrl is null");
		}
		if (returnUrl == null || "".equals(returnUrl)) {
			throw new RuntimeException("returnUrl is null");
		}
		if (outTradeNo == null || "".equals(outTradeNo)) {
			throw new RuntimeException("outTradeNo is null");
		}
		if (subject == null || "".equals(subject)) {
			throw new RuntimeException("subject is null");
		}
		if (totalFee == null || "".equals(totalFee)) {
			throw new RuntimeException("totalFee is null");
		}
	}

	public String getPayHtml() {
		check();
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_email", AlipayConfig.seller_id);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", paymentType);
		sParaTemp.put("notify_url", notifyUrl);
		sParaTemp.put("return_url", returnUrl);
		sParaTemp.put("out_trade_no", outTradeNo);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", totalFee);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", showUrl);
		sParaTemp.put("anti_phishing_key", antiPhishingKey);
		sParaTemp.put("exter_invoke_ip", exterInvokeIp);
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
		return sHtmlText;
	}

}
