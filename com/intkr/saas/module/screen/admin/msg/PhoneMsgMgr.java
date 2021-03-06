package com.intkr.saas.module.screen.admin.msg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.type.sns.MsgChannel;
import com.intkr.saas.domain.type.sns.MsgType;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2015-4-17 下午10:25:15
 * @version 1.0
 */
public class PhoneMsgMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgManager msgManager = IOC.get(MsgManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MsgBO query = com.intkr.saas.module.action.sns.MsgAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		query.setChannel(MsgChannel.Phone.getCode());
		query = msgManager.selectAndCount(query);
		userManager.fill(query.getDatas());
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("MsgType", MsgType.Error);
	}

}
