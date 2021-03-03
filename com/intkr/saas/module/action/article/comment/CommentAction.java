package com.intkr.saas.module.action.article.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.article.ArticleCommentBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.sns.CommentStatus;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.cms.comment.ArticleCommentManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class CommentAction extends BaseAction<ArticleCommentBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleCommentManager commentManager = IOC.get(ArticleCommentManager.class);

	public ArticleCommentBO getBO(HttpServletRequest request) {
		ArticleCommentBO cmsCommentBO = getParameter(request);
		return cmsCommentBO;
	}

	public static ArticleCommentBO getParameter(HttpServletRequest request) {
		ArticleCommentBO comment = new ArticleCommentBO();
		comment.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		comment.setId(RequestUtil.getParam(request, "id", Long.class));
		comment.setType(RequestUtil.getParam(request, "type"));
		comment.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		comment.setAvatar(RequestUtil.getParam(request, "avatar"));
		comment.setUserName(RequestUtil.getParam(request, "userName"));
		comment.setEmail(RequestUtil.getParam(request, "email"));
		comment.setWebsite(RequestUtil.getParam(request, "website"));
		comment.setPid(RequestUtil.getParam(request, "pid", Long.class));
		comment.setRelatedId(RequestUtil.getParam(request, "relatedId", Long.class));
		if (comment.getRelatedId() == null) {
			comment.setRelatedId(RequestUtil.getParam(request, "articleId", Long.class));
		}
		if (comment.getRelatedId() == null) {
			comment.setRelatedId(RequestUtil.getParam(request, "pageId", Long.class));
		}
		comment.setStatus(RequestUtil.getParam(request, "status"));
		comment.setContent(RequestUtil.getParam(request, "content"));
		comment.setSort(RequestUtil.getParam(request, "sort", Double.class));
		PagerUtil.initPage(request, comment);
		return comment;
	}

	public BaseManager<?, ?> getBaseManager() {
		return commentManager;
	}

	/**
	 * 更新评论的状态
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doUpdateStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String commentId = RequestUtil.getParam(request, "commentId");
		String status = RequestUtil.getParam(request, "status");
		ArticleCommentBO commentBO = commentManager.get(commentId);
		commentBO.setStatus(status);
		commentManager.update(commentBO);
		request.setAttribute("result", true);
		request.setAttribute("msg", "更新成功");
	}

	/**
	 * 回复评论
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doReply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String commentId = RequestUtil.getParam(request, "commentId");
		ArticleCommentBO comment = commentManager.get(commentId);
		ArticleCommentBO reply = new ArticleCommentBO();
		reply.setId(commentManager.getId());
		reply.setType(comment.getType());
		UserBO user = SessionClient.getLoginUser(request);
		reply.setUserId(user.getId());
		reply.setUserName(user.getNickName());
		reply.setEmail(user.getEmail());
		reply.setRelatedId(comment.getRelatedId());
		reply.setStatus(CommentStatus.Approve.getCode());
		reply.setContent(RequestUtil.getParam(request, "content"));
		reply.setPid(comment.getId());
		commentManager.insert(reply);
		request.setAttribute("result", true);
		request.setAttribute("msg", "回复成功");
	}

	/**
	 * 发表评论
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArticleCommentBO bo = getParameter(request);
		bo.setId(commentManager.getId());
		bo.setType("articleComment");
		bo.setStatus(CommentStatus.WaitAudit.getCode());
		commentManager.insert(bo);
		request.setAttribute("result", true);
		request.setAttribute("msg", "添加成功");
	}

	/**
	 * 删除评论
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doDeleteOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idString = RequestUtil.getParam(request, "deleteId");
		commentManager.delete(idString);
		request.setAttribute("result", true);
		request.setAttribute("msg", "删除成功");
	}

}
