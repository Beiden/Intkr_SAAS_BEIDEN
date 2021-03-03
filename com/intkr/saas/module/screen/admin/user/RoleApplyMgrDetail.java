package com.intkr.saas.module.screen.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.RoleApplyBO;
import com.intkr.saas.domain.type.user.RoleApplyStatus;
import com.intkr.saas.manager.fs.MediaManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.RoleApplyManager;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-2 上午11:34:21
 * @version 1.0
 */
public class RoleApplyMgrDetail {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RoleManager roleManager = IOC.get("RoleManager");

	private UserManager userManager = IOC.get("UserManager");

	private RoleApplyManager roleApplyManager = IOC.get("RoleApplyManager");

	private MediaManager mediaManager = IOC.get("MediaManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String roleApplyId = RequestUtil.getParam(request, "roleApplyId");
		RoleApplyBO roleApply = roleApplyManager.get(roleApplyId);
		userManager.fill(roleApply);
		roleManager.fill(roleApply);
		roleApply.setProperty("companyBusinessLicense", mediaManager.get(roleApply.getFeature("companyBusinessLicense")));
		roleApply.setProperty("zzjgdmz", mediaManager.get(roleApply.getFeature("zzjgdmzId")));
		roleApply.setProperty("swdjz", mediaManager.get(roleApply.getFeature("swdjzId")));
		roleApply.setProperty("companyOwnerIdFront", mediaManager.get(roleApply.getFeature("companyOwnerIdFront")));
		roleApply.setProperty("companyOwnerIdBack", mediaManager.get(roleApply.getFeature("companyOwnerIdBack")));
		roleApply.setProperty("htz", mediaManager.get(roleApply.getFeature("htzId")));
		roleApply.setProperty("zzsfp", mediaManager.get(roleApply.getFeature("zzsfpId")));
		roleApply.setProperty("clzp", mediaManager.get(roleApply.getFeature("clzpId")));
		roleApply.setProperty("driverIdFront", mediaManager.get(roleApply.getFeature("driverIdFront")));
		roleApply.setProperty("dirverIdBack", mediaManager.get(roleApply.getFeature("dirverIdBack")));
		roleApply.setProperty("jsz", mediaManager.get(roleApply.getFeature("jszId")));
		roleApply.setProperty("xxz", mediaManager.get(roleApply.getFeature("xxzId")));
		roleApply.setProperty("dlyscyzgz", mediaManager.get(roleApply.getFeature("dlyscyzgzId")));
		roleApply.setProperty("dlysjyxkz", mediaManager.get(roleApply.getFeature("dlysjyxkzId")));
		roleApply.setProperty("hybxd", mediaManager.get(roleApply.getFeature("hybxdId")));
		roleApply.setProperty("cma", mediaManager.get(roleApply.getFeature("cmaId")));
		roleApply.setProperty("cnas", mediaManager.get(roleApply.getFeature("cnasId")));
		roleApply.setProperty("cal", mediaManager.get(roleApply.getFeature("calId")));
		roleApply.setProperty("rzdbxkz", mediaManager.get(roleApply.getFeature("rzdbxkzId")));
		request.setAttribute("roleApply", roleApply);
		request.setAttribute("roleApplyStatusList", RoleApplyStatus.values());
	}

}
