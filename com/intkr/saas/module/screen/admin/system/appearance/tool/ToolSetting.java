package com.intkr.saas.module.screen.admin.system.appearance.tool;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.conf.CmsConf;
import com.intkr.saas.conf.SystemProperties;
import com.intkr.saas.domain.type.sys.ToolType;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-10-8 下午2:32:55
 * @version 1.0
 */
public class ToolSetting {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OptionManager optionManager = IOC.get(OptionManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		File file = new File(SystemProperties.getVmPath() + "/IK/templates/screen/themes/" + SessionClient.getTheme(request) + "/control/toolSetting.vm");
		if (file.exists()) {
			request.setAttribute("hasToolSetting", true);
		}
		request.setAttribute(CmsConf.theme, SessionClient.getTheme(request));

		request.setAttribute("toolTypeList", ToolType.values());
	}

}
