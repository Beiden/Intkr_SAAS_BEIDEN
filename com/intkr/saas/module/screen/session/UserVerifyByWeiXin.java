package com.intkr.saas.module.screen.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.common.api.WxConsts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.facade.wx.WebOAuth2Facade;
import com.intkr.saas.facade.wx.WeiXinEngine;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.saas.SaasClientManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-8 上午11:34:34
 * @version 1.0
 */
public class UserVerifyByWeiXin {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OptionManager optionManager = IOC.get(OptionManager.class);

	private SaasClientManager saasClientManager = IOC.get(SaasClientManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Long tractionId = WeiXinEngine.getTractionId();
		String redirectURI = "http://" + saasClientManager.getSaasDomain(SessionClient.getSaasId(request)) + "/member/account/weixinBD.html";
		String url = WebOAuth2Facade.getUrl(redirectURI, WxConsts.OAUTH2_SCOPE_USER_INFO, tractionId + "");
		request.setAttribute("url", url);
		request.setAttribute("tractionId", tractionId);
	}

}
