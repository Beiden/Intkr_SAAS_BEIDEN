package com.intkr.saas.module.screen.session;

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
import com.intkr.saas.util.ResponseUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-4 下午6:14:51
 * @version 1.0
 */
public class SignUpByWeiXinPC {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get(UserManager.class);

	private WxAccountManager wxAccountManager = IOC.get(WxAccountManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Long saasId = SessionClient.getSaasId(request);
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		try {
			WxMpOAuth2AccessToken token = WebOAuth2Facade.getAccessToken(code);
			WxAccountBO wxAccount = wxAccountManager.getByOpenId(saasId, token.getOpenId());
			request.getSession().setAttribute("registerTractionId", Long.valueOf(state));
			if (wxAccount != null && IdEngine.isValidate(wxAccount.getUserId())) {
				request.setAttribute("code", "areadyRegister");
				request.setAttribute("result", false);
				request.setAttribute("msg", "微信已经注册");
				SessionClient.login(request, response, wxAccount.getUserId());
				ResponseUtil.sendRedirect(request, response, "/sign/signUpByWeiXinPCConfirmLogin.html?code=areadyRegister");
				return;
			} else if (wxAccount == null) {
				UserBO user = userManager.register(saasId, "", "");
				wxAccount = wxAccountManager.insert(user, token);
			}
			request.getSession().setAttribute("wxAccount", wxAccount);
		} catch (Exception e) {
			logger.error("", e);
			ResponseUtil.sendRedirect(request, response, "/sign/signUpByWeiXinPCConfirmLogin.html?code=registerError");
		}
		request.setAttribute("activeFirstMenu", "index");
	}

}
