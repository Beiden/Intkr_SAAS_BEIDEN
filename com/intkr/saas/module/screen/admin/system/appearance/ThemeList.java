package com.intkr.saas.module.screen.admin.system.appearance;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.conf.CmsConf;
import com.intkr.saas.conf.SystemProperties;
import com.intkr.saas.domain.bo.conf.OptionBO;
import com.intkr.saas.domain.bo.saas.ThemeBO;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-10-2 下午5:52:57
 * @version 1.0
 */
public class ThemeList {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OptionManager optionManager = IOC.get(OptionManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<String> keys = new ArrayList<String>();
		keys.add(CmsConf.theme);
		for (String key : keys) {
			OptionBO option = optionManager.getByName(SessionClient.getSaas(request).getId(), key);
			if (option != null) {
				request.setAttribute(key, option.getValue());
			}
		}
		request.setAttribute("themeList", readThemeInfo(SystemProperties.getWebappPath() + "/IK/templates/screen/themes"));
	}

	private List<ThemeBO> readThemeInfo(String location) {
		List<ThemeBO> themeList = new ArrayList<ThemeBO>();
		File file = new File(location);
		for (String themeName : file.list()) {
			ThemeBO theme = new ThemeBO();
			theme.setName(themeName);
			themeList.add(theme);
		}
		return themeList;
	}

}
