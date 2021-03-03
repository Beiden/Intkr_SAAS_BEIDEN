package com.intkr.saas.client.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.SignLogClient;
import com.intkr.saas.domain.bo.saas.SaasClientBO;
import com.intkr.saas.domain.bo.user.AuthorityBO;
import com.intkr.saas.domain.bo.user.RoleBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.log.SignLogType;
import com.intkr.saas.domain.type.user.UserStatus;
import com.intkr.saas.engine.CookieEngine;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.manager.user.RightManager;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.conf.theme.ThemeAction;
import com.intkr.saas.module.action.user.auth.UpperRightAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.ClassUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-4-23 下午3:11:00
 * @version 1.0
 */
public class SessionClient {

	protected static final Logger logger = LoggerFactory.getLogger(SessionClient.class);

	public static final String ikLoginToken = "ikLoginToken";

	private static final String _LoginUserInfoKey = "loginUserBO";

	private static UserManager userManager = IOC.get(UserManager.class);

	private static ShopManager shopManager = IOC.get(ShopManager.class);

	private static RoleManager roleManager = IOC.get(RoleManager.class);

	private static RightManager rightManager = IOC.get(RightManager.class);

	/**
	 * 登录
	 */
	private static boolean login(HttpServletRequest request, HttpServletResponse response, UserBO user) {
		if (user == null) {
			return false;
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		session.setAttribute(_LoginUserInfoKey, user);
		if (RequestUtil.existParam(request, "_l_u_i")) {
			Integer maxAge = RequestUtil.getParam(request, "_l_u_i", Integer.class);
			CookieEngine.setRememberLoginUserId(response, user.getId(), maxAge);
		}
		return true;
	}

	/**
	 * 登录
	 */
	public static boolean login(HttpServletRequest request, HttpServletResponse response, Long userId) {
		if (SessionClient.isLogin(request)) {// 已经登录的Session，没有注销前不能重新登录
			return false;
		}
		UserBO user = getAccount(userId);
		if (user == null) {// 帐号不存在
			return false;
		}
		if (UserStatus.Prohibit.equals(user.getStatus())) {// 帐号禁用
			return false;
		}
		shopManager.fill(user);
		SignLogClient.log(request, user, SignLogType.Login.getCode());
		return login(request, response, user);
	}

	/**
	 * 通过Cookie登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static boolean loginByCookie(HttpServletRequest request, HttpServletResponse response) {
		if (SessionClient.isLogin(request)) {// 已经登录的Session，没有注销前不能重新登录
			return false;
		}
		Long userId = CookieEngine.getRememberLoginUserId(request);
		if (userId == null || userId <= 0L) {// 不存在cookie
			return false;
		}
		boolean loginResult = SessionClient.login(request, response, userId);
		if (!loginResult) {// cookie登录失败
			CookieEngine.removeLoginUserIdKey(request, response);
		}
		return loginResult;
	}

	private static UserBO getAccount(Long userId) {
		if (userId == null || userId <= 0L) {
			return null;
		}
		UserBO user = userManager.get(userId);
		if (user == null) {
			return null;
		}
		user = roleManager.fill(user);
		user = rightManager.fill(user);
		List<RoleBO> roleList = rightManager.fill(user.getAuthority().getRoleList());
		AuthorityBO authorityBO = user.getAuthority();
		authorityBO.setRoleList(roleList);
		return user;
	}

	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获得当前登录的帐号
	 * 
	 * @param request
	 * @return
	 */
	public static UserBO getLoginUser(HttpServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		UserBO user = (UserBO) session.getAttribute(_LoginUserInfoKey);
		return user;
	}

	public static Long getLoginUserId(HttpServletRequest request) {
		UserBO user = getLoginUser(request);
		if (user != null) {
			return user.getId();
		}
		return null;
	}

	public static String getLoginUserName(HttpServletRequest request) {
		UserBO user = getLoginUser(request);
		if (user != null) {
			return user.getNickName();
		}
		return null;
	}

	/**
	 * 是否已登录
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isLogin(HttpServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		UserBO user = (UserBO) session.getAttribute(_LoginUserInfoKey);
		return user != null;
	}

	/**
	 * 注销
	 * 
	 * @param request
	 * @return
	 */
	public static boolean logout(HttpServletRequest request, HttpServletResponse response) {
		if (!isLogin(request)) {
			return true;
		}
		SignLogClient.log(request, getLoginUser(request), SignLogType.Logout.getCode());
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		session.removeAttribute(_LoginUserInfoKey);
		CookieEngine.removeLoginUserIdKey(request, response);
		return true;
	}

	public static boolean hasUpperRight(HttpServletRequest request) {
		return request.getSession().getAttribute(UpperRightAction.key) != null;
	}

	public static SaasClientBO getSaas(Object request) {
		if (request instanceof HttpServletRequest) {
			return getSaas((HttpServletRequest) request);
		} else if (request instanceof Map) {
			return getSaas((Map) request);
		}
		return null;
	}

	public static SaasClientBO getSaas(HttpServletRequest request) {
		return (SaasClientBO) request.getSession().getAttribute("_saas");
	}

	public static Long getSaasId(HttpServletRequest request, Long defaultValue) {
		Long saasId = getSaasId(request);
		if (saasId == null) {
			return defaultValue;
		}
		return saasId;
	}

	public static Long getSaasId(HttpServletRequest request) {
		if (request.getSession().getAttribute("_saas") == null) {
			return null;
		}
		return ((SaasClientBO) request.getSession().getAttribute("_saas")).getId();
	}

	public static SaasClientBO getSaas(Map request) {
		if (request.containsKey("request")) {
			return SessionClient.getSaas(request.get("request"));
		}
		return (SaasClientBO) request.get("_saas");
	}

	public static String getTheme(HttpServletRequest request) {
		return ThemeAction.getThemeName(request);
	}

	/**
	 * 登录时的Token
	 * 
	 * @return
	 */
	public static String getToken() {
		return "";
	}

	public static boolean isSaasData(HttpServletRequest request, Object obj) {
		Long saasId = getSaasId(request);
		Long objSaasId = ClassUtil.getFieldValue(obj, "saasId");
		if (objSaasId == null) {
			return false;
		}
		if (saasId.equals(objSaasId)) {
			return true;
		}
		return false;
	}

}
