package com.intkr.saas.valve.auth.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.distributed.session.SessionClientDistImpl;
import com.intkr.saas.distributed.session.SkeyEngine;
import com.intkr.saas.util.AESUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.valve.Valve;

/**
 * 
 * @author Beiden
 * @date 2017-12-3
 * @version 1.0
 */
public class LoginByUserIdValve implements Valve {

	private String urlKey = "j3hBTHVwgGJijF9B3ixJ9RopQrad+ZpCQADdtPbu2Tk=";

	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		String key = geturlKey(urlKey);
		if (!RequestUtil.existParam(request, key)) {
			return true;
		}
		Long userId = RequestUtil.getParam(request, key, Long.class);
		SessionClientDistImpl.login(request, response, userId);
		return true;
	}

	private static String geturlKey(String skey) {
		if (skey == null || "".equals(skey)) {
			return null;
		}
		try {
			String content = AESUtil.AESDncode(SkeyEngine.encodeRules, skey);
			return content;
		} catch (Exception e) {
			return null;
		}
	}

}