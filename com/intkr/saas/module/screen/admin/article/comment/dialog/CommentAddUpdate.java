package com.intkr.saas.module.screen.admin.article.comment.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.article.ArticleCommentBO;
import com.intkr.saas.manager.cms.comment.ArticleCommentManager;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class CommentAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleCommentManager manager = IOC.get(ArticleCommentManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String cmsCommentId = RequestUtil.getParam(request, "cmsCommentId");
		ArticleCommentBO cmsComment = manager.get(cmsCommentId);
		request.setAttribute("cmsComment", cmsComment);
		request.setAttribute("addId", manager.getId());
	}

}
