package com.intkr.saas.module.screen.admin.shop.msg;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.saas.SaasAction;

/**
 * 
 * @author Beiden
 * @date 2016-4-24 下午5:16:42
 * @version 1.0
 */
public class SysMsgSend {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		SaasAction.fillSaas(request);
	}

}
