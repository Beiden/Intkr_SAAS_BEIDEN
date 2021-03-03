package com.intkr.saas.module.screen.saas.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.saas.SaasThemeBO;
import com.intkr.saas.manager.saas.SaasThemeManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class SaasThemeAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SaasThemeManager manager = IOC.get(SaasThemeManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String saasThemeId = RequestUtil.getParam(request, "saasThemeId");
		SaasThemeBO saasTheme = manager.get(saasThemeId);
		request.setAttribute("saasTheme", saasTheme);
		request.setAttribute("addId", manager.getId());
	}

}
