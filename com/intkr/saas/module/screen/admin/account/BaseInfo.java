package com.intkr.saas.module.screen.admin.account;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2015-4-29 下午1:30:32
 * @version 1.0
 */
public class BaseInfo {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get(UserManager.class);

	private RoleManager roleManager = IOC.get(RoleManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		UserBO sessionUserBO = SessionClient.getLoginUser(request);
		UserBO userBO = userManager.get(sessionUserBO.getId());
		roleManager.fill(userBO);
		request.setAttribute("userInfo", userBO);
		request.setAttribute("activeSecondMenu", "baseInfo");
	}

}
