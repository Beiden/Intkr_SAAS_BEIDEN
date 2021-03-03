package com.intkr.saas.engine.theme;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.intkr.saas.conf.Conf;
import com.intkr.saas.module.action.conf.theme.ThemeAction;

/**
 * 
 * @author Beiden
 */
public class ThemeEngine {

	public static boolean needThemeRewrite(HttpServletRequest request) {
		if (!Conf.useTheme()) {
			return false;
		}
		TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
		String target = runData.getTarget();
		if (!target.startsWith("/admin/") //
				&& !target.startsWith("/saas/")//
				&& !target.startsWith("/common/jsonResult")) {// 主题重定向
			return true;
		}
		return false;
	}

	public static String getThemeName(HttpServletRequest request) {
		return ThemeAction.getThemeName(request);
	}

	public static String getPreviewThemeName(HttpServletRequest request) {
		return ThemeAction.getPreviewThemeName(request);
	}

	public void doSetTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	}

	public void doSetAllTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	}

	public void doSetPreviewTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	}

}
