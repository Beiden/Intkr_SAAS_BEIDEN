package com.intkr.saas.module.action.sns;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.article.ArticleCommentBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.sns.CommentStatus;
import com.intkr.saas.manager.cms.comment.ArticleCommentManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2015-6-30 上午11:57:22
 * @version 1.0
 */
public class MyCommentAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleCommentManager commentManager = IOC.get(ArticleCommentManager.class);

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArticleCommentBO commentBO = getCommentBO(request);
		commentBO.setId(commentManager.getId());
		UserBO userBO = SessionClient.getLoginUser(request);
		commentBO.setStatus(CommentStatus.WaitAudit.getCode());
		commentBO.setUserId(userBO.getId());
		commentBO.setAvatar(userBO.getAvatar());
		commentBO.setEmail(userBO.getEmail());
		commentManager.insert(commentBO);
		request.setAttribute("msg", "记录成功");
		request.setAttribute("result", true);
	}

	public void doDeleteOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idString = RequestUtil.getParam(request, "id");
		deleteOne(request, response, idString);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response, String id) {
		ArticleCommentBO commentBO = commentManager.get(id);
		if (commentBO == null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "记录不存在");
			return;
		}
		UserBO userBO = SessionClient.getLoginUser(request);
		if (userBO.getId().equals(commentBO.getUserId())) {
			commentManager.delete(id);
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功");
			return;
		}
		request.setAttribute("result", false);
		request.setAttribute("msg", "记录不存在");
	}

	public void deleteAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idsString = RequestUtil.getParam(request, "ids");
		String[] idsStrings = idsString.split(",");
		for (String idString : idsStrings) {
			deleteOne(request, response, idString);
		}
		request.setAttribute("result", false);
		request.setAttribute("msg", "操作成功");
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArticleCommentBO commentBO = getCommentBO(request);
		commentBO.setId(RequestUtil.getParam(request, "id", Long.class));
		ArticleCommentBO oldCommentBO = commentManager.get(commentBO.getId());
		if (oldCommentBO == null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "记录不存在");
			return;
		}
		UserBO userBO = SessionClient.getLoginUser(request);
		if (userBO.getId().equals(commentBO.getUserId())) {
			commentManager.update(commentBO);
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功");
			return;
		}
		request.setAttribute("result", false);
		request.setAttribute("msg", "记录不存在");
	}

	private ArticleCommentBO getCommentBO(HttpServletRequest request) {
		ArticleCommentBO commentBO = new ArticleCommentBO();
		commentBO.setId(RequestUtil.getParam(request, "id", Long.class));
		commentBO.setType(RequestUtil.getParam(request, "type"));
		commentBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		commentBO.setRelatedId(RequestUtil.getParam(request, "relatedId", Long.class));
		commentBO.setContent(RequestUtil.getParam(request, "content"));
		commentBO.setPid(RequestUtil.getParam(request, "pid", Long.class));
		return commentBO;
	}

}
