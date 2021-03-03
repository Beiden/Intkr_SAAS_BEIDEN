package com.intkr.saas.valve;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.conf.Conf;
import com.intkr.saas.domain.bo.saas.SaasClientBO;
import com.intkr.saas.domain.bo.saas.SaasDomainBO;
import com.intkr.saas.manager.saas.SaasClientManager;
import com.intkr.saas.manager.saas.SaasDomainManager;
import com.intkr.saas.module.action.conf.theme.ThemeAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-10-3 上午11:12:35
 * @version 1.0
 */
public class SaasValve implements Valve {

	public static ThreadLocal<SaasClientBO> saas = new ThreadLocal<SaasClientBO>();

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SaasClientManager saasClientManager = IOC.get(SaasClientManager.class);

	private SaasDomainManager appDomainManager = IOC.get(SaasDomainManager.class);

	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			SaasClientBO saas = null;
			SaasDomainBO appDomain = appDomainManager.getByDomain(RequestUtil.getDomain(request));
			if (appDomain != null) {
				saas = saasClientManager.getFullByDomain(appDomain.getDomain());
			} else {
				saas = saasClientManager.getDefaultFull();
				if (saas != null && saas.getDomains() != null && !saas.getDomains().isEmpty()) {
					appDomain = saas.getDomains().get(0);
				}
			}

			request.getSession().setAttribute("_saas", saas);
			request.getSession().setAttribute("_appDomain", appDomain);
			if (Conf.useTheme() && saas.getTheme() != null) {
				request.getSession().setAttribute("_cmsThemeBO", saas.getTheme());
				String themeName = ThemeAction.getPreviewThemeName(request);
				if (themeName != null && !"".equals(themeName)) {
					request.getSession().setAttribute("_cmsTheme", themeName);
				} else if (appDomain.getThemeId() != null && appDomain.getDomain() != null) {
					request.getSession().setAttribute("_cmsTheme", appDomain.getThemeName());
				} else {
					request.getSession().setAttribute("_cmsTheme", saas.getTheme().getName());
				}
			}
			SaasValve.saas.set(saas);
			return true;
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}
}