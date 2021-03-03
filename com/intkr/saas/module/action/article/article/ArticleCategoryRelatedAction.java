package com.intkr.saas.module.action.article.article;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.article.ArticleCategoryRelatedBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.manager.cms.article.ArticleCategoryRelatedManager;
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
public class ArticleCategoryRelatedAction extends BaseAction<ArticleCategoryRelatedBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleCategoryRelatedManager articleCategoryRelatedManager = IOC.get(ArticleCategoryRelatedManager.class);

	public ArticleCategoryRelatedBO getBO(HttpServletRequest request) {
		ArticleCategoryRelatedBO articleCategoryRelatedBO = getParameter(request);
		return articleCategoryRelatedBO;
	}

	public static ArticleCategoryRelatedBO getParameter(HttpServletRequest request) {
		ArticleCategoryRelatedBO articleCategoryRelatedBO = new ArticleCategoryRelatedBO();
		articleCategoryRelatedBO.setId(RequestUtil.getParam(request, "id", Long.class));
		articleCategoryRelatedBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		articleCategoryRelatedBO.setCategoryId(RequestUtil.getParam(request, "categoryId" , Long.class));
		articleCategoryRelatedBO.setArticleId(RequestUtil.getParam(request, "articleId" , Long.class));
		PagerUtil.initPage(request, articleCategoryRelatedBO);
		return articleCategoryRelatedBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return articleCategoryRelatedManager;
	}

}
