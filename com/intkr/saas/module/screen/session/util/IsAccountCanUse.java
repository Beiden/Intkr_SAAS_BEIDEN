package com.intkr.saas.module.screen.session.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-5-21 下午9:57:17
 * @version 1.0
 */
public class IsAccountCanUse {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get(UserManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String account = request.getParameter("account");
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

}
