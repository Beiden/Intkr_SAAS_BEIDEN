package com.intkr.saas.module.action.conf.theme;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.conf.CmsConf;
import com.intkr.saas.conf.Conf;
import com.intkr.saas.domain.bo.saas.SaasThemeBO;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.saas.SaasThemeManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-5-7 下午12:46:55
 * @version 1.0
 */
public class CmsThemeAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static String cms_theme = null;

	private static OptionManager optionManager = IOC.get(OptionManager.class);

	private static SaasThemeManager themeManager = IOC.get(SaasThemeManager.class);

	public static String getPreviewThemeName(HttpServletRequest request) {
		if (!Conf.useTheme()) {
			return null;
		}
		if (CmsPreviewThemeAction.hasPreviewTheme(request)) {
			return CmsPreviewThemeAction.getThemeName(request);
		}
		return null;
	}

	public static String getThemeName(HttpServletRequest request) {
		if (!Conf.useTheme()) {
			return null;
		}
		if (CmsPreviewThemeAction.hasPreviewTheme(request)) {
			return CmsPreviewThemeAction.getThemeName(request);
		}
		return getTheme();
	}

	private static String getTheme() {
		if (cms_theme == null) {
			initThemeByDB();
		}
		return CmsThemeAction.cms_theme;
	}

	private static void initThemeByDB() {
		String theme = optionManager.getValueByName(-1l, CmsConf.theme);
		if (theme == null) {
			theme = "Startup";
		}
		CmsThemeAction.cms_theme = theme;
	}

	public static void doSetTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (RequestUtil.existParam(request, "themeId")) {
			setThemeById(request);
		} else {
			setThemeByName(request);
		}
	}

	private static void setThemeByName(HttpServletRequest request) {
		String themeName = RequestUtil.getParam(request, "themeName");
		CmsThemeAction.cms_theme = themeName;
		optionManager.updateOrInsert(-1l, CmsConf.theme, themeName);
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

	private static void setThemeById(HttpServletRequest request) {
		Long themeId = RequestUtil.getParam(request, "themeId", Long.class);
		SaasThemeBO theme = themeManager.get(themeId);
		CmsThemeAction.cms_theme = theme.getName();
		optionManager.updateOrInsert(-1l, CmsConf.theme, theme.getName());
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

}
