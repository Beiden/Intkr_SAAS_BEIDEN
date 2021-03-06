package com.intkr.saas.module.screen.admin.article.article.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.article.ArticleCategoryRelationshipBO;
import com.intkr.saas.manager.cms.article.ArticleCategoryRelationshipManager;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ArticleCategoryRelationshipAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleCategoryRelationshipManager manager = IOC.get(ArticleCategoryRelationshipManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String articleCategoryRelationshipId = RequestUtil.getParam(request, "articleCategoryRelationshipId");
		ArticleCategoryRelationshipBO articleCategoryRelationship = manager.get(articleCategoryRelationshipId);
		request.setAttribute("articleCategoryRelationship", articleCategoryRelationship);
		request.setAttribute("addId", manager.getId());
	}

}
