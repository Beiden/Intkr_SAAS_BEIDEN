package com.intkr.saas.module.screen.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.article.ArticleCommentBO;
import com.intkr.saas.domain.bo.log.UserLogBO;
import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.manager.cms.article.ArticleManager;
import com.intkr.saas.manager.cms.comment.ArticleCommentManager;
import com.intkr.saas.manager.cms.page.PageManager;
import com.intkr.saas.manager.log.UserLogManager;
import com.intkr.saas.module.BaseScreen;
import com.intkr.saas.module.action.log.UserLogAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-3 下午6:00:36
 * @version 1.0
 */
public class Index extends BaseScreen {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleManager articleManager = IOC.get(ArticleManager.class);

	private PageManager pageManager = IOC.get(PageManager.class);

	private ArticleCommentManager commentManager = IOC.get(ArticleCommentManager.class);

	private UserLogManager userLogManager = IOC.get(UserLogManager.class);

	public void subExecute(HttpServletRequest request, HttpServletResponse response) {
		culArticleCount(request);
		culPageCount(request);
		culCommentCount(request);
		request.setAttribute("systemVersioin", "0.0.1");
		fillLog(request);
	}

	private void fillLog(HttpServletRequest request) {
		UserLogBO query = UserLogAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		query.set_pageSize(8);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		List<UserLogBO> logList = userLogManager.select(query);
		request.setAttribute("logList", logList);
	}

	private void culArticleCount(HttpServletRequest request) {
		Long saasId = SessionClient.getSaasId(request);
		ArticleBO query = new ArticleBO();
		query.setSaasId(saasId);
		Long articleCount = articleManager.count(query);
		request.setAttribute("articleCount", articleCount);
	}

	private void culPageCount(HttpServletRequest request) {
		Long saasId = SessionClient.getSaasId(request);
		PageBO query = new PageBO();
		query.setSaasId(saasId);
		Long pageCount = pageManager.count(query);
		request.setAttribute("pageCount", pageCount);
	}

	private void culCommentCount(HttpServletRequest request) {
		Long saasId = SessionClient.getSaasId(request);
		ArticleCommentBO query = new ArticleCommentBO();
		query.setSaasId(saasId);
		Long commentCount = commentManager.count(query);
		request.setAttribute("commentCount", commentCount);
	}

}
