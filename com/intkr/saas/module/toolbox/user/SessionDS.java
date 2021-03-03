package com.intkr.saas.module.toolbox.user;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.module.toolbox.BaseToolBox;

/**
 * 
 * @author Beiden
 * @date 2016-3-29 上午10:44:06
 * @version 1.0
 */
public class SessionDS extends BaseToolBox {

	public void set(HttpServletRequest request, String name, Object value) {
		request.getSession().setAttribute(name, value);
	}

	public void remove(HttpServletRequest request, String name) {
		request.getSession().removeAttribute(name);
	}

}
