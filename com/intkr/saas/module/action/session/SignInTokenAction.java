package com.intkr.saas.module.action.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.distributed.session.SessionClientDistImpl;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.engine.CheckCodeEngine;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 登录(微服务)
 * 
 * @author Beiden
 * @date 2011-4-19 下午12:46:30
 * @version 1.0
 */
public class SignInTokenAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get(UserManager.class);

	public void doSignIn(HttpServletRequest request, HttpServletResponse response) {
		logger.info("start");
		if (SessionClientDistImpl.isLogin(request)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "您已经登录！");
			return;
		}
		if (!CheckCodeEngine.verifyCode(request)) {
			return;
		}
		if (RequestUtil.getParam(request, "account") == null || "".equals(RequestUtil.getParam(request, "account"))) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请填写名称！");
			return;
		}
		if (RequestUtil.getParam(request, "password") == null || "".equals(RequestUtil.getParam(request, "password"))) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请填写密码！");
			return;
		}
		Long saasId = RequestUtil.getParam(request, "saasId", Long.class);
		if (saasId == null) {
			saasId = SessionClientDistImpl.getSaasId(request);
		}
		String account = RequestUtil.getParam(request, "account");
		UserBO userBO = null;
		if (userBO == null) {
			userBO = userManager.getByPhone(saasId, account);
		}
		if (userBO == null) {
			userBO = userManager.getByEmail(saasId, account);
		}
		if (userBO == null) {
			userBO = userManager.getByAccount(saasId, account);
		}
		if (userBO == null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "帐号不存在！");
			return;
		}
		String password = RequestUtil.getParam(request, "password");
		if (!userBO.isPasswordEquals(password)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "密码错误！");
			return;
		}
		boolean loginResult = SessionClientDistImpl.login(request, response, userBO.getId());
		String token = SessionClientDistImpl.getToken(request, response);
		request.setAttribute("data", token);
		request.setAttribute("result", true);
		request.setAttribute("msg", "登录成功！");
		logger.info("end");
	}

}
