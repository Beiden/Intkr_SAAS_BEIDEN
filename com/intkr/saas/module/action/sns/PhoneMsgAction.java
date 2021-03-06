package com.intkr.saas.module.action.sns;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.engine.SmsEngine;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-import com.intkr.saas.web.modular.PhoneEngine; 4-24 上午9:53:19
 * @version 1.0
 */
public class PhoneMsgAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgManager msgManager = IOC.get(MsgManager.class);

	public void doSend(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!RequestUtil.existParam(request, "toPhone")) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请输入要发送的手机号码");
			return;
		}
		if (!RequestUtil.existParam(request, "content")) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请输入要发送的内容");
			return;
		}
		if (RequestUtil.existParam(request, "actionMethod") && "update".equals(RequestUtil.getParam(request, "actionMethod"))) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "短信早经发送，不要重复发送");
		} else {
			String phone = RequestUtil.getParam(request, "toPhone");
			String message = RequestUtil.getParam(request, "content");
			SmsEngine.send(SessionClient.getSaas(request).getId(), phone, message);
			MsgBO msgBO = MsgAction.getParameter(request);
			msgBO.setId(msgManager.getId());
			msgManager.insert(msgBO);
			request.setAttribute("result", true);
			request.setAttribute("msg", "短信发送成功");
		}
	}

}
