package com.intkr.saas.module.screen.admin.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.module.action.sns.MsgAction;
import com.intkr.saas.util.StringUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-8-29 下午1:59:07
 * @version 1.0
 */
public class MyMsg {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgManager msgManager = IOC.get(MsgManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MsgBO query = MsgAction.getParameter(request);
		query.setToUserId(SessionClient.getLoginUserId(request));
		msgManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("StringEscapeUtils", new StringEscapeUtils());
		request.setAttribute("StringUtil", new StringUtil());
	}

}
