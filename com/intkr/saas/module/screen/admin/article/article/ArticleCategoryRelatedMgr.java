package com.intkr.saas.module.screen.admin.article.article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.article.ArticleCategoryRelatedBO;
import com.intkr.saas.manager.cms.article.ArticleCategoryRelatedManager;
import com.intkr.saas.module.action.article.article.ArticleCategoryRelatedAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class ArticleCategoryRelatedMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleCategoryRelatedManager manager = IOC.get(ArticleCategoryRelatedManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ArticleCategoryRelatedBO query = ArticleCategoryRelatedAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query.setSaasId(SessionClient.getSaasId(request));
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
