package com.intkr.saas.module.screen.admin.lang.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.lang.LanguageBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.lang.LanguageManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 语言
 * 
 * @table language
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:03
 * @version 1.0
 */
public class LanguageAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LanguageManager manager = IOC.get(LanguageManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String languageId = RequestUtil.getParam(request, "languageId");
		LanguageBO language = manager.get(languageId);
		request.setAttribute("language", language);
		request.setAttribute("addId", manager.getId());
	}

}
