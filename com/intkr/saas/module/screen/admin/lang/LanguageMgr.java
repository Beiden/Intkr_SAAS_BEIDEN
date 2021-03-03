package com.intkr.saas.module.screen.admin.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.lang.LanguageAction;
import com.intkr.saas.domain.bo.lang.LanguageBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.lang.LanguageManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class LanguageMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LanguageManager manager = IOC.get(LanguageManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		LanguageBO query = LanguageAction.getParameter(request);
		query.setQuery("orderBy", "lang_id");
		query.setQuery("order", "asc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
