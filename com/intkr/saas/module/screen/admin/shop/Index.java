package com.intkr.saas.module.screen.admin.shop;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-11-3 下午05:43:58
 * @version 1.0
 */
public class Index {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("_system_", "shopIndex");
	}

}
