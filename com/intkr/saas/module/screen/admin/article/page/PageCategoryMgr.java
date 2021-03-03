package com.intkr.saas.module.screen.admin.article.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.page.PageCategoryBO;
import com.intkr.saas.manager.cms.page.PageCategoryManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class PageCategoryMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private PageCategoryManager manager = IOC.get(PageCategoryManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<PageCategoryBO> list = manager.getFirstCategoryFull(SessionClient.getSaas(request).getId());
		request.setAttribute("list", list);
	}

}
