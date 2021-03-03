package com.intkr.saas.module.screen.admin.mms.msg.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.mms.MsgWarningBO;
import com.intkr.saas.manager.mms.MsgWarningManager;
import com.intkr.saas.manager.mms.impl.MsgWarningManagerImpl;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class MsgWarningAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgWarningManager manager = IOC.get(MsgWarningManagerImpl.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mmsMsgWarningId = RequestUtil.getParam(request, "mmsMsgWarningId");
		MsgWarningBO mmsMsgWarning = manager.get(mmsMsgWarningId);
		request.setAttribute("mmsMsgWarning", mmsMsgWarning);
		request.setAttribute("addId", manager.getId());
	}

}
