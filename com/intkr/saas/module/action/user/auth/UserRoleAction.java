package com.intkr.saas.module.action.user.auth;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.UserRoleBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.user.UserRoleManager;
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
public class UserRoleAction extends BaseAction<UserRoleBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserRoleManager userRoleManager = IOC.get(UserRoleManager.class);

	public UserRoleBO getBO(HttpServletRequest request) {
		UserRoleBO userRoleBO = getParameter(request);
		return userRoleBO;
	}

	public static UserRoleBO getParameter(HttpServletRequest request) {
		UserRoleBO userRoleBO = new UserRoleBO();
		userRoleBO.setId(RequestUtil.getParam(request, "id", Long.class));
		userRoleBO.setUserId(RequestUtil.getParam(request, "userId" , Long.class));
		userRoleBO.setRoleId(RequestUtil.getParam(request, "roleId" , Long.class));
		PagerUtil.initPage(request, userRoleBO);
		return userRoleBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return userRoleManager;
	}

}
