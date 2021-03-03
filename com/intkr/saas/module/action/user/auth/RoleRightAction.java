package com.intkr.saas.module.action.user.auth;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.RoleRightBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.user.RoleRightManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class RoleRightAction extends BaseAction<RoleRightBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RoleRightManager roleRightManager = IOC.get(RoleRightManager.class);

	public RoleRightBO getBO(HttpServletRequest request) {
		RoleRightBO roleRightBO = getParameter(request);
		return roleRightBO;
	}

	public static RoleRightBO getParameter(HttpServletRequest request) {
		RoleRightBO roleRightBO = new RoleRightBO();
		roleRightBO.setId(RequestUtil.getParam(request, "id", Long.class));
		roleRightBO.setRoleId(RequestUtil.getParam(request, "roleId" , Long.class));
		roleRightBO.setRightId(RequestUtil.getParam(request, "rightId" , Long.class));
		PagerUtil.initPage(request, roleRightBO);
		return roleRightBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return roleRightManager;
	}

}
