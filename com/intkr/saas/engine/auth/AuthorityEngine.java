package com.intkr.saas.engine.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.TurbineRunDataInternal;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.RoleBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.engine.UrlRewriteEngine;
import com.intkr.saas.manager.user.RightManager;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.ResponseUtil;
import com.intkr.saas.util.UrlUtil;
import com.intkr.saas.util.UserAgentUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 权限引擎
 * 
 * @author Beiden
 * @date 2016-5-24 下午2:55:31
 * @version 1.0
 */
public class AuthorityEngine {

	public static Cache<String, RoleBO> fullRoleCache = CacheBuilder.newBuilder()//
			.expireAfterAccess(30, TimeUnit.MINUTES).//
			maximumSize(1000).//
			build();

	public static RoleBO getRoleFillRight(final String roleCode) {
		try {
			RoleBO role = fullRoleCache.get(roleCode, new Callable<RoleBO>() {
				public RoleBO call() throws Exception {
					RoleManager roleManager = IOC.get("RoleManager");
					RightManager rightManager = IOC.get("RightManager");
					RoleBO role = roleManager.getByCode(roleCode);
					rightManager.fill(role);
					return role;
				}
			});
			return role;
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean hasRight(HttpServletRequest request, HttpServletResponse response) {
		if (UrlRewriteEngine.hasRedirect(request)) {
			return false;
		}
		TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
		String target = runData.getTarget();
		if (!ActionRightEngine.hasAction(request, response) && target.contains(".")) {
			return true;// .do 图片类
		}
		UserBO user = SessionClient.getLoginUser(request);
		if (SessionClient.isLogin(request) && "admin".equals(user.getSaasRole())) {// Saas的超级管理员
			return true;
		}
		return ScreenRightEngine.hasRight(request, response) && ActionRightEngine.hasRight(request, response);
	}

	public static void redirect(HttpServletRequest request) {
		if (UrlRewriteEngine.hasRedirect(request)) {
			return;
		}
		request.setAttribute("result", false);
		request.setAttribute("msg", "您没有权限");
		Map<String, Object> data = new HashMap<String, Object>();
		TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
		data.put("uri", runData.getTarget());
		data.put("action", runData.getAction() + "." + runData.getActionEvent());
		request.setAttribute("_redirect", true);
		request.setAttribute("data", data);
		if (!RequestUtil.isJsonResponse(request)) {
			runData.setRedirectTarget("/common/noRight");
		}
		TurbineRunDataInternal runDataInt = ((TurbineRunDataInternal) runData);
		runDataInt.setAction(null);
		runDataInt.setActionEvent(null);
	}

	public static boolean hasRight(String code, UserBO user) {
		if (user == null) {
			return false;
		}
		return user.getAuthority().hasRight(code);
	}

	public static boolean hasRole(String role, UserBO user) {
		if (user == null) {
			return false;
		}
		return user.getAuthority().hasRole(role);
	}

	public static AuthRule findByUri(String uri) {
		Config.init();
		for (AuthRule rule : Config.urlRightRules) {
			Pattern p = Pattern.compile(rule.getUri());
			Matcher m = p.matcher(uri);
			if (m.find()) {
				return rule;
			}
		}
		return null;
	}

	public static AuthRule findByAction(String action) {
		Config.init();
		for (AuthRule rule : Config.actionRightRules) {
			Pattern p = Pattern.compile(rule.getAction());
			Matcher m = p.matcher(action);
			if (m.find()) {
				return rule;
			}
		}
		return null;
	}

	public static void redirectLogin(HttpServletRequest request, HttpServletResponse response, AuthRule rule) {
		if (UrlRewriteEngine.hasRedirect(request)) {
			return;
		}
		if (RequestUtil.isJsonResponse(request)) {
			request.setAttribute("msg", "你没有权限!");
			request.setAttribute("result", false);
			return;
		}
		String domain = RequestUtil.getDomain(request);
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		String redirectUri = "http://" + domain + uri;
		if (queryString != null && !"".equals(queryString)) {
			String[] querys = queryString.split("&");
			for (String para : querys) {
				String[] keyValue = para.split("=");
				redirectUri = UrlUtil.addParam(redirectUri, keyValue[0], keyValue[1]);
			}
		}

		String loginUrl = Config.pcLoginUrl + "?loginRedirectUrl=" + redirectUri;
		if (rule.getFalseConf() != null) {
			loginUrl = rule.getFalseConf() + "?loginRedirectUrl=" + redirectUri;
		} else if (UserAgentUtil.isPhone(request.getHeader("User-Agent"))) {
			loginUrl = Config.appLoginUrl + "?loginRedirectUrl=" + redirectUri;
		}
		ResponseUtil.sendRedirect(request, response, loginUrl);
	}

}
