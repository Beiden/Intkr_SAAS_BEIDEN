package com.intkr.saas.module.screen.admin.mms.email.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.mms.EmailSignatureBO;
import com.intkr.saas.manager.mms.EmailSignatureManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class EmailSignatureAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmailSignatureManager manager = IOC.get(EmailSignatureManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mmsEmailSignatureId = RequestUtil.getParam(request, "mmsEmailSignatureId");
		EmailSignatureBO mmsEmailSignature = manager.get(mmsEmailSignatureId);
		request.setAttribute("mmsEmailSignature", mmsEmailSignature);
		request.setAttribute("addId", manager.getId());
	}

}
