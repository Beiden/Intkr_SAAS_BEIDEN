package com.intkr.saas.module.action.user.signUp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.user.UserStatus;
import com.intkr.saas.engine.CheckCodeEngine;
import com.intkr.saas.manager.user.MoneyAccountManager;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.UserRoleManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-4-22 下午1:51:17
 * @version 1.0
 */
public class SignUpAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MoneyAccountManager moneyAccountManager = IOC.get(MoneyAccountManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	private UserRoleManager authManager = IOC.get(UserRoleManager.class);

	private RoleManager roleManager = IOC.get(RoleManager.class);

	public void doSignUp(HttpServletRequest request, HttpServletResponse response) {
		if (!CheckCodeEngine.verifyCode(request)) {
			return;
		}
		if (!verify(request, response)) {
			return;
		}
		Long saasId = SessionClient.getSaasId(request);
		UserBO user = new UserBO();
		user.setId(userManager.getId());
		user.setAccount(RequestUtil.getParam(request, "account"));
		user.setNickName(RequestUtil.getParam(request, "account"));
		user.setPassword(RequestUtil.getParam(request, "password"));
		user.encryptPassword();
		user.setStatus(UserStatus.WaitVerified.getCode());
		user.setAvatar("/asset/img/avatar/1001.png");
		user.setMoney(0L);
		user.setHasSecureQuestion(2);
		user.setIsIdentity(2);
		userManager.insert(user);
		authManager.addRole(user.getId(), roleManager.getByCode("preMember").getId());
		moneyAccountManager.signUpInit(saasId, user.getId());
		SessionClient.login(request, response, user.getId());
		if (RequestUtil.existParam(request, "go_url")) {
			RequestUtil.forward(request, RequestUtil.getParam(request, "go_url"));
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "注册成功！");
	}

	public void doIsEmailCanUse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = RequestUtil.getParam(request, "email");
		request.setAttribute("email", email);
		if (email == null || "".equals(email)) {
			request.setAttribute("result", false);
		}
		if (userManager.isEmailCanUse(SessionClient.getSaasId(request), email)) {
			request.setAttribute("result", true);
		} else {
			request.setAttribute("result", false);
		}
	}

	public void doIsAccountCanUse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String account = RequestUtil.getParam(request, "account");
		request.setAttribute("account", account);
		if (account == null || "".equals(account)) {
			request.setAttribute("result", false);
		}
		if (userManager.getByAccount(SessionClient.getSaasId(request), account) == null) {
			request.setAttribute("result", true);
		} else {
			request.setAttribute("result", false);
		}
	}

	public void doIsPhoneCanUse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String phone = RequestUtil.getParam(request, "phone");
		request.setAttribute("phone", phone);
		if (phone == null || "".equals(phone)) {
			request.setAttribute("result", false);
		}
		if (userManager.isPhoneCanUse(SessionClient.getSaasId(request), phone)) {
			request.setAttribute("result", true);
		} else {
			request.setAttribute("result", false);
		}
	}

	private boolean verify(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.getParam(request, "account") == null || "".equals(RequestUtil.getParam(request, "account"))) {
			request.setAttribute("msg", "请填写帐号！");
			request.setAttribute("result", false);
			return false;
		}
		String password = RequestUtil.getParam(request, "password");
		if (RequestUtil.getParam(request, "password") == null || "".equals(RequestUtil.getParam(request, "password"))) {
			request.setAttribute("msg", "请填写密码！");
			request.setAttribute("result", false);
			return false;
		}
		String repassword = RequestUtil.getParam(request, "repassword");
		if (RequestUtil.getParam(request, "repassword") == null || "".equals(RequestUtil.getParam(request, "repassword"))) {
			request.setAttribute("msg", "请填写密码！");
			request.setAttribute("result", false);
			return false;
		}
		if (!password.equals(repassword)) {
			request.setAttribute("msg", "两次填写密码不一样！");
			request.setAttribute("result", false);
			return false;
		}
		if (!userManager.isAccountCanUse(SessionClient.getSaasId(request), RequestUtil.getParam(request, "account"))) {
			request.setAttribute("msg", "用户名已存在！");
			request.setAttribute("result", false);
			return false;
		}
		return true;
	}

}
