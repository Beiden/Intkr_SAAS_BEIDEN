package com.intkr.saas.valve.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.engine.auth.AuthorityEngine;
import com.intkr.saas.engine.auth.RightCollectEngine;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.valve.Valve;

/**
 * 鉴权
 * 
 * @author Beiden
 * @date 2016-5-24 下午4:52:44
 * @version 1.0
 */
public class AuthorityValve implements Valve {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		RightCollectEngine.collectRight(request);
		OptionManager optionManager = IOC.get(OptionManager.class);
		if (!optionManager.authorityOpen(SessionClient.getSaasId(request))) {
			return true;
		}
		if (!AuthorityEngine.hasRight(request, response)) {
			AuthorityEngine.redirect(request);
			return false;
		}
		return true;
	}

}
