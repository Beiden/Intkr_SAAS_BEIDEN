package com.intkr.saas.module.screen.saas.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sys.MonitorBO;
import com.intkr.saas.manager.sys.MonitorManager;
import com.intkr.saas.module.action.sys.MonitorAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Administrator
 * 
 */
public class MonitorMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MonitorManager monitorManager = IOC.get(MonitorManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MonitorBO query = MonitorAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		monitorManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
