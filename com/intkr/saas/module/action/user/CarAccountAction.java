package com.intkr.saas.module.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.MoneyUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;

/**
 * 
 * @author Beiden
 * @date 2016-7-23 上午8:34:38
 * @version 1.0
 */
public class CarAccountAction extends BaseAction<UserBO> {

	private UserManager userManager = IOC.get(UserManager.class);

	public UserBO getBO(HttpServletRequest request) {
		UserBO userBO = getParameter(request);
		return userBO;
	}

	public static UserBO getParameter(HttpServletRequest request) {
		UserBO userBO = new UserBO();
		userBO.setId(RequestUtil.getParam(request, "id", Long.class));
		userBO.setNickName(RequestUtil.getParam(request, "nickName"));
		userBO.setAvatar(RequestUtil.getParam(request, "avatar"));
		userBO.setAccount(RequestUtil.getParam(request, "account"));
		userBO.setPassword(RequestUtil.getParam(request, "password"));
		userBO.setSecurePassword(RequestUtil.getParam(request, "securePassword"));
		userBO.setStatus(RequestUtil.getParam(request, "status"));
		userBO.setEmail(RequestUtil.getParam(request, "email", String.class, ""));
		userBO.setPhone(RequestUtil.getParam(request, "phone"));
		userBO.setQq(RequestUtil.getParam(request, "qq", Long.class));
		if (RequestUtil.existParam(request, "money")) {
			userBO.setMoney(MoneyUtil.parse(RequestUtil.getParam(request, "money")));
		}
		PagerUtil.initPage(request, userBO);
		return userBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return userManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserBO bo = getBO(request);
		if (!userManager.isAccountCanUse(SessionClient.getSaasId(request), bo.getAccount())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "帐号已存在！");
			return;
		}
		bo.encryptPassword();
		userManager.insert(bo);

		request.setAttribute("result", true);
		request.setAttribute("msg", "添加成功！");
		UserLogClient.log(request, "新增", "帐号", bo.getId());
	}

	public void doDeleteOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long idString = RequestUtil.getParam(request, "deleteId", Long.class);
		getBaseManager().delete(idString);
		request.setAttribute("result", true);
		request.setAttribute("msg", "删除成功！");
		UserLogClient.log(request, "删除", "帐号", idString);
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserBO old = userManager.get(RequestUtil.getParam(request, "id"));
		UserBO bo = getBO(request);
		if (!old.getAccount().equals(bo.getAccount()) && !userManager.isAccountCanUse(SessionClient.getSaasId(request), bo.getAccount())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "帐号已存在！");
			return;
		}
		if (!old.getPassword().equals(bo.getPassword())) {
			bo.encryptPassword();
		}
		userManager.update(bo);
		request.setAttribute("result", true);
		request.setAttribute("msg", "更新成功！");
		UserLogClient.log(request, "编辑", "帐号", old.getId());
	}

}
