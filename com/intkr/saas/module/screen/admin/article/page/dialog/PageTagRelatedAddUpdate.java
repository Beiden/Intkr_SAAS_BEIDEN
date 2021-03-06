package com.intkr.saas.module.screen.admin.article.page.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.page.PageTagRelatedBO;
import com.intkr.saas.manager.cms.page.PageTagRelatedManager;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class PageTagRelatedAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private PageTagRelatedManager manager = IOC.get(PageTagRelatedManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String cmsPageTagRelatedId = RequestUtil.getParam(request, "cmsPageTagRelatedId");
		PageTagRelatedBO cmsPageTagRelated = manager.get(cmsPageTagRelatedId);
		request.setAttribute("cmsPageTagRelated", cmsPageTagRelated);
		request.setAttribute("addId", manager.getId());
	}

}
