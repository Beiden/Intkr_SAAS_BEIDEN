package com.intkr.saas.module.action.article.article;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.article.ArticleTagBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.cms.article.TagManager;
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
public class TagAction extends BaseAction<ArticleTagBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private TagManager articleTagManager = IOC.get(TagManager.class);

	public ArticleTagBO getBO(HttpServletRequest request) {
		ArticleTagBO articleTagBO = getParameter(request);
		return articleTagBO;
	}

	public static ArticleTagBO getParameter(HttpServletRequest request) {
		ArticleTagBO articleTagBO = new ArticleTagBO();
		articleTagBO.setId(RequestUtil.getParam(request, "id", Long.class));
		articleTagBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		articleTagBO.setName(RequestUtil.getParam(request, "name"));
		articleTagBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		articleTagBO.setImgDesc(RequestUtil.getParam(request, "imgDesc"));
		articleTagBO.setFeature(RequestUtil.getParam(request, "feature"));
		articleTagBO.setNote(RequestUtil.getParam(request, "note"));
		PagerUtil.initPage(request, articleTagBO);
		return articleTagBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return articleTagManager;
	}

}
