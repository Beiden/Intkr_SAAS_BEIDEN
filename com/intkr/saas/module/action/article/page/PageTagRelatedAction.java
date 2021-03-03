package com.intkr.saas.module.action.article.page;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.page.PageTagRelatedBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.manager.cms.page.PageTagRelatedManager;
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
public class PageTagRelatedAction extends BaseAction<PageTagRelatedBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private PageTagRelatedManager cmsPageTagRelatedManager = IOC.get(PageTagRelatedManager.class);

	public PageTagRelatedBO getBO(HttpServletRequest request) {
		PageTagRelatedBO cmsPageTagRelatedBO = getParameter(request);
		return cmsPageTagRelatedBO;
	}

	public static PageTagRelatedBO getParameter(HttpServletRequest request) {
		PageTagRelatedBO cmsPageTagRelatedBO = new PageTagRelatedBO();
		cmsPageTagRelatedBO.setId(RequestUtil.getParam(request, "id", Long.class));
		cmsPageTagRelatedBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		cmsPageTagRelatedBO.setTagId(RequestUtil.getParam(request, "tagId" , Long.class));
		cmsPageTagRelatedBO.setRelatedId(RequestUtil.getParam(request, "relatedId" , Long.class));
		PagerUtil.initPage(request, cmsPageTagRelatedBO);
		return cmsPageTagRelatedBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return cmsPageTagRelatedManager;
	}

}
