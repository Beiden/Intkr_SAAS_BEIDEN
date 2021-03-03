package com.intkr.saas.module.screen.admin.article.page.dialog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.page.PageCategoryBO;
import com.intkr.saas.manager.cms.page.PageCategoryManager;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class PageCategoryAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private PageCategoryManager manager = IOC.get(PageCategoryManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<PageCategoryBO> categoryList = manager.getFirstCategoryFull(SessionClient.getSaas(request).getId());
		request.setAttribute("categoryList", categoryList);

		String cmsPageCategoryId = RequestUtil.getParam(request, "cmsPageCategoryId");
		PageCategoryBO cmsPageCategory = manager.get(cmsPageCategoryId);
		request.setAttribute("cmsPageCategory", cmsPageCategory);
		request.setAttribute("addId", manager.getId());
	}

}
