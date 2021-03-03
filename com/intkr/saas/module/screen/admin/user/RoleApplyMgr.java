package com.intkr.saas.module.screen.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.RoleApplyBO;
import com.intkr.saas.domain.type.user.RoleApplyStatus;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.RoleApplyManager;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.module.action.user.role.RoleApplyAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 下午3:49:58
 * @version 1.0
 */
public class RoleApplyMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RoleManager roleManager = IOC.get("RoleManager");

	private UserManager userManager = IOC.get("UserManager");

	private RoleApplyManager roleApplyManager = IOC.get("RoleApplyManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		RoleApplyBO query = RoleApplyAction.getParameter(request);
		if (query.getStatus() == null) {
			query.setStatus(RoleApplyStatus.WaitAudit.getCode());
		} else if ("all".equalsIgnoreCase(query.getStatus())) {
			query.setStatus(null);
		}
		query = roleApplyManager.selectAndCount(query);
		query.setDatas(userManager.fill(query.getDatas()));
		query.setDatas(roleManager.fill(query.getDatas()));
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("roleApplyStatusList", RoleApplyStatus.values());
		request.setAttribute("roleList", roleManager.select());
	}

}
