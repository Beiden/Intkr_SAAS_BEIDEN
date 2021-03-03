package com.intkr.saas.module.toolbox.theme;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.domain.bo.saas.SaasThemeBO;
import com.intkr.saas.manager.saas.SaasThemeManager;
import com.intkr.saas.module.action.saas.SaasThemeAction;
import com.intkr.saas.module.toolbox.BaseToolBox;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class SaasThemeDS extends BaseToolBox {

	private SaasThemeManager cmsThemeManager = IOC.get("SaasThemeManager");

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public SaasThemeBO select(HttpServletRequest request, HttpServletResponse response) {
		SaasThemeBO query = SaasThemeAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		cmsThemeManager.selectAndCount(query);
		return query;
	}

}
