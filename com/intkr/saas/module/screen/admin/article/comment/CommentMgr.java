package com.intkr.saas.module.screen.admin.article.comment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.article.ArticleCommentBO;
import com.intkr.saas.domain.type.sns.CommentStatus;
import com.intkr.saas.manager.cms.article.ArticleManager;
import com.intkr.saas.manager.cms.comment.ArticleCommentManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.article.comment.CommentAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午3:44:48
 * @version 1.0
 */
public class CommentMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleCommentManager commentManager = IOC.get(ArticleCommentManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	private ArticleManager articleManager = IOC.get(ArticleManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ArticleCommentBO query = CommentAction.getParameter(request);
		commentManager.selectAndCount(query);
		List<ArticleCommentBO> list = query.getDatas();
		userManager.fill(list);
		articleManager.fill(list);
		request.setAttribute("query", query);
		request.setAttribute("list", list);

		request.setAttribute("commentStatusList", CommentStatus.values());
	}

}
