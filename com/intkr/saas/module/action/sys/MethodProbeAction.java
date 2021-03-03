package com.intkr.saas.module.action.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.engine.probe.MethodStat;
import com.intkr.saas.engine.probe.ProbeEngine;
import com.intkr.saas.util.PagerUtil;

/**
 * 
 * @author Beiden
 * @date 2011-5-24 下午1:15:16
 * @version 1.0
 */
public class MethodProbeAction {

	protected static final Logger logger = LoggerFactory.getLogger(MethodProbeAction.class);

	public void doStart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProbeEngine.start();
		request.setAttribute("result", true);
		request.setAttribute("msg", "启动成功！");
	}

	public void doStop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProbeEngine.stop();
		request.setAttribute("result", true);
		request.setAttribute("msg", "停止成功！");
	}

	public void doClear(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProbeEngine.clear();
		request.setAttribute("result", true);
		request.setAttribute("msg", "清除成功！");
	}

	public static MethodStat getParameter(HttpServletRequest request) {
		MethodStat sysLogBO = new MethodStat();
		PagerUtil.initPage(request, sysLogBO);
		return sysLogBO;
	}

}
