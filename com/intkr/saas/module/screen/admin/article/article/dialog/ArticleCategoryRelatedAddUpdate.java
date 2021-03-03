package com.intkr.saas.module.screen.admin.article.article.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.article.ArticleCategoryRelatedBO;
import com.intkr.saas.manager.cms.article.ArticleCategoryRelatedManager;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ArticleCategoryRelatedAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleCategoryRelatedManager manager = IOC.get(ArticleCategoryRelatedManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String articleCategoryRelatedId = RequestUtil.getParam(request, "articleCategoryRelatedId");
		ArticleCategoryRelatedBO articleCategoryRelated = manager.get(articleCategoryRelatedId);
		request.setAttribute("articleCategoryRelated", articleCategoryRelated);
		request.setAttribute("addId", manager.getId());
	}

}
