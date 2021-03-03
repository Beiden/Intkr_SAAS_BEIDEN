package com.intkr.saas.module.screen.admin.shop.msg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.type.sns.MsgChannel;
import com.intkr.saas.domain.type.sns.MsgType;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.sns.MsgAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2015-4-17 下午10:25:15
 * @version 1.0
 */
public class WeixinMsgMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgManager messageManager = IOC.get("MsgManager");

	private UserManager userManager = IOC.get("UserManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MsgBO query = MsgAction.getParameter(request);
		query.setChannel(MsgChannel.Phone.getCode());
		query = messageManager.selectAndCount(query);
		query.setDatas(userManager.fill(query.getDatas()));
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("MsgType", MsgType.Error);
	}

}
