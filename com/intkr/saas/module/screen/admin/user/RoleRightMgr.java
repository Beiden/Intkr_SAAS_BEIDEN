package com.intkr.saas.module.screen.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.user.RightManager;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.RoleBO;

/**
 * 
 * @author Beiden
 * @date 2016-5-3 下午6:00:36
 * @version 1.0
 */
public class RoleRightMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RightManager rightManager = IOC.get(RightManager.class);

	private RoleManager roleManager = IOC.get(RoleManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String roleId = RequestUtil.getParam(request, "roleId");
		RoleBO role = roleManager.get(roleId);
		rightManager.fill(role);
		request.setAttribute("role", role);
		request.setAttribute("allRight", rightManager.getAllFull("admin"));
	}

}
