package com.intkr.saas.facade.wx;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.mp.bean.WxMpXmlMessage;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * @author Beiden
 * @date 2016-3-22 下午8:43:02
 * @version 1.0
 */
public class NotifyMsgFacade {

	protected WxMpXmlMessage service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");
		String timestamp = request.getParameter("timestamp");
		if (!MsgCheckFacade.check(request, response)) {
			return null;
		}
		String echostr = request.getParameter("echostr");
		if (StringUtils.isNotBlank(echostr)) {
			// 说明是一个仅仅用来验证的请求，回显echostr
			response.getWriter().println(echostr);
			return null;
		}
		String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ? "raw" : request.getParameter("encrypt_type");
		if ("raw".equals(encryptType)) {
			// 明文传输的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
			return inMessage;
		}
		if ("aes".equals(encryptType)) {
			// 是aes加密的消息
			String msgSignature = request.getParameter("msg_signature");
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), Config.config, timestamp, nonce, msgSignature);
			return inMessage;
		}
		return null;
	}

}
