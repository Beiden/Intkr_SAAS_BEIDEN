package com.intkr.saas.facade.wx;

import com.intkr.saas.facade.wx.util.TenpayUtil;

public class Request {

	// 微信支付商户开通后 微信会提供appid
	private String appId;

	// appsecret和
	private String appSecret;

	// 商户号partner
	private String partner;

	// // 这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	private String partnerKey;

	// 这里notify_url是支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等
	private String notifyUrl;

	// 订单ID
	private String orderId;

	// 订单生成的机器 IP
	private String spbillCreateIp = "127.0.0.1";

	// 金额:单位分
	private Long totalFee;

	// 商品描述根据情况修改
	private String body;
	
	// 附加数据 原样返回
	private String attach = "";

	private String openId;// 微信用户对一个公众号唯一

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public Long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}
	
	/**
	 * 获取随机字符串
	 * 
	 * @return
	 */
	public static String getRandomStr() {
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
