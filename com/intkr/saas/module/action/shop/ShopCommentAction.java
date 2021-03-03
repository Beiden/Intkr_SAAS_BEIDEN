package com.intkr.saas.module.action.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.shop.ShopCommentBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.sns.CommentStatus;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.shop.ShopCommentManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-11-21 下午4:03:03
 * @version 1.0
 */
public class ShopCommentAction extends BaseAction<ShopCommentBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopCommentManager commentManager = IOC.get(ShopCommentManager.class);

	public ShopCommentBO getBO(HttpServletRequest request) {
		ShopCommentBO commentBO = getParameter(request);
		return commentBO;
	}

	public static ShopCommentBO getParameter(HttpServletRequest request) {
		ShopCommentBO commentBO = new ShopCommentBO();
		commentBO.setId(RequestUtil.getParam(request, "id", Long.class));
		commentBO.setType(RequestUtil.getParam(request, "type"));
		commentBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		commentBO.setUserName(RequestUtil.getParam(request, "userName"));
		commentBO.setEmail(RequestUtil.getParam(request, "email"));
		commentBO.setWebsite(RequestUtil.getParam(request, "website"));
		commentBO.setRelatedId(RequestUtil.getParam(request, "relatedId", Long.class));
		commentBO.setStatus(RequestUtil.getParam(request, "status"));
		commentBO.setContent(RequestUtil.getParam(request, "content"));
		commentBO.setPid(RequestUtil.getParam(request, "pid", Long.class));
		PagerUtil.initPage(request, commentBO);
		return commentBO;
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
		ShopCommentBO commentBO = commentManager.get(commentId);
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
		ShopCommentBO comment = commentManager.get(commentId);
		ShopCommentBO reply = new ShopCommentBO();
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

}
