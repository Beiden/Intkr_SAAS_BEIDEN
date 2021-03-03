package com.intkr.saas.valve.auth.account;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.valve.Valve;

/**
 * 
 * @author Beiden
 * @date 2016-6-7 下午10:11:43
 * @version 1.0
 */
public class PasswordInitValve implements Valve {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private List<String> exculdeUrlParterns = new ArrayList<String>();
	{
		exculdeUrlParterns.add("/shopAdmin/account/passwordModification.html");
	};

	private List<String> urlParterns = new ArrayList<String>();
	{
		urlParterns.add("/shopAdmin/*");
	};

	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		if (exculde(request)) {
			return true;
		}
		if (!inculde(request)) {
			return true;
		}
		UserBO user = SessionClient.getLoginUser(request);
		if (user != null && user.isPasswordEquals("12345678")) {
			request.setAttribute("msg", "第一次使用系统，请先设置密码！线上正式运营环境，请误使用他人帐号。");
			RequestUtil.forward(request, "/shopAdmin/account/passwordModification.html");
			return false;
		}
		return true;
	}

	private boolean inculde(HttpServletRequest request) {
		for (String urlPartern : urlParterns) {
			Pattern p = Pattern.compile(urlPartern);
			Matcher m = p.matcher(request.getRequestURI());
			if (m.find()) {
				return true;
			}
		}
		return false;
	}

	private boolean exculde(HttpServletRequest request) {
		for (String urlPartern : exculdeUrlParterns) {
			Pattern p = Pattern.compile(urlPartern);
			Matcher m = p.matcher(request.getRequestURI());
			if (m.find()) {
				return true;
			}
		}
		return false;
	}

}
