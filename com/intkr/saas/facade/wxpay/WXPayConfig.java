package com.intkr.saas.facade.wxpay;

import java.io.InputStream;

/**
 * 
 * @author Beiden
 */
public class WXPayConfig {

	// 微信支付商户开通后 微信会提供appid
	private String appId;

	// appsecret
	private String appSecret;

	// 商户号partner
	private String mchId;

	// 这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	private String mchKey;

	/**
	 * 获取 App ID
	 * 
	 * @return App ID
	 */
	public String getAppID() {
		return appId;
	}

	/**
	 * 获取 Mch ID
	 * 
	 * @return Mch ID
	 */
	public String getMchID() {
		return mchId;
	}

	/**
	 * 获取 API 密钥
	 * 
	 * @return API密钥
	 */
	public String getKey() {
		return appSecret;
	}

	/**
	 * 获取商户证书内容
	 * 
	 * @return 商户证书内容
	 */
	public InputStream getCertStream() {
		return null;
	}

	/**
	 * HTTP(S) 连接超时时间，单位毫秒
	 * 
	 * @return
	 */
	public int getHttpConnectTimeoutMs() {
		return 6 * 1000;
	}

	/**
	 * HTTP(S) 读数据超时时间，单位毫秒
	 * 
	 * @return
	 */
	public int getHttpReadTimeoutMs() {
		return 8 * 1000;
	}

	/**
	 * 获取WXPayDomain, 用于多域名容灾自动切换
	 * 
	 * @return
	 */
	public WXPayDomain getWXPayDomain() {
		return WXPayDomainSimpleImpl.instance();
	}

	/**
	 * 是否自动上报。 若要关闭自动上报，子类中实现该函数返回 false 即可。
	 * 
	 * @return
	 */
	public boolean shouldAutoReport() {
		return true;
	}

	/**
	 * 进行健康上报的线程的数量
	 * 
	 * @return
	 */
	public int getReportWorkerNum() {
		return 6;
	}

	/**
	 * 健康上报缓存消息的最大数量。会有线程去独立上报 粗略计算：加入一条消息200B，10000消息占用空间 2000 KB，约为2MB，可以接受
	 * 
	 * @return
	 */
	public int getReportQueueMaxSize() {
		return 10000;
	}

	/**
	 * 批量上报，一次最多上报多个数据
	 * 
	 * @return
	 */
	public int getReportBatchSize() {
		return 10;
	}

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

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchKey() {
		return mchKey;
	}

	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}

}
