package com.intkr.saas.module.screen.admin.shop.json;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-11-3 下午03:49:42
 * @version 1.0
 */
public class HasLogin {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("msg", "yes");
	}

}
