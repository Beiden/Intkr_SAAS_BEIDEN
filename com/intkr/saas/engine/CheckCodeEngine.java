package com.intkr.saas.engine;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.util.RequestUtil;

/**
 * 
 * @author Beiden
 * @date 2011-7-25 下午5:32:41
 * @version 1.0
 */
public class CheckCodeEngine {

	/**
	 * 验证用户输入的验证码和session里的验证码是否一致
	 * 
	 * @param request
	 * @param response
	 * @param code
	 * @return
	 */
	public static boolean check(HttpServletRequest request, String code) {
		String codeFormUser = RequestUtil.getParam(request, code);
		if (codeFormUser == null || "".equals(codeFormUser)) {
			return false;
		}
		String codeFormServer = (String) request.getSession().getAttribute(code);
		if (codeFormServer == null || "".equals(codeFormServer)) {
			return false;
		}
		boolean result = codeFormUser.equalsIgnoreCase(codeFormServer);
		return result;
	}

	public static boolean verifyCode(HttpServletRequest request) {
		if (!CheckCodeEngine.check(request, "checkCode")) {
			request.setAttribute("msg", "验证码错误，请点击验证码刷新，重新输入！");
			request.setAttribute("result", false);
			return false;
		}
		request.getSession().removeAttribute("checkCode");
		return true;
	}

}
