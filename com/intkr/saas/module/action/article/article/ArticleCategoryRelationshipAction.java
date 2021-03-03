package com.intkr.saas.module.action.article.article;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.article.ArticleCategoryRelationshipBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.manager.cms.article.ArticleCategoryRelationshipManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class ArticleCategoryRelationshipAction extends BaseAction<ArticleCategoryRelationshipBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleCategoryRelationshipManager articleCategoryRelationshipManager = IOC.get(ArticleCategoryRelationshipManager.class);

	public ArticleCategoryRelationshipBO getBO(HttpServletRequest request) {
		ArticleCategoryRelationshipBO articleCategoryRelationshipBO = getParameter(request);
		return articleCategoryRelationshipBO;
	}

	public static ArticleCategoryRelationshipBO getParameter(HttpServletRequest request) {
		ArticleCategoryRelationshipBO articleCategoryRelationshipBO = new ArticleCategoryRelationshipBO();
		articleCategoryRelationshipBO.setId(RequestUtil.getParam(request, "id", Long.class));
		articleCategoryRelationshipBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		articleCategoryRelationshipBO.setType(RequestUtil.getParam(request, "type"));
		articleCategoryRelationshipBO.setCategoryId(RequestUtil.getParam(request, "categoryId" , Long.class));
		articleCategoryRelationshipBO.setRelatedId(RequestUtil.getParam(request, "relatedId" , Long.class));
		PagerUtil.initPage(request, articleCategoryRelationshipBO);
		return articleCategoryRelationshipBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return articleCategoryRelationshipManager;
	}

}
