package com.intkr.saas.module.screen.admin.order;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Beiden
 * @date 2011-10-31 下午11:00:07
 * @version 1.0
 */
public class Index {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("_system_", "trade");
	}

}
