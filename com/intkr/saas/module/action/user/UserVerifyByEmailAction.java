package com.intkr.saas.module.action.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.cache.EmailVerifyCodeLocalCache;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.sns.EmailTemplateBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.user.UserStatus;
import com.intkr.saas.facade.email.EmailMsgEngine;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.saas.SaasClientManager;
import com.intkr.saas.manager.sns.EmailTemplateManager;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.UserRoleManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.TemplateUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-5-11 下午4:10:52
 * @version 1.0
 */
public class UserVerifyByEmailAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static OptionManager optionManager = IOC.get(OptionManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	private static EmailTemplateManager emailTemplateManager = IOC.get(EmailTemplateManager.class);

	private UserRoleManager authManager = IOC.get(UserRoleManager.class);

	private RoleManager roleManager = IOC.get(RoleManager.class);
	
	private static SaasClientManager saasClientManager = IOC.get(SaasClientManager.class);

	public void doSendAccountVerifyEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserBO userBO = SessionClient.getLoginUser(request);
		if (userBO == null) {
			request.setAttribute("msg", "请先登录，再进行邮箱验证！");
			request.setAttribute("result", false);
		}
		String email = RequestUtil.getParam(request, "email");
		if (userManager.isEmailCanUse(SessionClient.getSaasId(request), email)) {
			sendVerifyEmail(SessionClient.getSaas(request).getId(), userBO.getId(), email);
			request.setAttribute("msg", "验证邮件已发送！");
			request.setAttribute("result", true);
		} else {
			request.setAttribute("msg", "邮箱已注册！");
			request.setAttribute("result", false);
		}
	}

	public void doVerifyEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getParam(request, "id", Long.class);
		String code = RequestUtil.getParam(request, "code");
		UserBO userBO = userManager.get(id);
		if (EmailVerifyCodeLocalCache.isCodeValidate(id, code)) {
			Map<String, String> map = EmailVerifyCodeLocalCache.get(id);
			String email = map.get("email");
			if (userManager.isEmailCanUse(SessionClient.getSaasId(request), email)) {
				userBO.setEmail(email);
				userBO.setStatus(UserStatus.Normal.getCode());
				userManager.update(userBO);
				authManager.addRole(userBO.getId(), roleManager.getByCode("member").getId());
				authManager.deleteRole(userBO.getId(), roleManager.getByCode("preMember").getId());
				SessionClient.login(request, response, userBO.getId());
				EmailVerifyCodeLocalCache.remove(id);
				request.setAttribute("result", true);
				request.setAttribute("msg", "邮件验证成功！");
			} else {
				request.setAttribute("msg", "邮箱已经注册");
				request.setAttribute("result", false);
			}
		} else {
			request.setAttribute("msg", "验证码已失效，请重新验证！");
			request.setAttribute("result", false);
		}
	}

	public static void sendVerifyEmail(Long saasId, Long userId, String email) {
		String code = EmailVerifyCodeLocalCache.getVerifyEmailCode(userId, email);
		String verifyUrl = "http://" + saasClientManager.getSaasDomain(saasId) + "/sign/accountVerifyByEmailSuccess.html?action_method=sign.AccountVerifyByEmailAction.verifyEmail&id=" + userId + "&code=" + code;
		Map<String, String> param = new HashMap<String, String>();
		param.put("websiteTitle", optionManager.getWebsiteTitle(saasId));
		param.put("verifyUrl", verifyUrl);
		param.put("websiteUrl", "http://" + saasClientManager.getSaasDomain(saasId));

		EmailTemplateBO template = emailTemplateManager.getByCode("account-verify-email");
		String subject = TemplateUtil.merge(template.getTitle(), param);
		String content = TemplateUtil.merge(template.getContent(), param);
		EmailMsgEngine.send(saasId, email, subject, content);
	}

}
