package com.intkr.saas.engine.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.engine.UrlRewriteEngine;

/**
 * 权限引擎
 * 
 * @author Beiden
 * @date 2016-5-24 下午2:55:31
 * @version 1.0
 */
public class ScreenRightEngine {

	public static boolean hasRight(HttpServletRequest request, HttpServletResponse response) {
		String uri = UrlRewriteEngine.getRealTarget(request);
		if (uri == null || "".equals(uri)) {
			return true;
		}
		if (OpaRightEngine.isOpa(request, response)) {
			return OpaRightEngine.hasRight(request, response);
		}
		AuthRule rule = AuthorityEngine.findByUri(uri);
		if (rule == null) {// 没有规则
			return true;
		} else if ("anon".equals(rule.getConf())) {
			return true;
		} else if ("authc".equals(rule.getConf())) {
			boolean result = SessionClient.isLogin(request);
			if (!result) {
				AuthorityEngine.redirectLogin(request, response, rule);
				return false;
			}
			return true;
		} else if ("perms".equals(rule.getConf())) {
			boolean hasLoginResult = SessionClient.isLogin(request);
			if (!hasLoginResult) {
				AuthorityEngine.redirectLogin(request, response, rule);
				return false;
			}
			boolean hasRightResult = AuthorityEngine.hasRight(uri, SessionClient.getLoginUser(request));
			if (!hasRightResult) {
				request.setAttribute("uri", uri);
			}
			return hasRightResult;
		} else if (rule.getConf().startsWith("perms[")) {
			boolean hasLoginResult = SessionClient.isLogin(request);
			if (!hasLoginResult) {
				AuthorityEngine.redirectLogin(request, response, rule);
				return false;
			}
			List<String> perms = rule.getPerms();
			for (String perm : perms) {
				boolean result = AuthorityEngine.hasRight(perm, SessionClient.getLoginUser(request));
				if (result) {
					return true;
				}
			}
			request.setAttribute("uri", rule.getConf());
			return false;
		} else if (rule.getConf().startsWith("roles[")) {
			boolean hasLoginResult = SessionClient.isLogin(request);
			if (!hasLoginResult) {
				AuthorityEngine.redirectLogin(request, response, rule);
				return false;
			}
			List<String> roles = rule.getRoles();
			if (roles != null) {
				for (String role : roles) {
					boolean result = AuthorityEngine.hasRole(role, SessionClient.getLoginUser(request));
					if (result) {
						return true;
					}
				}
			}
			request.setAttribute("uri", uri + ":" + rule.getConf());
			return false;
		} else if (rule.getConf().startsWith("permsa[")) {
			boolean hasLoginResult = SessionClient.isLogin(request);
			if (!hasLoginResult) {
				AuthorityEngine.redirectLogin(request, response, rule);
				return false;
			}
			List<String> permsa = rule.getPermsa();
			for (String perm : permsa) {
				boolean result = AuthorityEngine.hasRight(perm, SessionClient.getLoginUser(request));
				if (!result) {
					request.setAttribute("uri", uri + ":" + rule.getConf() + ":" + perm);
					return false;
				}
			}
			return true;
		} else if (rule.getConf().startsWith("rolesa[")) {
			boolean hasLoginResult = SessionClient.isLogin(request);
			if (!hasLoginResult) {
				AuthorityEngine.redirectLogin(request, response, rule);
				return false;
			}
			List<String> rolesa = rule.getRolesa();
			for (String role : rolesa) {
				boolean result = AuthorityEngine.hasRole(role, SessionClient.getLoginUser(request));
				if (!result) {
					request.setAttribute("uri", uri + ":" + rule.getConf() + ":" + role);
					return false;
				}
			}
			return true;
		}
		return true;// 没有配置就等于拥有权限
	}

}
