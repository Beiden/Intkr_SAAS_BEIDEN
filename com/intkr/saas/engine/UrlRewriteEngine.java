package com.intkr.saas.engine;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.TurbineRunDataInternal;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.intkr.saas.engine.theme.ThemeEngine;
import com.intkr.saas.module.action.conf.theme.ThemeAction;

/**
 * 
 * @author Beiden
 * @date 2017-10-6
 * @version 1.0
 */
public class UrlRewriteEngine {

	private static final String _redirect = "_redirect";

	public static boolean hasRedirect(HttpServletRequest request) {
		return request.getAttribute(_redirect) != null;
	}

	public static void redirect(HttpServletRequest request, String target) {
		if (UrlRewriteEngine.hasRedirect(request)) {
			return;
		}
		TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
		runData.setRedirectTarget(target);
		TurbineRunDataInternal runDataInt = ((TurbineRunDataInternal) runData);
		runDataInt.setAction(null);
		runDataInt.setActionEvent(null);
		request.setAttribute("_redirect", true);
	}

	public static boolean needRewrite(HttpServletRequest request) {
		TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
		String target = runData.getTarget();
		if (target.startsWith("/_install/")) {
			return false;
		}
		if (target.contains(".")) {// .do 图片类
			return false;
		}
		if (target.contains("-param-") || target.contains("-p-")) {
			return true;
		}
		return ThemeEngine.needThemeRewrite(request);
	}

	public static String getTarget(HttpServletRequest request) {
		TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
		String target = runData.getTarget();
		return target;
	}

	public static String getRealTarget(HttpServletRequest request) {
		TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
		String target = runData.getTarget();
		if (UrlRewriteEngine.needRewrite(request)) {
			if (target.contains("-param-")) {
				target = target.substring(0, target.indexOf("-param-"));
			} else if (target.contains("-p-")) {
				target = target.substring(0, target.indexOf("-p-"));
			}
		}
		if (ThemeEngine.needThemeRewrite(request)) {
			target = "/themes/" + ThemeAction.getThemeName(request) + target;
		}
		return target;
	}

}
