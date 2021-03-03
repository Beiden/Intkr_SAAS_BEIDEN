package com.intkr.saas.module.screen.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.domain.bo.user.UserBO;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-24 下午03:47:07
 * @version 1.0
 */
public class UserDetail {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get(UserManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			UserBO userBO = userManager.get(id);
			request.setAttribute("dto", userBO);
		} else {
			request.setAttribute("addId", userManager.getId());
		}
	}

}
