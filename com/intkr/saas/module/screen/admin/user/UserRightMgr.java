package com.intkr.saas.module.screen.admin.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.RightManager;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-3 下午6:00:36
 * @version 1.0
 */
public class UserRightMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RightManager rightManager = IOC.get(RightManager.class);

	private RoleManager roleManager = IOC.get(RoleManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String userId = RequestUtil.getParam(request, "userId");
		UserBO user = userManager.get(userId);
		roleManager.fill(user);
		rightManager.fill(user);
		request.setAttribute("user", user);
		request.setAttribute("allRoleRight", roleManager.getAllFull(SessionClient.getSaasId(request)));
		request.setAttribute("allRight", rightManager.getAllFull("admin"));
	}

}
