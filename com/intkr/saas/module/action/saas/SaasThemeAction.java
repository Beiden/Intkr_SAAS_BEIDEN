package com.intkr.saas.module.action.saas;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.saas.SaasThemeBO;
import com.intkr.saas.manager.BaseManager;
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

	private SaasThemeManager themeManager = IOC.get(SaasThemeManager.class);

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

}
