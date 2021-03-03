package com.intkr.saas.module.action.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.distributed.session.SkeyEngine;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.user.UserStatus;
import com.intkr.saas.engine.CheckCodeEngine;
import com.intkr.saas.manager.saas.SaasClientManager;
import com.intkr.saas.manager.saas.SaasDomainManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.screen.admin.sign.impl.SignUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 登录
 * 
 * @author Beiden
 * @date 2011-4-19 下午12:46:30
 * @version 1.0 *
 */
public class SignInAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get(UserManager.class);

	private SaasClientManager saasClientManager = IOC.get(SaasClientManager.class);

	private SaasDomainManager appDomainManager = IOC.get(SaasDomainManager.class);

	public void doSignIn(HttpServletRequest request, HttpServletResponse response) {
		logger.info("start");
		if (!CheckCodeEngine.verifyCode(request) || !verifyAccount(request) || !verifyPwd(request)) {
			return; // 参数异常
		}
		UserBO user = getUser(request);
		if (user == null) { // 帐号不存在
			request.setAttribute("result", false);
			request.setAttribute("msg", "帐号不存在！");
			return;
		}
		String pwd = RequestUtil.getParam(request, "password");
		if (!user.isPasswordEquals(pwd)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "密码错误！");
			return;
		}
		if (UserStatus.Prohibit.getCode().equals(user.getStatus())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "帐号禁用！");
			return;
		}
		if (UserStatus.WaitVerified.getCode().equals(user.getStatus())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "帐号待认证！");
			return;
		}
		SessionClient.login(request, response, user.getId());
		fillSkey(request, user.getId());
		request.setAttribute("result", true);
		request.setAttribute("msg", "登录成功！");
		logger.info("end");
	}

	private UserBO getUser(HttpServletRequest request) {
		Long saasId = getSaasId(request);
		String account = RequestUtil.getParam(request, "account");
		UserBO user = null;
		if (user == null) {
			user = userManager.getByPhone(saasId, account);
		}
		if (user == null) {
			user = userManager.getByEmail(saasId, account);
		}
		if (user == null) {
			user = userManager.getByAccount(saasId, account);
		}
		return user;
	}

	private Long getSaasId(HttpServletRequest request) {
		Long saasId = RequestUtil.getParam(request, "saasId", Long.class);
		if (saasId == null) {
			saasId = SessionClient.getSaasId(request);
		}
		String account = RequestUtil.getParam(request, "account");
		if ("admin".equals(account)) {
			saasId = null;
		}
		return saasId;
	}

	private boolean verifyPwd(HttpServletRequest request) {
		if (RequestUtil.getParam(request, "password") == null || "".equals(RequestUtil.getParam(request, "password"))) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请填写密码！");
			return false;
		}
		return true;
	}

	private boolean verifyAccount(HttpServletRequest request) {
		if (RequestUtil.getParam(request, "account") == null || "".equals(RequestUtil.getParam(request, "account"))) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请填写名称！");
			return false;
		}
		return true;
	}

	private void fillSkey(HttpServletRequest request, Long userId) {
		String loginRedirectUrl = RequestUtil.getParam(request, "loginRedirectUrl");
		String redirectDomain = SignUtil.getDomain(loginRedirectUrl);
		String domain = RequestUtil.getDomain(request);
		if (redirectDomain != null && !domain.equals(redirectDomain)) {
			Map<String, String> data = new HashMap<String, String>();
			data.put(SkeyEngine.urlKey, SkeyEngine.getSkey(userId));
			request.setAttribute("data", data);
		}
	}

}
