package com.intkr.saas.module.screen.admin.system.appearance.menu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.article.ArticleCategoryBO;
import com.intkr.saas.domain.bo.article.ArticleTagBO;
import com.intkr.saas.domain.bo.menu.FrontMenuBO;
import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.manager.cms.article.ArticleCategoryManager;
import com.intkr.saas.manager.cms.article.TagManager;
import com.intkr.saas.manager.cms.menu.FrontMenuDetailManager;
import com.intkr.saas.manager.cms.menu.FrontMenuManager;
import com.intkr.saas.manager.cms.page.PageManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-10-1 下午4:28:19
 * @version 1.0
 */
public class MenuMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private FrontMenuManager frontMenuManager = IOC.get(FrontMenuManager.class);

	private FrontMenuDetailManager frontMenuDetailManager = IOC.get(FrontMenuDetailManager.class);

	private ArticleCategoryManager categoryManager = IOC.get(ArticleCategoryManager.class);

	private TagManager tagManager = IOC.get(TagManager.class);

	private PageManager pageManager = IOC.get(PageManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			FrontMenuBO frontMenuBO = frontMenuManager.get(id);
			frontMenuDetailManager.fill(frontMenuBO);
			request.setAttribute("dto", frontMenuBO);
		} else {
		}

		fileCategory(request);
		fillTag(request);
		fillPage(request);
	}

	private void fillPage(HttpServletRequest request) {
		PageBO query = new PageBO();
		query.set_pageSize(1000);
		List<PageBO> pageList = pageManager.select(query);
		request.setAttribute("pageList", pageList);
	}

	private void fillTag(HttpServletRequest request) {
		ArticleTagBO query = new ArticleTagBO();
		query.set_pageSize(1000);
		List<ArticleTagBO> tagList = tagManager.select(query);
		request.setAttribute("tagList", tagList);
	}

	private void fileCategory(HttpServletRequest request) {
		List<ArticleCategoryBO> categoryList = categoryManager.getFirstCategoryFull(SessionClient.getSaas(request).getId());
		request.setAttribute("categoryList", categoryList);
	}

}
