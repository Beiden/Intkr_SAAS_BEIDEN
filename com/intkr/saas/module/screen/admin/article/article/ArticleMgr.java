package com.intkr.saas.module.screen.admin.article.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.type.cms.ArticleStatus;
import com.intkr.saas.manager.cms.article.ArticleCategoryManager;
import com.intkr.saas.manager.cms.article.ArticleManager;
import com.intkr.saas.manager.cms.article.TagManager;
import com.intkr.saas.manager.cms.comment.ArticleCommentManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.article.article.ArticleAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class ArticleMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleManager manager = IOC.get(ArticleManager.class);

	private ArticleCategoryManager categoryManager = IOC.get(ArticleCategoryManager.class);

	private TagManager tagManager = IOC.get(TagManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	private ArticleCommentManager commentManager = IOC.get(ArticleCommentManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ArticleBO query = ArticleAction.getParameter(request);
		if (RequestUtil.existParam(request, "categoryId")) {
			query.setQuery("categoryId", RequestUtil.getParam(request, "categoryId"));
		}
		if (RequestUtil.existParam(request, "dayStart")) {
			query.setQuery("dayStart", RequestUtil.getParam(request, "dayStart"));
		}
		if (RequestUtil.existParam(request, "dayEnd")) {
			query.setQuery("dayEnd", RequestUtil.getParam(request, "dayEnd"));
		}
		if (RequestUtil.existParam(request, "searchWord")) {
			query.setQuery("searchWord", RequestUtil.getParam(request, "searchWord"));
			query.setQuery("searchWordSQL", "%" + RequestUtil.getParam(request, "searchWord") + "%");
		}
		query.setQuery("orderBy", "public_time");
		query.setQuery("order", "desc");
		query.setSaasId(SessionClient.getSaasId(request));
		manager.selectAndCount(query);
		List<ArticleBO> list = query.getDatas();
		userManager.fill(list);
		categoryManager.fill(list);
		tagManager.fill(list);
		for (Object boTmp : query.getDatas()) {
			ArticleBO bo = (ArticleBO) boTmp;
			bo.setProperty("commentCount", commentManager.countByRelatedId(bo.getId()));
		}
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("categoryList", categoryManager.getFirstCategoryFull(SessionClient.getSaas(request).getId()));
		request.setAttribute("articleStatusList", ArticleStatus.values());
	}

}
