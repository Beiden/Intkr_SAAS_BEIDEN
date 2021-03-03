package com.intkr.saas.engine.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.util.RequestUtil;

/**
 * 权限引擎
 * 
 * @author Beiden
 * @date 2016-5-24 下午2:55:31
 * @version 1.0
 */
public class ActionRightEngine {

	public static boolean hasAction(HttpServletRequest request, HttpServletResponse response) {
		TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
		String actopm = runData.getAction();
		String actionEvent = runData.getActionEvent();
		if (actopm != null || actionEvent != null) {
			return true;
		}
		return false;
	}

	public static boolean hasRight(HttpServletRequest request, HttpServletResponse response) {
		if (!hasAction(request, response)) {
			return true;
		}
		TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
		String action = runData.getAction();
		String actionEvent = runData.getActionEvent();
		action = action + "." + actionEvent;
		AuthRule rule = AuthorityEngine.findByAction(action);
		if (rule == null) {// 没有规则
			return true;
		} else if ("anon".equals(rule.getConf())) {
			return true;
		} else if ("authc".equals(rule.getConf())) {
			boolean result = SessionClient.isLogin(request);
			if (!result) {
				if (!RequestUtil.isJsonResponse(request)) {
					AuthorityEngine.redirectLogin(request, response, rule);
				} else {
					request.setAttribute("msg", "你没有权限!");
					request.setAttribute("result", false);
				}
				return false;
			}
			return true;
		} else if ("perms".equals(rule.getConf())) {
			boolean hasLoginResult = SessionClient.isLogin(request);
			if (!hasLoginResult) {
				if (!RequestUtil.isJsonResponse(request)) {
					AuthorityEngine.redirectLogin(request, response, rule);
				} else {
					request.setAttribute("msg", "你没有权限!");
					request.setAttribute("result", false);
				}
				return false;
			}
			boolean result = AuthorityEngine.hasRight(action, SessionClient.getLoginUser(request));
			if (!result) {
				request.setAttribute("actionMethod", action);
			}
			return result;
		} else if (rule.getConf().startsWith("perms[")) {
			boolean hasLoginResult = SessionClient.isLogin(request);
			if (!hasLoginResult) {
				if (!RequestUtil.isJsonResponse(request)) {
					AuthorityEngine.redirectLogin(request, response, rule);
				} else {
					request.setAttribute("msg", "你没有权限!");
					request.setAttribute("result", false);
				}
				return false;
			}
			List<String> perms = rule.getPerms();
			for (String perm : perms) {
				boolean result = AuthorityEngine.hasRight(perm, SessionClient.getLoginUser(request));
				if (result) {
					return true;
				}
			}
			request.setAttribute("actionMethod", action + ":" + rule.getConf());
			return false;
		} else if (rule.getConf().startsWith("roles[")) {
			boolean hasLoginResult = SessionClient.isLogin(request);
			if (!hasLoginResult) {
				if (!RequestUtil.isJsonResponse(request)) {
					AuthorityEngine.redirectLogin(request, response, rule);
				} else {
					request.setAttribute("msg", "你没有权限!");
					request.setAttribute("result", false);
				}
				return false;
			}
			List<String> roles = rule.getRoles();
			for (String role : roles) {
				boolean result = AuthorityEngine.hasRole(role, SessionClient.getLoginUser(request));
				if (result) {
					return true;
				}
			}
			request.setAttribute("actionMethod", action + ":" + rule.getConf());
			return false;
		} else if (rule.getConf().startsWith("permsa[")) {
			boolean hasLoginResult = SessionClient.isLogin(request);
			if (!hasLoginResult) {
				if (!RequestUtil.isJsonResponse(request)) {
					AuthorityEngine.redirectLogin(request, response, rule);
				} else {
					request.setAttribute("msg", "你没有权限!");
					request.setAttribute("result", false);
				}
				return false;
			}
			List<String> permsa = rule.getPermsa();
			for (String perm : permsa) {
				boolean result = AuthorityEngine.hasRight(perm, SessionClient.getLoginUser(request));
				if (!result) {
					request.setAttribute("actionMethod", action + ":" + rule.getConf() + ":" + perm);
					return false;
				}
			}
			return true;
		} else if (rule.getConf().startsWith("rolesa[")) {
			boolean hasLoginResult = SessionClient.isLogin(request);
			if (!hasLoginResult) {
				if (!RequestUtil.isJsonResponse(request)) {
					AuthorityEngine.redirectLogin(request, response, rule);
				} else {
					request.setAttribute("msg", "你没有权限!");
					request.setAttribute("result", false);
				}
				return false;
			}
			List<String> rolesa = rule.getRolesa();
			for (String role : rolesa) {
				boolean result = AuthorityEngine.hasRole(role, SessionClient.getLoginUser(request));
				if (!result) {
					request.setAttribute("actionMethod", action + ":" + rule.getConf() + ":" + role);
					return false;
				}
			}
			return true;
		}
		return true;// 没有配置就等于拥有权限
	}

}
