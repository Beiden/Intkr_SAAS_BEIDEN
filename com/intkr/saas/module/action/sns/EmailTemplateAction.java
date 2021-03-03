package com.intkr.saas.module.action.sns;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sns.EmailTemplateBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.sns.EmailTemplateManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 上午10:49:17
 * @version 1.0
 */
public class EmailTemplateAction extends BaseAction<EmailTemplateBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmailTemplateManager emailTemplateManager = IOC.get(EmailTemplateManager.class);

	public EmailTemplateBO getBO(HttpServletRequest request) {
		EmailTemplateBO emailTemplateBO = getParameter(request);
		return emailTemplateBO;
	}

	public static EmailTemplateBO getParameter(HttpServletRequest request) {
		EmailTemplateBO emailTemplateBO = new EmailTemplateBO();
		emailTemplateBO.setId(RequestUtil.getParam(request, "id", Long.class));
		emailTemplateBO.setCode(RequestUtil.getParam(request, "code"));
		emailTemplateBO.setName(RequestUtil.getParam(request, "name"));
		emailTemplateBO.setTitle(RequestUtil.getParam(request, "title"));
		emailTemplateBO.setContent(RequestUtil.getParam(request, "content"));
		emailTemplateBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, emailTemplateBO);
		return emailTemplateBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return emailTemplateManager;
	}
}
