package com.intkr.saas.module.screen.admin.user.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.RoleRightBO;
import com.intkr.saas.manager.user.RoleRightManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class RoleRightAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RoleRightManager manager = IOC.get(RoleRightManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String roleRightId = RequestUtil.getParam(request, "roleRightId");
		RoleRightBO roleRight = manager.get(roleRightId);
		request.setAttribute("roleRight", roleRight);
		request.setAttribute("addId", manager.getId());
	}

}
