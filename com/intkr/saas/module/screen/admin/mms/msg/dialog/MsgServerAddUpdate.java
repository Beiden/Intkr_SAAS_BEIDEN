package com.intkr.saas.module.screen.admin.mms.msg.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.mms.MsgServerBO;
import com.intkr.saas.manager.mms.MsgServerManager;
import com.intkr.saas.manager.mms.impl.MsgServerManagerImpl;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class MsgServerAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgServerManager manager = IOC.get(MsgServerManagerImpl.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mmsMsgServerId = RequestUtil.getParam(request, "mmsMsgServerId");
		MsgServerBO mmsMsgServer = manager.get(mmsMsgServerId);
		request.setAttribute("mmsMsgServer", mmsMsgServer);
		request.setAttribute("addId", manager.getId());
	}

}
