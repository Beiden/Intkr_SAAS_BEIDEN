package com.intkr.saas.module.screen.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.RoleBO;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.module.action.saas.SaasAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-11-16 下午4:30:11
 * @version 1.0
 */
public class RoleMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RoleManager roleManager = IOC.get(RoleManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			RoleBO roleBO = roleManager.get(id);
			request.setAttribute("dto", roleBO);
		} else {
			request.setAttribute("addId", roleManager.getId());
		}
		request.setAttribute("allRoleList", roleManager.getAllFull(SessionClient.getSaasId(request)));
		SaasAction.fillSaas(request);
	}

}
