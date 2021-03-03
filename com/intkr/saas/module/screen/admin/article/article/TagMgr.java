package com.intkr.saas.module.screen.admin.article.article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.article.ArticleTagBO;
import com.intkr.saas.manager.cms.article.TagManager;
import com.intkr.saas.module.action.article.article.TagAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class TagMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private TagManager manager = IOC.get(TagManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ArticleTagBO query = TagAction.getParameter(request);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "desc");
		query.setSaasId(SessionClient.getSaasId(request));
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
