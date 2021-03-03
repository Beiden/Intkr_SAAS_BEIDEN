package com.intkr.saas.module.screen.admin.lang;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.lang.LangContentBO;
import com.intkr.saas.manager.lang.LangContentManager;
import com.intkr.saas.manager.lang.LangContentTranslateManager;
import com.intkr.saas.module.action.lang.LangContentAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 内容
 * 
 * @table lang_content
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:16
 * @version 1.0
 */
public class LangContentMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LangContentManager manager = IOC.get(LangContentManager.class);

	private LangContentTranslateManager langContentTranslateManager = IOC.get(LangContentTranslateManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		LangContentBO query = LangContentAction.getParameter(request);
		String langId = RequestUtil.getParam(request, "langId");
		query.setQuery("orderBy", "lang_id asc, category");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		List<LangContentBO> list = query.getDatas();
		for (LangContentBO bo : list) {
			langContentTranslateManager.fill(bo, langId);
		}
		request.setAttribute("query", query);
		request.setAttribute("list", list);
	}
}
