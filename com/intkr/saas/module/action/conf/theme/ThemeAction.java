package com.intkr.saas.module.action.conf.theme;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.conf.Conf;

/**
 * 
 * @author Beiden
 * @date 2011-5-7 下午12:46:55
 * @version 1.0
 */
public class ThemeAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static String getThemeName(HttpServletRequest request) {
		if (!Conf.useTheme()) {
			throw new RuntimeException("系统没有启动主题模块！");
		}
		if (!Conf.isSaas()) {
			return CmsThemeAction.getThemeName(request);
		} else {
			return SaasThemeAction.getThemeName(request);
		}
	}

	public static String getPreviewThemeName(HttpServletRequest request) {
		if (!Conf.useTheme()) {
			throw new RuntimeException("系统没有启动主题模块！");
		}
		if (!Conf.isSaas()) {
			return CmsThemeAction.getPreviewThemeName(request);
		} else {
			return SaasThemeAction.getPreviewThemeName(request);
		}
	}

	public void doSetTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Conf.useTheme()) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "设置失败，系统没有启动主题模块！");
		}
		request.getSession().removeAttribute(CmsPreviewThemeAction.previewTheme);
		if (!Conf.isSaas()) {
			CmsThemeAction.doSetTheme(request, response);
		} else {
			SaasThemeAction.doSetTheme(request, response);
		}
	}

	public void doSetAllTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Conf.useTheme()) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "设置失败，系统没有启动主题模块！");
		}
		request.getSession().removeAttribute(CmsPreviewThemeAction.previewTheme);
		if (!Conf.isSaas()) {
			CmsThemeAction.doSetTheme(request, response);
		} else {
			SaasThemeAction.doSetAllTheme(request, response);
		}
	}

	public void doSetPreviewTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Conf.useTheme()) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "设置失败，系统没有启动主题模块！");
		}
		if (!Conf.isSaas()) {
			CmsPreviewThemeAction.doSetPreviewTheme(request, response);
		} else {
			SaasPreviewThemeAction.doSetPreviewTheme(request, response);
		}
	}

}
