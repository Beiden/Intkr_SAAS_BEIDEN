package com.intkr.saas.module.action.article.page;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.page.PageCategoryBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.manager.cms.page.PageCategoryManager;
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
public class PageCategoryAction extends BaseAction<PageCategoryBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static PageCategoryManager cmsPageCategoryManager = IOC.get(PageCategoryManager.class);

	public PageCategoryBO getBO(HttpServletRequest request) {
		PageCategoryBO cmsPageCategoryBO = getParameter(request);
		return cmsPageCategoryBO;
	}

	public static PageCategoryBO getParameter(HttpServletRequest request) {
		PageCategoryBO cmsPageCategoryBO = new PageCategoryBO();
		cmsPageCategoryBO.setId(RequestUtil.getParam(request, "id", Long.class));
		cmsPageCategoryBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		cmsPageCategoryBO.setPid(RequestUtil.getParam(request, "pid", Long.class));
		cmsPageCategoryBO.setName(RequestUtil.getParam(request, "name"));
		cmsPageCategoryBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		cmsPageCategoryBO.setNote(RequestUtil.getParam(request, "note"));
		cmsPageCategoryBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, cmsPageCategoryBO);
		return cmsPageCategoryBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return cmsPageCategoryManager;
	}

}
