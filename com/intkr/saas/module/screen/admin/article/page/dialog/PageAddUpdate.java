package com.intkr.saas.module.screen.admin.article.page.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.manager.cms.page.PageManager;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class PageAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private PageManager manager = IOC.get(PageManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String cmsPageId = RequestUtil.getParam(request, "cmsPageId");
		PageBO cmsPage = manager.get(cmsPageId);
		request.setAttribute("cmsPage", cmsPage);
		request.setAttribute("addId", manager.getId());
	}

}
