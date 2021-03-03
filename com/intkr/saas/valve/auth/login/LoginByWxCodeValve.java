package com.intkr.saas.valve.auth.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.bo.user.WxAccountBO;
import com.intkr.saas.facade.wx.WebOAuth2Facade;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.WxAccountManager;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.valve.Valve;

/**
 * 自动注册一个微信用户帐号
 * 
 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx6887c749b105fcd8&
 * redirect_uri
 * =http%3A%2F%2Fwww.intkr.com&response_type=code&scope=snsapi_base&state
 * =STATE&connect_redirect=1#wechat_redirect
 * 
 * @author Beiden
 * @date 2017-12-3
 * @version 1.0
 */
public class LoginByWxCodeValve implements Valve {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private WxAccountManager wxAccountManager = IOC.get(WxAccountManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		Long saasId = SessionClient.getSaasId(request);
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		if (!"lbwx".equals(state)) {
			return true;
		}
		try {
			WxMpOAuth2AccessToken token = WebOAuth2Facade.getAccessToken(code);
			WxAccountBO wxAccount = wxAccountManager.getByOpenId(saasId, token.getOpenId());
			if (wxAccount != null && IdEngine.isValidate(wxAccount.getUserId())) {
				request.setAttribute("code", "areadyRegister");
				request.setAttribute("result", false);
				request.setAttribute("msg", "微信登录");
				SessionClient.login(request, response, wxAccount.getUserId());
				return true;
			} else if (wxAccount == null) {
				UserBO user = userManager.register(saasId, "", "");
				wxAccount = wxAccountManager.insert(user, token);
			}
			request.getSession().setAttribute("wxAccount", wxAccount);
		} catch (Exception e) {
			logger.error("", e);
		}
		return true;
	}

}