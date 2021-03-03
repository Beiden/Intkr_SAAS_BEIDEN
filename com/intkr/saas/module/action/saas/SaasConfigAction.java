package com.intkr.saas.module.action.saas;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.saas.SaasConfigBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.saas.SaasConfigManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-5-7 下午12:46:55
 * @version 1.0
 */
public class SaasConfigAction extends BaseAction<SaasConfigBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SaasConfigManager appDomainManager = IOC.get(SaasConfigManager.class);

	public BaseManager getBaseManager() {
		return appDomainManager;
	}

	public static SaasConfigBO getParameter(HttpServletRequest request) {
		SaasConfigBO saas = new SaasConfigBO();
		saas.setId(RequestUtil.getParam(request, "id", Long.class));
		saas.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		saas.setThemeId(RequestUtil.getParam(request, "themeId", Long.class));
		saas.setThemeName(RequestUtil.getParam(request, "themeName"));
		saas.setFeature(RequestUtil.getParam(request, "feature"));
		saas.setNote(RequestUtil.getParam(request, "note"));
		PagerUtil.initPage(request, saas);
		return saas;
	}

	public SaasConfigBO getBO(HttpServletRequest request) {
		return getParameter(request);
	}

}
