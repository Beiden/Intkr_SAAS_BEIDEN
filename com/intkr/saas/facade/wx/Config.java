package com.intkr.saas.facade.wx;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;

import com.intkr.saas.domain.bo.order.PaymentBO;
import com.intkr.saas.manager.order.PaymentManager;
import com.intkr.saas.manager.order.impl.PaymentManagerImpl;
import com.intkr.saas.util.claz.IOC;

/**
 * API文档：https://github.com/chanjarster/weixin-java-tools
 * 
 * @author Beiden
 * @date 2016-3-22 下午8:14:40
 * @version 1.0
 */
public class Config {

	public static WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();

	public static WxMpService wxService = null;

	public static WxMpMessageRouter wxMpMessageRouter;

	public static PaymentManager paymentManager = IOC.get(PaymentManagerImpl.class);

	static {
		PaymentBO wxPayment = paymentManager.getWxpay(1L);
		config.setAppId((String) wxPayment.getFeature("app_id")); // 设置微信公众号的appid
		config.setSecret((String) wxPayment.getFeature("app_secret")); // 设置微信公众号的appCorpSecret
		config.setToken(null); // 设置微信公众号的token
		config.setAesKey(null); // 设置微信公众号的EncodingAESKey
		config.setPartnerId((String) wxPayment.getFeature("partner")); // 设置微信公众号的token
		config.setPartnerKey((String) wxPayment.getFeature("partner_key"));// 设置微信公众号的EncodingAESKey
		wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);
		wxMpMessageRouter = new WxMpMessageRouter(wxService);
	}

}
