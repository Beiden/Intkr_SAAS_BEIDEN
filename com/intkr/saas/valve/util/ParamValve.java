package com.intkr.saas.valve.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.engine.url.UrlParamEngine;
import com.intkr.saas.valve.Valve;

/**
 * 
 * 
 * @author Beiden
 * @date 2011-10-3 上午11:12:35
 * @version 1.0
 */
public class ParamValve implements Valve {

	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		UrlParamEngine.processParams(request);
		return true;
	}

}