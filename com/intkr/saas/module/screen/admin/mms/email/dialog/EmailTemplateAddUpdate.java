package com.intkr.saas.module.screen.admin.mms.email.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.mms.EmailTemplateBO;
import com.intkr.saas.manager.mms.EmailTemplateManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class EmailTemplateAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmailTemplateManager manager = IOC.get(EmailTemplateManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mmsEmailTemplateId = RequestUtil.getParam(request, "mmsEmailTemplateId");
		EmailTemplateBO mmsEmailTemplate = manager.get(mmsEmailTemplateId);
		request.setAttribute("mmsEmailTemplate", mmsEmailTemplate);
		request.setAttribute("addId", manager.getId());
	}

}
