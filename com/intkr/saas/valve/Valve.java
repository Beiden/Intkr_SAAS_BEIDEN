package com.intkr.saas.valve;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-24 下午04:22:56
 * @version 1.0
 */
public interface Valve {

	/**
	 * 
	 * @param request
	 * @param response
	 * @return true:通过 false:不通过
	 */
	public boolean execute(HttpServletRequest request, HttpServletResponse response);

}
