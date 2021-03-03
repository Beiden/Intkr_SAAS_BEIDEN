package com.intkr.saas.module.screen.admin.msg;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sns.MsgBO;
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
public class ChatMsgMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgManager messageManager = IOC.get(MsgManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MsgBO query = MsgAction.getParameter(request);
		query.setType(MsgType.Chat.getCode());
		messageManager.selectAndCount(query);
		List<MsgBO> list = query.getDatas();
		userManager.fill(list);
		request.setAttribute("query", query);
		request.setAttribute("list", list);
		request.setAttribute("MsgType", MsgType.Error);
	}

}
