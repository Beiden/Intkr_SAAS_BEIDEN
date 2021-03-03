package com.intkr.saas.module.screen.admin.sign.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.distributed.session.SessionClientDistImpl;
import com.intkr.saas.distributed.session.SkeyEngine;
import com.intkr.saas.domain.bo.saas.SaasClientBO;
import com.intkr.saas.domain.bo.saas.SaasDomainBO;
import com.intkr.saas.manager.saas.SaasClientManager;
import com.intkr.saas.manager.saas.SaasDomainManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.UrlUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2017-12-3
 * @version 1.0
 */
public class SignInToken {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SaasClientManager saasClientManager = IOC.get(SaasClientManager.class);

	private SaasDomainManager appDomainManager = IOC.get(SaasDomainManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (!RequestUtil.existParam(request, "loginRedirectUrl")) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "参数异常：loginRedirectUrl必填!");
			return;
		}
		String loginRedirectUrl = RequestUtil.getParam(request, "loginRedirectUrl");
		String domain = RequestUtil.getDomain(request);
		String redirectDomain = SignUtil.getDomain(loginRedirectUrl);
		if (SessionClient.isLogin(request) && redirectDomain != null && !domain.equals(redirectDomain)) {
			String skey = SkeyEngine.getSkey(SessionClient.getLoginUserId(request));
			if (skey != null && !"".equals(skey)) {
				loginRedirectUrl = UrlUtil.addParam(loginRedirectUrl, SkeyEngine.urlKey, skey);
			}
			String token = SessionClientDistImpl.setToken(SessionClient.getLoginUserId(request));
			if (token != null && !"".equals(token)) {
				loginRedirectUrl = UrlUtil.addParam(loginRedirectUrl, SessionClientDistImpl.ikLoginToken, token);
			}
		}
		SaasClientBO saas = null;
		SaasDomainBO appDomain = appDomainManager.getByDomain(RequestUtil.getDomain(request));
		if (appDomain != null) {
			saas = saasClientManager.getFullByDomain(appDomain.getDomain());
		} else {
			saas = saasClientManager.getDefaultFull();
			if (saas != null && saas.getDomains() != null && !saas.getDomains().isEmpty()) {
				appDomain = saas.getDomains().get(0);
			}
		}
		request.setAttribute("signInAppDomain", appDomain);
		request.setAttribute("signInApp", saas);
		request.setAttribute("loginRedirectUrl", loginRedirectUrl);
		request.setAttribute("fromUrl", request.getHeader("Referer"));
	}

}
