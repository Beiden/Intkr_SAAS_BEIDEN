package com.intkr.saas.facade.wx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 验证微信推送消息的正确性
 * 
 * @author Beiden
 * @date 2016-3-22 下午8:38:50
 * @version 1.0
 */
public class MsgCheckFacade {

	public static boolean check(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");
		String timestamp = request.getParameter("timestamp");

		if (!Config.wxService.checkSignature(timestamp, nonce, signature)) {
			return false;
		}
		return true;
	}

}
