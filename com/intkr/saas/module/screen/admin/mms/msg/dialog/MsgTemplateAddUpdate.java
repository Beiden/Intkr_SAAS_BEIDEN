package com.intkr.saas.module.screen.admin.mms.msg.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.mms.MsgTemplateBO;
import com.intkr.saas.manager.mms.MsgTemplateManager;
import com.intkr.saas.manager.mms.impl.MsgTemplateManagerImpl;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class MsgTemplateAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgTemplateManager manager = IOC.get(MsgTemplateManagerImpl.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mmsMsgTemplateId = RequestUtil.getParam(request, "mmsMsgTemplateId");
		MsgTemplateBO mmsMsgTemplate = manager.get(mmsMsgTemplateId);
		request.setAttribute("mmsMsgTemplate", mmsMsgTemplate);
		request.setAttribute("addId", manager.getId());
	}

}
