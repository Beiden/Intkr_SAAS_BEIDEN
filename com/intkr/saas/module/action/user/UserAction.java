package com.intkr.saas.module.action.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.bo.user.UserRightBO;
import com.intkr.saas.domain.bo.user.UserRoleBO;
import com.intkr.saas.domain.type.user.UserStatus;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.UserRightManager;
import com.intkr.saas.manager.user.UserRoleManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.MD5Util;
import com.intkr.saas.util.MoneyUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-24 下午03:44:12
 * @version 1.0
 */
public class UserAction extends BaseAction<UserBO> {

	protected UserManager accountManager = IOC.get(UserManager.class);

	protected UserRoleManager userRoleManager = IOC.get(UserRoleManager.class);

	protected UserRightManager userRightManager = IOC.get(UserRightManager.class);

	public UserBO getBO(HttpServletRequest request) {
		UserBO userBO = getParameter(request);
		return userBO;
	}

	public static UserBO getParameter(HttpServletRequest request) {
		UserBO userBO = new UserBO();
		userBO.setId(RequestUtil.getParam(request, "id", Long.class));
		userBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		userBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		userBO.setSaasRole(RequestUtil.getParam(request, "saasRole"));
		userBO.setNickName(RequestUtil.getParam(request, "nickName"));
		userBO.setAvatar(RequestUtil.getParam(request, "avatar"));
		userBO.setAccount(RequestUtil.getParam(request, "account"));
		userBO.setPassword(RequestUtil.getParam(request, "password"));
		userBO.setSecurePassword(RequestUtil.getParam(request, "securePassword"));
		userBO.setFeature(RequestUtil.getParam(request, "feature"));
		userBO.setStatus(RequestUtil.getParam(request, "status"));
		userBO.setEmail(RequestUtil.getParam(request, "email"));
		userBO.setPhone(RequestUtil.getParam(request, "phone"));
		userBO.setQq(RequestUtil.getParam(request, "qq", Long.class));
		userBO.setMoney(MoneyUtil.parse(RequestUtil.getParam(request, "money")));
		PagerUtil.initPage(request, userBO);
		if (RequestUtil.existParam(request, "feature-keys")) {
			String[] keys = request.getParameterValues("feature-keys");
			if (keys != null) {
				for (String key : keys) {
					userBO.setFeature(key, RequestUtil.getParam(request, key));
				}
			}
		}
		return userBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return accountManager;
	}

	public void doSelectOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserBO query = getParameter(request);
		UserBO user = accountManager.selectOne(query);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功！");
		request.setAttribute("data", user);
	}

	public void doGetByAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String account = RequestUtil.getParam(request, "account");
		UserBO user = accountManager.getByAccount(SessionClient.getSaasId(request), account);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功！");
		request.setAttribute("data", user);
	}

	public void doUpdateAvatar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String avatar = RequestUtil.getParam(request, "avatar");
		UserBO user = accountManager.get(SessionClient.getLoginUserId(request));
		user.setAvatar(avatar);
		accountManager.update(user);
		SessionClient.login(request, response, user.getId());
		request.setAttribute("result", true);
		request.setAttribute("msg", "更新头像成功");
	}

	/**
	 * 授权
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doAuth(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long userId = RequestUtil.getParam(request, "userId", Long.class);
		List<UserRoleBO> oldRoles = userRoleManager.selectByUserId(userId);
		String[] newRoleIds = request.getParameterValues("roleIds");
		if (newRoleIds == null) {
			newRoleIds = new String[0];
		}
		for (String newRoleId : newRoleIds) {
			if (!containNew(oldRoles, Long.valueOf(newRoleId))) {
				userRoleManager.addRole(userId, Long.valueOf(newRoleId));
			}
		}
		for (UserRoleBO oldRight : oldRoles) {
			if (!containOld(newRoleIds, oldRight)) {
				userRoleManager.delete(oldRight.getId());
			}
		}
		processRight(request, userId);
		request.setAttribute("result", true);
		request.setAttribute("msg", "授权成功");
	}

	private void processRight(HttpServletRequest request, Long userId) {
		String[] newRightIds = request.getParameterValues("rightIds");
		if (newRightIds == null) {
			newRightIds = new String[0];
		}
		List<UserRightBO> oldAuthList = userRightManager.selectByUserId(userId);
		for (String newRightId : newRightIds) {
			if (!containNew(oldAuthList, Long.valueOf(newRightId))) {
				UserRightBO newAuth = new UserRightBO();
				newAuth.setId(userRightManager.getId());
				newAuth.setRightId(Long.valueOf(newRightId));
				newAuth.setUserId(userId);
				userRightManager.insert(newAuth);
			}
		}
		for (UserRightBO oldRight : oldAuthList) {
			if (!containOld(newRightIds, oldRight)) {
				userRightManager.delete(oldRight.getId());
			}
		}
	}

	private boolean containOld(String[] newRightIds, UserRoleBO oldRight) {
		for (String newRightId : newRightIds) {
			if (oldRight.getRoleId().equals(Long.valueOf(newRightId))) {
				return true;
			}
		}
		return false;
	}

	private boolean containOld(String[] newRightIds, UserRightBO oldRight) {
		for (String newRightId : newRightIds) {
			if (oldRight.getRightId().equals(Long.valueOf(newRightId))) {
				return true;
			}
		}
		return false;
	}

	private boolean containNew(List<?> oldAuthList, Long newRight) {
		for (Object obj : oldAuthList) {
			if (obj instanceof UserRoleBO) {
				UserRoleBO oldRight = (UserRoleBO) obj;
				if (oldRight.getRoleId().equals(newRight)) {
					return true;
				}
			} else {
				UserRightBO oldRight = (UserRightBO) obj;
				if (oldRight.getRightId().equals(newRight)) {
					return true;
				}
			}
		}
		return false;
	}

	public void doInitSecurePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!RequestUtil.existParam(request, "securePassword")) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请输入安全密码！");
			return;
		}
		String securePassword = RequestUtil.getParam(request, "securePassword");
		UserBO user = accountManager.get(SessionClient.getLoginUserId(request));
		user.setSecurePassword(securePassword);
		user.encryptSecurePassword();
		accountManager.update(user);
		SessionClient.login(request, response, user.getId());
		request.setAttribute("result", true);
		request.setAttribute("msg", "修改成功！");
	}

	public void doUpdateBaseInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserBO userBO = SessionClient.getLoginUser(request);
		userBO.setNickName(RequestUtil.getParam(request, "nickName"));
		userBO.setPhone(RequestUtil.getParam(request, "phone"));
		userBO.setQq(RequestUtil.getParam(request, "qq", Long.class, 0L));
		accountManager.update(userBO);
		request.setAttribute("result", true);
		request.setAttribute("msg", "修改成功！");
	}

	public void doUpdatePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String password = RequestUtil.getParam(request, "password");
		String newPassword = RequestUtil.getParam(request, "newPassword");
		if (password == null || "".equals(password)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请输入原密码！");
			return;
		}
		UserBO user = SessionClient.getLoginUser(request);
		UserBO userBO = accountManager.get(user.getId());
		if (!userBO.isPasswordEquals(password)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "原密码输入错误，请重新输入！");
			return;
		}
		String randomString = MD5Util.getRandomAdd();
		String enpassword = MD5Util.encrypt(newPassword, randomString);
		userBO.setPassword(enpassword + "|" + randomString);
		accountManager.update(userBO);
		request.setAttribute("result", true);
		request.setAttribute("msg", "修改密码成功！");
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			UserBO userBO = getBO(request);
			if (userBO.getStatus() == null) {
				userBO.setStatus(UserStatus.Normal.getCode());
			}
			if (userBO.getPassword() != null && !userBO.getPassword().contains("|")) {
				userBO.encryptPassword();
			}
			if (userBO.getSecurePassword() != null && !userBO.getSecurePassword().contains("|")) {
				userBO.encryptSecurePassword();
			}
			if (userBO.getNickName() == null) {
				userBO.setNickName(userBO.getAccount());
			}
			if (userBO.getAvatar() == null) {
				userBO.setAvatar("/asset/img/avatar/1001.png");
			}
			if (userBO.getEmail() == null) {
				userBO.setEmail("test@intkr.com");
			}
			if (userBO.getPhone() == null) {
				userBO.setPhone("10000000000");
			}
			if (userBO.getQq() == null) {
				userBO.setQq(1L);
			}
			if (userBO.getMoney() == null) {
				userBO.setMoney(1L);
			}
			if (userBO.getHasSecureQuestion() == null) {
				userBO.setHasSecureQuestion(2);
			}
			Long saasId = RequestUtil.getParam(request, "saasId", Long.class);
			if (accountManager.getByAccount(saasId, userBO.getAccount()) != null) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "帐号已被使用！");
				return;
			}
			if (UserStatus.Normal.getCode().equals(userBO.getStatus())) {
				userBO.setIsIdentity(1);
			} else {
				userBO.setIsIdentity(2);
			}
			long id = accountManager.insert(userBO);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", userBO.getId());
			request.setAttribute("result", true);
			request.setAttribute("data", data);
			request.setAttribute("msg", "添加成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			UserBO userBO = getBO(request);
			if (userBO.getPassword() != null && !userBO.getPassword().contains("|")) {
				userBO.encryptPassword();
			}
			if (userBO.getSecurePassword() != null && !userBO.getSecurePassword().contains("|")) {
				userBO.encryptSecurePassword();
			}
			Long saasId = RequestUtil.getParam(request, "saasId", Long.class);
			UserBO userBOOld = accountManager.getByAccount(saasId, userBO.getAccount());
			if (!userBO.getAccount().equals(userBOOld.getAccount()) && accountManager.getByAccount(saasId, userBO.getAccount()) != null) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "帐号已被使用！");
				return;
			}
			accountManager.update(userBO);
			request.setAttribute("result", true);
			request.setAttribute("msg", "更新成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
