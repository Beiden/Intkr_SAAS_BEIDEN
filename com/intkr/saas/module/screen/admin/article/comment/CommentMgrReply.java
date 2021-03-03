package com.intkr.saas.module.screen.admin.article.comment;

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
 * @date 2011-6-29 下午3:45:04
 * @version 1.0
 */
public class CommentMgrReply {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleCommentManager commentManager = IOC.get(ArticleCommentManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			ArticleCommentBO commentBO = commentManager.get(id);
			request.setAttribute("dto", commentBO);
		} else {
			request.setAttribute("addId", commentManager.getId());
		}
	}

}
