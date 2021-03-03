package com.intkr.saas.module.action.sns;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-24 上午9:53:19
 * @version 1.0
 */
public class WeixinMsgAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgManager msgManager = IOC.get(MsgManager.class);

	public void send(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MsgBO msgBO = MsgAction.getParameter(request);
		msgBO.setId(msgManager.getId());
		msgManager.insert(msgBO);
		request.setAttribute("result", true);
		request.setAttribute("msg", "发送成功");
	}

}
