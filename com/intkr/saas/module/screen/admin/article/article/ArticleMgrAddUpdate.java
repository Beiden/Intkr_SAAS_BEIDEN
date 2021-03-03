package com.intkr.saas.module.screen.admin.article.article;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.article.ArticleCategoryBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.cms.ArticleStatus;
import com.intkr.saas.manager.cms.article.ArticleCategoryManager;
import com.intkr.saas.manager.cms.article.ArticleManager;
import com.intkr.saas.manager.cms.article.TagManager;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-10-2 下午3:38:39
 * @version 1.0
 */
public class ArticleMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleManager articleManager = IOC.get(ArticleManager.class);

	private ArticleCategoryManager categoryManager = IOC.get(ArticleCategoryManager.class);

	private TagManager tagManager = IOC.get(TagManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	private ImgManager imgManager = IOC.get(ImgManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Long id = RequestUtil.getParam(request, "id", Long.class);
		ArticleBO articleBO = articleManager.get(id);
		if (articleBO != null) {
			categoryManager.fill(articleBO);
			tagManager.fill(articleBO);
			imgManager.fill(articleBO);
			setCategoryIdsSet(request, articleBO);
			request.setAttribute("dto", articleBO);
		} else {
			request.setAttribute("addId", articleManager.getId());
		}
		request.setAttribute("categoryList", categoryManager.getFirstCategoryFull(SessionClient.getSaas(request).getId()));
		UserBO query = new UserBO();
		query.setId(SessionClient.getLoginUserId(request));
		request.setAttribute("authorList", userManager.select(query));
		request.setAttribute("articleStatusList", ArticleStatus.values());
	}

	private void setCategoryIdsSet(HttpServletRequest request, ArticleBO articleBO) {
		if (articleBO == null) {
			return;
		}
		Set<Long> categoryIdsSet = new HashSet<Long>();
		if (articleBO.getCategorys() != null) {
			for (ArticleCategoryBO bo : articleBO.getCategorys()) {
				categoryIdsSet.add(bo.getId());
			}
		}
		request.setAttribute("categoryIdsSet", categoryIdsSet);
	}

}
