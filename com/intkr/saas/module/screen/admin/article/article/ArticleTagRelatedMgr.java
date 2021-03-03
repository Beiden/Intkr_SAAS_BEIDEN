package com.intkr.saas.module.screen.admin.article.article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.article.ArticleTagRelatedBO;
import com.intkr.saas.manager.cms.article.ArticleTagRelatedManager;
import com.intkr.saas.module.action.article.article.ArticleTagRelatedAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class ArticleTagRelatedMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleTagRelatedManager manager = IOC.get(ArticleTagRelatedManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ArticleTagRelatedBO query = ArticleTagRelatedAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query.setSaasId(SessionClient.getSaasId(request));
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
