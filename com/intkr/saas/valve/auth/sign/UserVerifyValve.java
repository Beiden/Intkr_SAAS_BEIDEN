package com.intkr.saas.valve.auth.sign;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.user.UserStatus;
import com.intkr.saas.engine.auth.AuthorityEngine;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.valve.Valve;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 下午4:52:44
 * @version 1.0
 */
public class UserVerifyValve implements Valve {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OptionManager optionManager = IOC.get("OptionManager");

	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		if (!SessionClient.isLogin(request)) {// 没有登录的
			return true;
		}
		UserBO user = SessionClient.getLoginUser(request);
		if (!UserStatus.WaitVerified.getCode().equals(user.getStatus())) {// 帐号已经认证过的
			return true;
		}
		String action = request.getParameter("action");
		if (action == null) {// 没有访问action的
			return true;
		}
		if (AuthorityEngine.getRoleFillRight("preMember").hasRight(action)) {// 本来就有权限的
			return true;
		}
		RequestUtil.forward(request, "/sign/accountVerify.html");
		return false;
	}

}
