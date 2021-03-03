package com.intkr.saas.facade.wx;

import java.util.SortedMap;
import java.util.TreeMap;

import com.intkr.saas.facade.wx.util.GetWxOrderno;
import com.intkr.saas.facade.wx.util.RequestHandler;
import com.intkr.saas.facade.wx.util.Sha1Util;
import com.intkr.saas.facade.wx.util.TenpayUtil;

/**
 * @author ex_yangxiaoyi
 * 
 */
public class Demo {

	public static void main(String[] args) {
		testGetPackage();
	}

	private static void testGetPackage() {
		// 微信支付jsApi
		Request tpWxPay = new Request();
		tpWxPay.setOpenId(tpWxPay.getOpenId());
		tpWxPay.setBody("商品信息");
		tpWxPay.setOrderId(getNonceStr());
		tpWxPay.setSpbillCreateIp("127.0.0.1");
		tpWxPay.setTotalFee(1L);
		getPackage(tpWxPay);
	}

	/**
	 * 获取请求预支付id报文
	 * 
	 * @return
	 */
	public static String getPackage(Request tpRequest) {

		String openId = tpRequest.getOpenId();
		// 1 参数
		// 订单号
		String orderId = tpRequest.getOrderId();
		// 附加数据 原样返回
		String attach = "";
		// 总金额以分为单位，不带小数点
		String totalFee = tpRequest.getTotalFee().toString();

		// 订单生成的机器 IP
		String spbill_create_ip = tpRequest.getSpbillCreateIp();
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = tpRequest.getNotifyUrl();
		String trade_type = "JSAPI";

		// ---必须参数
		// 商户号
		String mch_id = tpRequest.getPartner();
		// 随机字符串
		String nonce_str = getNonceStr();

		// 商品描述根据情况修改
		String body = tpRequest.getBody();

		// 商户订单号
		String out_trade_no = orderId;

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", tpRequest.getAppId());
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		packageParams.put("total_fee", totalFee);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openId);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(tpRequest.getAppId(), tpRequest.getAppSecret(), tpRequest.getPartnerKey());

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + tpRequest.getAppId() + "</appid>" + "<mch_id>" + mch_id + "</mch_id>" + "<nonce_str>" + nonce_str + "</nonce_str>" + "<sign>" + sign + "</sign>" + "<body><![CDATA[" + body + "]]></body>" + "<out_trade_no>" + out_trade_no + "</out_trade_no>" + "<attach>" + attach + "</attach>" + "<total_fee>" + totalFee + "</total_fee>" + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>" + "<notify_url>" + notify_url + "</notify_url>" + "<trade_type>" + trade_type + "</trade_type>" + "<openid>" + openId + "</openid>" + "</xml>";
		String prepay_id = "";
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

		prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);

		System.out.println("获取到的预支付ID：" + prepay_id);

		// 获取prepay_id后，拼接最后请求支付所需要的package

		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String timestamp = Sha1Util.getTimeStamp();
		String packages = "prepay_id=" + prepay_id;
		finalpackage.put("appId", tpRequest.getAppId());
		finalpackage.put("timeStamp", timestamp);
		finalpackage.put("nonceStr", nonce_str);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		// 要签名
		String finalsign = reqHandler.createSign(finalpackage);

		String finaPackage = "\"appId\":\"" + tpRequest.getAppId() + "\",\"timeStamp\":\"" + timestamp + "\",\"nonceStr\":\"" + nonce_str + "\",\"package\":\"" + packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\"" + finalsign + "\"";

		System.out.println("V3 jsApi package:" + finaPackage);
		return finaPackage;
	}

	/**
	 * 获取随机字符串
	 * 
	 * @return
	 */
	public static String getNonceStr() {
		// 随机数
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		return strTime + strRandom;
	}

}
