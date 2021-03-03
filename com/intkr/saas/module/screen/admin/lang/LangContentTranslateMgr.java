package com.intkr.saas.module.screen.admin.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.lang.LangContentTranslateAction;
import com.intkr.saas.domain.bo.lang.LangContentTranslateBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.lang.LangContentTranslateManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.util.claz.IOC;

/**
 * 内容翻译
 * 
 * @table lang_content_translate
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:30
 * @version 1.0
 */
public class LangContentTranslateMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LangContentTranslateManager manager = IOC.get(LangContentTranslateManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		LangContentTranslateBO query = LangContentTranslateAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
