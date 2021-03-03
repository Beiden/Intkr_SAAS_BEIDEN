package com.intkr.saas.module.screen.admin.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.RoleBO;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.module.action.saas.SaasAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-11-16 下午4:29:59
 * @version 1.0
 */
public class RoleMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RoleManager roleManager = IOC.get(RoleManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Long saasId = SessionClient.getSaasId(request);
		List<RoleBO> list = roleManager.getAllFull(saasId);
		request.setAttribute("list", list);
		SaasAction.fillSaas(request);
	}

}
