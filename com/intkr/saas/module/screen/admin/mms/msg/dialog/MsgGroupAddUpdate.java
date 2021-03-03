package com.intkr.saas.module.screen.admin.mms.msg.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.mms.MsgGroupBO;
import com.intkr.saas.manager.mms.MsgGroupManager;
import com.intkr.saas.manager.mms.impl.MsgGroupManagerImpl;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class MsgGroupAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgGroupManager manager = IOC.get(MsgGroupManagerImpl.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mmsMsgGroupId = RequestUtil.getParam(request, "mmsMsgGroupId");
		MsgGroupBO mmsMsgGroup = manager.get(mmsMsgGroupId);
		request.setAttribute("mmsMsgGroup", mmsMsgGroup);
		request.setAttribute("addId", manager.getId());
	}

}
