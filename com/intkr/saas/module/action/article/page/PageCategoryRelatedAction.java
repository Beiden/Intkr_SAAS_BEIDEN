package com.intkr.saas.module.action.article.page;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.page.PageCategoryRelatedBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.cms.page.PageCategoryRelatedManager;
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
public class PageCategoryRelatedAction extends BaseAction<PageCategoryRelatedBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private PageCategoryRelatedManager cmsPageCategoryRelatedManager = IOC.get(PageCategoryRelatedManager.class);

	public PageCategoryRelatedBO getBO(HttpServletRequest request) {
		PageCategoryRelatedBO cmsPageCategoryRelatedBO = getParameter(request);
		return cmsPageCategoryRelatedBO;
	}

	public static PageCategoryRelatedBO getParameter(HttpServletRequest request) {
		PageCategoryRelatedBO cmsPageCategoryRelatedBO = new PageCategoryRelatedBO();
		cmsPageCategoryRelatedBO.setId(RequestUtil.getParam(request, "id", Long.class));
		cmsPageCategoryRelatedBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		cmsPageCategoryRelatedBO.setType(RequestUtil.getParam(request, "type"));
		cmsPageCategoryRelatedBO.setCategoryId(RequestUtil.getParam(request, "categoryId" , Long.class));
		cmsPageCategoryRelatedBO.setPageId(RequestUtil.getParam(request, "pageId" , Long.class));
		PagerUtil.initPage(request, cmsPageCategoryRelatedBO);
		return cmsPageCategoryRelatedBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return cmsPageCategoryRelatedManager;
	}

}
