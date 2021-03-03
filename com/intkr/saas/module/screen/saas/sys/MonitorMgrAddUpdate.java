package com.intkr.saas.module.screen.saas.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sys.MonitorBO;
import com.intkr.saas.manager.sys.MonitorManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Administrator
 * 
 */
public class MonitorMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MonitorManager monitorManager = IOC.get(MonitorManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = request.getParameter("id");
			MonitorBO monitorBO = monitorManager.get(id);
			request.setAttribute("dto", monitorBO);
		} else {
			request.setAttribute("addId", monitorManager.getId());
		}
	}

}
