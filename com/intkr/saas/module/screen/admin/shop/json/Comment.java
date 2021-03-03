package com.intkr.saas.module.screen.admin.shop.json;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.article.ArticleCommentBO;
import com.intkr.saas.manager.cms.comment.ArticleCommentManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-11-21 下午10:37:45
 * @version 1.0
 */
public class Comment {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleCommentManager commentManager = IOC.get("CommentManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String commentId = RequestUtil.getParam(request, "commentId");
		ArticleCommentBO comment = commentManager.get(commentId);
		request.setAttribute("comment", comment);
	}

}
