package com.intkr.saas.module.action.user.role;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.RoleApplyBO;
import com.intkr.saas.domain.type.user.RoleApplyStatus;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.manager.user.RoleApplyManager;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 下午2:44:29
 * @version 1.0
 */
public class RoleApplyAction extends BaseAction<RoleApplyBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RoleApplyManager roleApplyManager = IOC.get(RoleApplyManager.class);

	private RoleManager roleManager = IOC.get(RoleManager.class);

	private MsgManager msgManager = IOC.get(MsgManager.class);

	/**
	 * 申请
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doApply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RoleApplyBO roleApply = new RoleApplyBO();
		roleApply.setUserId(SessionClient.getLoginUserId(request));
		roleApply.setRoleId(roleManager.getByCode(RequestUtil.getParam(request, "role")).getId());
		if (roleApplyManager.count(roleApply) == 0) {
			roleApply.setStatus(RoleApplyStatus.WaitSubmit.getCode());
			roleApply.setId(roleApplyManager.getId());
			roleApply.setFeature("applyTime", DateUtil.formatDateTime(new Date()));
			roleApplyManager.insert(roleApply);
		}
		msgManager.sendSysMsg(roleApply.getUserId(), "角色认证审核结果", "亲，您好，你的认证申请已通过审核，请继续冻结保证金，完成认证。有任何疑问请随时联系您的专属客服。");
		request.setAttribute("result", true);
		request.setAttribute("msg", "签约成功");
	}

	public void doSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long userId = SessionClient.getLoginUserId(request);
		RoleApplyBO roleApply = roleApplyManager.get(userId, roleManager.getByCode(RequestUtil.getParam(request, "role")).getId());
		roleApply.setStatus(RoleApplyStatus.WaitAudit.getCode());
		roleApply.setFeature("companyName", RequestUtil.getParam(request, "companyName"));
		roleApply.setFeature("companyNum", RequestUtil.getParam(request, "companyNum"));
		roleApply.setFeature("companyOwnerName", RequestUtil.getParam(request, "companyOwnerName"));
		roleApply.setFeature("companyOwnerId", RequestUtil.getParam(request, "companyOwnerId"));
		roleApply.setFeature("companyOwnerIdFrontImg", RequestUtil.getParam(request, "companyOwnerIdFrontImg"));
		roleApply.setFeature("companyOwnerIdBackImg", RequestUtil.getParam(request, "companyOwnerIdBackImg"));
		roleApply.setFeature("companyOwnerPhone", RequestUtil.getParam(request, "companyOwnerPhone"));
		roleApply.setFeature("companyBusinessLicense", RequestUtil.getParam(request, "companyBusinessLicense"));
		roleApply.setFeature("zzjgdmzId", RequestUtil.getParam(request, "zzjgdmzId"));
		roleApply.setFeature("swdjzId", RequestUtil.getParam(request, "swdjzId"));
		roleApply.setFeature("companyOwnerIdFront", RequestUtil.getParam(request, "companyOwnerIdFront"));
		roleApply.setFeature("companyOwnerIdBack", RequestUtil.getParam(request, "companyOwnerIdBack"));
		roleApply.setFeature("htzId", RequestUtil.getParam(request, "htzId"));
		roleApply.setFeature("zzsfpId", RequestUtil.getParam(request, "zzsfpId"));
		roleApply.setFeature("clzpId", RequestUtil.getParam(request, "clzpId"));
		roleApply.setFeature("driverIdFront", RequestUtil.getParam(request, "driverIdFront"));
		roleApply.setFeature("dirverIdBack", RequestUtil.getParam(request, "dirverIdBack"));
		roleApply.setFeature("jszId", RequestUtil.getParam(request, "jszId"));
		roleApply.setFeature("xxzId", RequestUtil.getParam(request, "xxzId"));
		roleApply.setFeature("dlyscyzgzId", RequestUtil.getParam(request, "dlyscyzgzId"));
		roleApply.setFeature("dlysjyxkzId", RequestUtil.getParam(request, "dlysjyxkzId"));
		roleApply.setFeature("hybxdId", RequestUtil.getParam(request, "hybxdId"));
		roleApply.setFeature("cmaId", RequestUtil.getParam(request, "cmaId"));
		roleApply.setFeature("cnasId", RequestUtil.getParam(request, "cnasId"));
		roleApply.setFeature("calId", RequestUtil.getParam(request, "calId"));
		roleApply.setFeature("rzdbxkzId", RequestUtil.getParam(request, "rzdbxkzId"));
		roleApply.setFeature("submitTime", DateUtil.formatDateTime(new Date()));
		roleApplyManager.update(roleApply);
		request.setAttribute("result", true);
		request.setAttribute("msg", "提交资料成功");
	}

	public void doUnapprove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String roleApplyId = RequestUtil.getParam(request, "roleApplyId");
		RoleApplyBO roleApply = roleApplyManager.get(roleApplyId);
		if (!roleApply.getStatus().equals(RoleApplyStatus.WaitAudit.getCode())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "申请认证状态异常");
		}
		String msg = RequestUtil.getParam(request, "msg");
		roleApply.setStatus(RoleApplyStatus.unapprove.getCode());
		roleApply.setFeature("msg", msg);
		roleApplyManager.update(roleApply);
		request.setAttribute("result", true);
		request.setAttribute("msg", "驳回成功");
	}

	public void doApprove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long roleApplyId = RequestUtil.getParam(request, "roleApplyId", Long.class);
		RoleApplyBO roleApply = roleApplyManager.get(roleApplyId);
		roleManager.fill(roleApply);
		if (!roleApply.getStatus().equals(RoleApplyStatus.WaitAudit.getCode())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "申请认证状态异常");
		}
		roleApply.setStatus(RoleApplyStatus.WaitFreezeDeposit.getCode());
		roleApplyManager.update(roleApply);
		request.setAttribute("result", true);
		request.setAttribute("msg", "批准成功");
	}

	public RoleApplyBO getBO(HttpServletRequest request) {
		RoleApplyBO roleApplyBO = getParameter(request);
		return roleApplyBO;
	}

	public static RoleApplyBO getParameter(HttpServletRequest request) {
		RoleApplyBO roleApplyBO = new RoleApplyBO();
		roleApplyBO.setId(RequestUtil.getParam(request, "id", Long.class));
		roleApplyBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		roleApplyBO.setRoleId(RequestUtil.getParam(request, "roleId", Long.class));
		roleApplyBO.setStatus(RequestUtil.getParam(request, "status"));
		roleApplyBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, roleApplyBO);
		return roleApplyBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return roleApplyManager;
	}

}
