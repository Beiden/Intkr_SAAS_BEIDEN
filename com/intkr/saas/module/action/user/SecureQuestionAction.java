package com.intkr.saas.module.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.SecureQuestionManager;
import com.intkr.saas.module.action.user.auth.UpperRightAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.bo.user.SecureQuestionBO;

/**
 * 密保问题
 * 
 * @author Beiden
 * @date 2016-5-30 上午11:24:51
 * @version 1.0
 */
public class SecureQuestionAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SecureQuestionManager secureQuestionManager = IOC.get(SecureQuestionManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!SessionClient.hasUpperRight(request)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请认证后再进行操作！");
			return;
		}
		if (!RequestUtil.existParam(request, "question1")) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请填写问题一");
			return;
		}
		if (!RequestUtil.existParam(request, "question2")) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请填写问题二");
			return;
		}
		if (!RequestUtil.existParam(request, "answer1")) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请填写问题一答案");
			return;
		}
		if (!RequestUtil.existParam(request, "answer2")) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请填写问题二答案");
			return;
		}
		if (RequestUtil.getParam(request, "question1").equals(RequestUtil.getParam(request, "question2"))) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "二个问题不能一样");
			return;
		}
		if (RequestUtil.getParam(request, "answer1").equals(RequestUtil.getParam(request, "answer2"))) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "二个问题答案不能一样");
			return;
		}
		Long userId = SessionClient.getLoginUserId(request);
		List<SecureQuestionBO> secureQuestionList = secureQuestionManager.getSecureQuestion(userId);
		for (SecureQuestionBO sq : secureQuestionList) {
			secureQuestionManager.delete(sq.getId());
		}
		{
			SecureQuestionBO secureQuestion = new SecureQuestionBO();
			secureQuestion.setId(secureQuestionManager.getId());
			secureQuestion.setUserId(userId);
			secureQuestion.setCode("1");
			secureQuestion.setQuestion(RequestUtil.getParam(request, "question1"));
			secureQuestion.setAnswer(RequestUtil.getParam(request, "answer1"));
			secureQuestion.encryptAnswer();
			secureQuestionManager.insert(secureQuestion);
		}
		{
			SecureQuestionBO secureQuestion = new SecureQuestionBO();
			secureQuestion.setId(secureQuestionManager.getId());
			secureQuestion.setUserId(userId);
			secureQuestion.setCode("2");
			secureQuestion.setQuestion(RequestUtil.getParam(request, "question2"));
			secureQuestion.setAnswer(RequestUtil.getParam(request, "answer2"));
			secureQuestion.encryptAnswer();
			secureQuestionManager.insert(secureQuestion);
		}
		UpperRightAction.doRemoveRight(request);
		UserBO user = SessionClient.getLoginUser(request);
		if (user.getHasSecureQuestion() == null || user.getHasSecureQuestion() != 1) {
			user = userManager.get(user.getId());
			user.setHasSecureQuestion(1);
			userManager.update(user);
			SessionClient.login(request, response, user.getId());
		}
		request.setAttribute("msg", "修改成功！");
		request.setAttribute("result", true);
	}

}
