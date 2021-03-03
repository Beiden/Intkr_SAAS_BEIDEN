package com.intkr.saas.module.screen.admin.shop.msg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sns.EmailTemplateBO;
import com.intkr.saas.manager.sns.EmailTemplateManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 上午10:50:35
 * @version 1.0
 */
public class EmailTemplateMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmailTemplateManager emailTemplateManager = IOC.get(EmailTemplateManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			EmailTemplateBO emailTemplateBO = emailTemplateManager.get(id);
			request.setAttribute("dto", emailTemplateBO);
		} else {
			request.setAttribute("addId", emailTemplateManager.getId());
		}
	}

}
