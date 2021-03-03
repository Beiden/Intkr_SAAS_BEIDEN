package com.intkr.saas.module.action.conf.theme;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.conf.Conf;
import com.intkr.saas.util.RequestUtil;

/**
 * 
 * @author Beiden
 * @date 2011-5-7 下午12:46:55
 * @version 1.0
 */
public class CmsPreviewThemeAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String previewTheme = "_previewTheme";

	public static String getThemeName(HttpServletRequest request) {
		if (!Conf.useTheme()) {
			return null;
		}
		if (hasPreviewTheme(request)) {
			String previewTheme = getPreviewTheme(request);
			if (previewTheme == null || "".equals(previewTheme)) {
				throw new RuntimeException("预览主题异常！");
			}
			return previewTheme;
		}
		return null;
	}

	public static String getPreviewTheme(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(CmsPreviewThemeAction.previewTheme);
	}

	public static boolean hasPreviewTheme(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session.getAttribute(CmsPreviewThemeAction.previewTheme) != null;
	}

	public static void doSetPreviewTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String themeName = RequestUtil.getParam(request, "themeName");
		if (themeName != null && !"".equals(themeName)) {
			request.getSession().setAttribute(CmsPreviewThemeAction.previewTheme, themeName);
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "修改成功！");
	}

}
