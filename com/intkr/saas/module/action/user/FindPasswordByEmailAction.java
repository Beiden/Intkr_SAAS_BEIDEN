package com.intkr.saas.module.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.cache.FindPasswordVerifyCodeLocalCache;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.engine.CheckCodeEngine;
import com.intkr.saas.facade.email.EmailMsgEngine;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.saas.SaasClientManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.util.MD5Util;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-5-22 下午2:34:00
 * @version 1.0
 */
public class FindPasswordByEmailAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static OptionManager optionManager = IOC.get(OptionManager.class);
	
	private static SaasClientManager saasClientManager = IOC.get(SaasClientManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	private boolean verifyCheckCode(HttpServletRequest request) {
		if (!CheckCodeEngine.check(request, "checkCode")) {
			request.setAttribute("msg", "验证码错误，请点击验证码刷新，重新输入！");
			return false;
		} else {
			request.getSession().removeAttribute("checkCode");
			return true;
		}
	}

	private boolean verify(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.getParam(request, "email") == null || "".equals(RequestUtil.getParam(request, "email"))) {
			request.setAttribute("msg", "请填写正确的邮箱地址！");
			return false;
		}
		return true;
	}

	public void doSendVerifyEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!verifyCheckCode(request) || !verify(request, response)) {
			return;
		}
		String email = RequestUtil.getParam(request, "email");
		UserBO query = new UserBO();
		query.setEmail(email);
		List<UserBO> userList = userManager.select(query);
		if (userList.isEmpty()) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "邮箱不存在！");
			return;
		} else if (userList.size() > 1) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常！");
			return;
		} else {
			sendFindPasswordEmail(SessionClient.getSaas(request).getId(), email);
			request.getSession().setAttribute("findPasswordEmail", email);
			request.setAttribute("msg", "验证邮件已发送！");
			request.setAttribute("result", true);
		}
	}

	public void doVerifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (RequestUtil.existParam(request, "code")) {
			String code = RequestUtil.getParam(request, "code");
			String email = null;
			if (FindPasswordVerifyCodeLocalCache.getEmail(code) != null) {
				email = FindPasswordVerifyCodeLocalCache.getEmail(code);
				request.getSession().setAttribute("findPasswordEmail", email);
			}
			if (email != null && FindPasswordVerifyCodeLocalCache.isCodeValidate(email, code)) {
				UserBO user = userManager.getByEmail(SessionClient.getSaasId(request), email);
				SessionClient.login(request, response, user.getId());
				request.getSession().setAttribute("canChangePasswordByEmail", true);
				request.getSession().setAttribute("canChangePasswordByEmail-code", code);
				FindPasswordVerifyCodeLocalCache.remove(code);
				request.setAttribute("msg", "验证成功！");
				request.setAttribute("result", true);
			} else {
				request.setAttribute("msg", "验证码已过期！");
				request.setAttribute("result", false);
			}
		} else {
			request.setAttribute("msg", "请填写验证码！");
			request.setAttribute("result", false);
		}
	}

	public void doChangePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getSession().getAttribute("canChangePasswordByEmail") != null) {
			request.getSession().removeAttribute("canChangePasswordByEmail");
			String email = (String) request.getSession().getAttribute("findPasswordEmail");
			String password = RequestUtil.getParam(request, "password");
			UserBO query = new UserBO();
			query.setEmail(email);
			List<UserBO> userBOList = userManager.select(query);
			UserBO userBO = userBOList.get(0);
			String passwordAdd = MD5Util.getRandomAdd();
			String passwordEyc = MD5Util.encrypt(password, passwordAdd);
			userBO.setPassword(passwordEyc + "|" + passwordAdd);
			userManager.update(userBO);
			request.getSession().removeAttribute("canChangePasswordByEmail");
			String code = (String) request.getSession().getAttribute("canChangePasswordByEmail-code");
			FindPasswordVerifyCodeLocalCache.remove(code);
			request.setAttribute("result", true);
			request.setAttribute("msg", "更新密码成功");
		} else {
			request.setAttribute("msg", "验证码已经过期，请重新验证！");
			request.setAttribute("result", false);
		}
	}

	public void sendFindPasswordEmail(Long saasId, String email) {
		String websiteUrl = "http://" + saasClientManager.getSaasDomain(saasId);
		String code = FindPasswordVerifyCodeLocalCache.getfindPasswordCode(email);
		String verifyUrl = websiteUrl + "/sign/findPasswordByEmailResetPassword.html?action_method=sign.FindPasswordByEmailAction.verifyCode&code=" + code;
		String subject = "[小贝商城]-找回密码";
		String content = "";
		content += "这封信是由 小贝商城 发送的。<br/><br/>";
		content += "您只需点击下面的链接即可修改你帐号的密码：<br/>";
		content += "<a href='" + verifyUrl + "'>" + verifyUrl + "</a><br/>";
		content += "(如果上面点击没反应，请将该地址手工粘贴到浏览器地址栏再访问)<br/><br/>";
		content += "你的修改密码验证码为：<br/>";
		content += code + "<br/><br/>";
		content += "感谢您的访问，祝您使用愉快！<br/><br/>";
		content += "此致<br/><br/>";
		content += "小贝商城 管理团队.<br/><br/>";
		content += "<a href='http://" + websiteUrl + "/'>http://" + websiteUrl + "</a>";
		EmailMsgEngine.send(saasId, email, subject, content);
	}

}
