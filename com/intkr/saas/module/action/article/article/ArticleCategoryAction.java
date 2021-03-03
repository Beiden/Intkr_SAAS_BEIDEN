package com.intkr.saas.module.action.article.article;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.article.ArticleCategoryBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.manager.cms.article.ArticleCategoryManager;
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
public class ArticleCategoryAction extends BaseAction<ArticleCategoryBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static ArticleCategoryManager articleCategoryManager = IOC.get(ArticleCategoryManager.class);

	public ArticleCategoryBO getBO(HttpServletRequest request) {
		ArticleCategoryBO articleCategoryBO = getParameter(request);
		return articleCategoryBO;
	}

	public static ArticleCategoryBO getParameter(HttpServletRequest request) {
		ArticleCategoryBO articleCategoryBO = new ArticleCategoryBO();
		articleCategoryBO.setId(RequestUtil.getParam(request, "id", Long.class));
		articleCategoryBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		articleCategoryBO.setPid(RequestUtil.getParam(request, "pid", Long.class));
		articleCategoryBO.setName(RequestUtil.getParam(request, "name"));
		articleCategoryBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		articleCategoryBO.setNote(RequestUtil.getParam(request, "note"));
		articleCategoryBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, articleCategoryBO);
		return articleCategoryBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return articleCategoryManager;
	}

}
