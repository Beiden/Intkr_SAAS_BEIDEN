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
public class IsEmailCanUse {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get(UserManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
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

}
