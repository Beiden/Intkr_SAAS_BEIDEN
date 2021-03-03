package com.intkr.saas.module.screen.saas.tool;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.engine.probe.MethodStat;
import com.intkr.saas.engine.probe.ProbeEngine;
import com.intkr.saas.module.action.sys.MethodProbeAction;

/**
 * 
 * @author Beiden
 * @date 2011-5-24 上午10:28:56
 * @version 1.0
 */
public class MethodProbeMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MethodStat query = MethodProbeAction.getParameter(request);
		List<MethodStat> list = ProbeEngine.getStat(query);
		request.setAttribute("query", query);
		request.setAttribute("list", list);
	}

}
