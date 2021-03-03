package com.intkr.saas.module.action.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.util.RequestUtil;

/**
 * 
 * @author Beiden
 * @date 2016-6-7 下午10:05:57
 * @version 1.0
 */
public class IdEngineAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tableName = RequestUtil.getParam(request, "tableName");
		Long id = IdEngine.get(tableName);
		request.setAttribute("data", id);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功！");
	}

}
