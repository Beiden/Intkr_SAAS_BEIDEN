package com.intkr.saas.module.screen.admin.mms.msg.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.mms.MsgSignatureBO;
import com.intkr.saas.manager.mms.MsgSignatureManager;
import com.intkr.saas.manager.mms.impl.MsgSignatureManagerImpl;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class MsgSignatureAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgSignatureManager manager = IOC.get(MsgSignatureManagerImpl.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mmsMsgSignatureId = RequestUtil.getParam(request, "mmsMsgSignatureId");
		MsgSignatureBO mmsMsgSignature = manager.get(mmsMsgSignatureId);
		request.setAttribute("mmsMsgSignature", mmsMsgSignature);
		request.setAttribute("addId", manager.getId());
	}

}
