package com.intkr.saas.module.action.user.auth;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.UserRightBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.user.UserRightManager;
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
public class UserRightAction extends BaseAction<UserRightBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserRightManager userRightManager = IOC.get(UserRightManager.class);

	public UserRightBO getBO(HttpServletRequest request) {
		UserRightBO userRightBO = getParameter(request);
		return userRightBO;
	}

	public static UserRightBO getParameter(HttpServletRequest request) {
		UserRightBO userRightBO = new UserRightBO();
		userRightBO.setId(RequestUtil.getParam(request, "id", Long.class));
		userRightBO.setUserId(RequestUtil.getParam(request, "userId" , Long.class));
		userRightBO.setRightId(RequestUtil.getParam(request, "rightId" , Long.class));
		PagerUtil.initPage(request, userRightBO);
		return userRightBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return userRightManager;
	}

}
