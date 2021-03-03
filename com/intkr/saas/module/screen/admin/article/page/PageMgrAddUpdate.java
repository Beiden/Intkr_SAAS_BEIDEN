package com.intkr.saas.module.screen.admin.article.page;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.domain.bo.page.PageCategoryBO;
import com.intkr.saas.domain.type.cms.ArticleStatus;
import com.intkr.saas.manager.cms.article.TagManager;
import com.intkr.saas.manager.cms.page.PageCategoryManager;
import com.intkr.saas.manager.cms.page.PageManager;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-10-2 下午3:38:39
 * @version 1.0
 */
public class PageMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private PageManager articleManager = IOC.get(PageManager.class);

	private PageCategoryManager categoryManager = IOC.get(PageCategoryManager.class);

	private TagManager tagManager = IOC.get(TagManager.class);

	private ImgManager imgManager = IOC.get(ImgManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			PageBO articleBO = articleManager.get(id);
			categoryManager.fill(articleBO);
			tagManager.fill(articleBO);
			imgManager.fill(articleBO);
			setCategoryIdsSet(request, articleBO);
			request.setAttribute("dto", articleBO);
		} else {
			request.setAttribute("addId", articleManager.getId());
		}
		request.setAttribute("categoryList", categoryManager.getFirstCategoryFull(SessionClient.getSaas(request).getId()));
		request.setAttribute("articleStatusList", ArticleStatus.values());
	}

	private void setCategoryIdsSet(HttpServletRequest request, PageBO articleBO) {
		Set<Long> categoryIdsSet = new HashSet<Long>();
		for (PageCategoryBO bo : articleBO.getCategorys()) {
			categoryIdsSet.add(bo.getId());
		}
		request.setAttribute("categoryIdsSet", categoryIdsSet);
	}
}
