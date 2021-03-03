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
public class SecurePasswordInitValve implements Valve {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private List<String> urlParterns = new ArrayList<String>();
	{
		urlParterns.add("/member/fund/fundMgr.html");
		urlParterns.add("/member/fund/fundRecharge.html");
		urlParterns.add("/member/fund/fundWithdraw.html");
		urlParterns.add("/member/fund/fundTransfer.html");
		urlParterns.add("/payment/payments.html");
		urlParterns.add("/trade/payments.html");
	};

	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		if (!match(request)) {
			return true;
		}
		UserBO user = SessionClient.getLoginUser(request);
		if (user.getSecurePassword() == null || "".equals(user.getSecurePassword())) {
			request.setAttribute("firstTimeUseFundMgr", "第一次使用资金管理功能，请先设置安全密码！");
			RequestUtil.forward(request, "/shop/member/fund/securePasswordInit.html");
		}
		return false;
	}

	private boolean match(HttpServletRequest request) {
		for (String urlPartern : urlParterns) {
			Pattern p = Pattern.compile(urlPartern);
			Matcher m = p.matcher(request.getRequestURI());
			if (m.find()) {
				return true;
			}
		}
		return false;
	}

}
