package com.intkr.saas.module.action.conf.theme;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.conf.Conf;
import com.intkr.saas.domain.bo.saas.SaasClientBO;
import com.intkr.saas.domain.bo.saas.SaasConfigBO;
import com.intkr.saas.domain.bo.saas.SaasDomainBO;
import com.intkr.saas.domain.bo.saas.SaasThemeBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.saas.SaasClientManager;
import com.intkr.saas.manager.saas.SaasConfigManager;
import com.intkr.saas.manager.saas.SaasDomainManager;
import com.intkr.saas.manager.saas.SaasThemeManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class SaasThemeAction extends BaseAction<SaasThemeBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static SaasClientManager saasClientManager = IOC.get(SaasClientManager.class);

	private static SaasConfigManager appConfigManager = IOC.get(SaasConfigManager.class);

	private static SaasDomainManager saasDomainManager = IOC.get(SaasDomainManager.class);

	private static SaasThemeManager themeManager = IOC.get(SaasThemeManager.class);

	public static final String previewTheme = "_previewTheme";

	public static final String _theme_name = "_theme";

	public static String getThemeName(HttpServletRequest request) {
		if (!Conf.useTheme()) {
			return null;
		}
		if (CmsPreviewThemeAction.hasPreviewTheme(request)) {
			return CmsPreviewThemeAction.getThemeName(request);
		}
		SaasClientBO saas = SessionClient.getSaas(request);
		if (saas.getDomains().size() == 0) {
			return saas.getConfig().getThemeName();
		} else if (saas.getDomains().size() == 1 && saas.getConfig() != null) {
			return saas.getConfig().getThemeName();
		} else if (saas.getDomains().size() == 1) {
			if (saas.getTheme() == null) {
				return null;
			}
			return saas.getTheme().getName();
		} else {
			String domain = RequestUtil.getDomain(request);
			for (SaasDomainBO domainBO : saas.getDomains()) {
				if (domain.equals(domainBO.getDomain()) && //
						domainBO.getThemeId() != null && //
						domainBO.getThemeName() != null && //
						!"".equals(domainBO.getThemeName())) {
					return domainBO.getThemeName();
				}
			}
			SaasDomainBO domainBO = saas.getDomains().get(0);
			if (domain.equals(domainBO.getDomain()) && //
					domainBO.getThemeId() != null && //
					domainBO.getThemeName() != null && //
					!"".equals(domainBO.getThemeName())) {
				return domainBO.getThemeName();
			}
			if (domainBO.getThemeId() != null && //
					domainBO.getThemeName() != null && //
					!"".equals(domainBO.getThemeName())) {
				return domainBO.getThemeName();
			}
		}
		return saas.getConfig().getThemeName();
	}

	public static String getPreviewThemeName(HttpServletRequest request) {
		if (!Conf.useTheme()) {
			return null;
		}
		if (CmsPreviewThemeAction.hasPreviewTheme(request)) {
			return CmsPreviewThemeAction.getThemeName(request);
		}
		return null;
	}

	public SaasThemeBO getBO(HttpServletRequest request) {
		SaasThemeBO cmsThemeBO = getParameter(request);
		return cmsThemeBO;
	}

	public static SaasThemeBO getParameter(HttpServletRequest request) {
		SaasThemeBO themeBO = new SaasThemeBO();
		themeBO.setId(RequestUtil.getParam(request, "id", Long.class));
		themeBO.setCover(RequestUtil.getParam(request, "cover"));
		themeBO.setName(RequestUtil.getParam(request, "name"));
		themeBO.setStatus(RequestUtil.getParam(request, "status"));
		themeBO.setNote(RequestUtil.getParam(request, "note"));
		themeBO.setFeature(RequestUtil.getParam(request, "feature"));
		themeBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		if (RequestUtil.existParam(request, "searchWord")) {
			themeBO.setQuery("searchWord", RequestUtil.getParam(request, "searchWord"));
			themeBO.setQuery("searchWordSQL", "%" + RequestUtil.getParam(request, "searchWord") + "%");
		}
		PagerUtil.initPage(request, themeBO);
		return themeBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return themeManager;
	}

	public static void doSetTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (RequestUtil.existParam(request, "themeId")) {
			Long themeId = RequestUtil.getParam(request, "themeId", Long.class);
			SaasThemeBO theme = themeManager.get(themeId);
			setTheme(request, theme);
		} else {
			String themeName = RequestUtil.getParam(request, "themeName");
			SaasThemeBO theme = themeManager.getByName(themeName);
			setTheme(request, theme);
		}
	}

	public static void doSetAllTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (RequestUtil.existParam(request, "themeId")) {
			Long themeId = RequestUtil.getParam(request, "themeId", Long.class);
			SaasThemeBO theme = themeManager.get(themeId);
			setAllTheme(request, theme);
		} else {
			String themeName = RequestUtil.getParam(request, "themeName");
			SaasThemeBO theme = themeManager.getByName(themeName);
			setAllTheme(request, theme);
		}
	}

	private static void setTheme(HttpServletRequest request, SaasThemeBO theme) {
		SaasClientBO saas = SessionClient.getSaas(request);
		SaasConfigBO config = appConfigManager.getBySaasId(saas.getId());
		if (config == null) {
			config = new SaasConfigBO();
			config.setId(appConfigManager.getId());
			config.setSaasId(saas.getId());
			config.setThemeId(theme.getId());
			config.setThemeName(theme.getName());
			appConfigManager.insert(config);
			saas = saasClientManager.getFull(saas.getId());
		} else {
			config.setThemeId(theme.getId());
			config.setThemeName(theme.getName());
			appConfigManager.update(saas.getConfig());
		}
		String domainString = RequestUtil.getDomain(request);
		for (SaasDomainBO domain : saas.getDomains()) {
			if (domainString.equals(domain.getDomain())) {
				domain.setThemeId(theme.getId());
				domain.setThemeName(theme.getName());
				saasDomainManager.update(domain);
			}
		}
		saas = saasClientManager.getFull(saas.getId());
		request.getSession().setAttribute("_saas", saas);
		request.getSession().setAttribute("_cmsThemeBO", saas.getTheme());
		request.getSession().setAttribute("_cmsTheme", saas.getTheme().getName());
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

	private static void setAllTheme(HttpServletRequest request, SaasThemeBO theme) {
		Long saasId = SessionClient.getSaasId(request);
		SaasClientBO saas = SessionClient.getSaas(request);
		SaasConfigBO config = appConfigManager.getBySaasId(saasId);
		if (config == null) {
			config = new SaasConfigBO();
			config.setId(appConfigManager.getId());
			config.setSaasId(saasId);
			config.setThemeId(theme.getId());
			config.setThemeName(theme.getName());
			appConfigManager.insert(config);
			saas = saasClientManager.getFull(saasId);
		} else {
			config.setThemeId(theme.getId());
			config.setThemeName(theme.getName());
			appConfigManager.update(saas.getConfig());
		}
		for (SaasDomainBO domain : saas.getDomains()) {
			domain.setThemeId(theme.getId());
			domain.setThemeName(theme.getName());
			saasDomainManager.update(domain);
		}
		saas = saasClientManager.getFull(saasId);
		request.getSession().setAttribute("_saas", saas);
		request.getSession().setAttribute("_cmsThemeBO", saas.getTheme());
		request.getSession().setAttribute("_cmsTheme", saas.getTheme().getName());
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

}
