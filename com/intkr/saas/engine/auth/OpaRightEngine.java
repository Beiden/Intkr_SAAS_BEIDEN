package com.intkr.saas.engine.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.engine.UrlRewriteEngine;
import com.intkr.saas.module.action.opa.OaClientAction;
import com.intkr.saas.util.RequestUtil;

/**
 * 鉴权
 * 
 * @author Beiden
 * @date 2016-5-24 下午4:52:44
 * @version 1.0
 */
public class OpaRightEngine {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static boolean isOpa(HttpServletRequest request, HttpServletResponse response) {
		String uri = UrlRewriteEngine.getRealTarget(request);
		if (uri == null || "".equals(uri)) {
			return true;
		}
		if (uri.startsWith("/opa/")) {
			return true;
		}
		return false;
	}

	public static boolean hasRight(HttpServletRequest request, HttpServletResponse response) {
		String uri = UrlRewriteEngine.getRealTarget(request);
		if (uri.startsWith("/opa/")) {
			TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
			String action = runData.getAction();
			if (action != null) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "没有权限调用此接口！");
				return false;
			}

			String accessToken = request.getHeader("access_token");
			if (accessToken == null) {
				accessToken = RequestUtil.getParam(request, "_access_token");
			}
			if (accessToken == null || "".equals(accessToken)) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "没有权限调用此接口！");
				return false;
			}
			Long saasId = SessionClient.getSaasId(request);
			if (!OaClientAction.isValidate(saasId, accessToken)) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "没有权限调用此接口！");
				return false;
			}
		}
		return true;
	}

}
