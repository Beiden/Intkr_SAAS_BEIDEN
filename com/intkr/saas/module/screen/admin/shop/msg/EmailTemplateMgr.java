package com.intkr.saas.module.screen.admin.shop.msg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sns.EmailTemplateBO;
import com.intkr.saas.manager.sns.EmailTemplateManager;
import com.intkr.saas.module.action.sns.EmailTemplateAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 上午10:50:22
 * @version 1.0
 */
public class EmailTemplateMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmailTemplateManager emailTemplateManager = IOC.get(EmailTemplateManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		EmailTemplateBO query = EmailTemplateAction.getParameter(request);
		query = emailTemplateManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
