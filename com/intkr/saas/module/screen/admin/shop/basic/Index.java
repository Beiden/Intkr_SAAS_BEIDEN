package com.intkr.saas.module.screen.admin.shop.basic;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 上午8:58:40
 * @version 1.0
 */
public class Index {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("_system_", "basic");
	}

}
