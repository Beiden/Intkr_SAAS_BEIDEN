package com.intkr.saas.module.action.article.article;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.article.ArticleTagRelatedBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.manager.cms.article.ArticleTagRelatedManager;
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
public class ArticleTagRelatedAction extends BaseAction<ArticleTagRelatedBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleTagRelatedManager articleTagRelatedManager = IOC.get(ArticleTagRelatedManager.class);

	public ArticleTagRelatedBO getBO(HttpServletRequest request) {
		ArticleTagRelatedBO articleTagRelatedBO = getParameter(request);
		return articleTagRelatedBO;
	}

	public static ArticleTagRelatedBO getParameter(HttpServletRequest request) {
		ArticleTagRelatedBO articleTagRelatedBO = new ArticleTagRelatedBO();
		articleTagRelatedBO.setId(RequestUtil.getParam(request, "id", Long.class));
		articleTagRelatedBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		articleTagRelatedBO.setTagId(RequestUtil.getParam(request, "tagId", Long.class));
		articleTagRelatedBO.setRelatedId(RequestUtil.getParam(request, "relatedId", Long.class));
		PagerUtil.initPage(request, articleTagRelatedBO);
		return articleTagRelatedBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return articleTagRelatedManager;
	}

}
