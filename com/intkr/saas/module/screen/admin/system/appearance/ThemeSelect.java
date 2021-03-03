package com.intkr.saas.module.screen.admin.system.appearance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.conf.CmsConf;
import com.intkr.saas.domain.bo.saas.SaasThemeBO;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.saas.SaasThemeManager;
import com.intkr.saas.module.action.saas.SaasThemeAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-10-2 下午5:52:57
 * @version 1.0
 */
public class ThemeSelect {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public OptionManager optionManager = IOC.get(OptionManager.class);

	private SaasThemeManager cmsThemeManager = IOC.get(SaasThemeManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(CmsConf.theme, SessionClient.getTheme(request));
		SaasThemeBO query = SaasThemeAction.getParameter(request);
		if (RequestUtil.existParam(request, "pageSize")) {
			query.set_pageSize(RequestUtil.getParam(request, "pageSize", Integer.class));
		} else {
			query.set_pageSize(9);
		}
		query.setStatus("on");
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "desc");
		query = cmsThemeManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
