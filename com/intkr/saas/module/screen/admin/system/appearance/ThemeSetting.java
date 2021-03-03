package com.intkr.saas.module.screen.admin.system.appearance;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.conf.CmsConf;
import com.intkr.saas.conf.SystemProperties;

/**
 * 
 * @author Beiden
 * @date 2011-10-8 下午2:32:55
 * @version 1.0
 */
public class ThemeSetting {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		File file = new File(SystemProperties.getWebappPath() + "/IK/templates/screen/themes/" + SessionClient.getTheme(request) + "/control/themeSetting.vm");
		if (file.exists()) {
			request.setAttribute("hasThemeSetting", "menuSetting");
		}
		request.setAttribute(CmsConf.theme, SessionClient.getTheme(request));
	}

}
