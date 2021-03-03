package com.intkr.saas.module.action.sys;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sys.MonitorBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.sys.MonitorManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Administrator
 * 
 */
public class MonitorAction extends BaseAction<MonitorBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MonitorManager monitorManager = IOC.get(MonitorManager.class);

	public MonitorBO getBO(HttpServletRequest request) {
		MonitorBO monitorBO = getParameter(request);
		return monitorBO;
	}

	public static MonitorBO getParameter(HttpServletRequest request) {
		MonitorBO monitorBO = new MonitorBO();
		monitorBO.setId(RequestUtil.getParam(request, "id", Long.class));
		monitorBO.setType(RequestUtil.getParam(request, "type", String.class, ""));
		monitorBO.setResult(RequestUtil.getParam(request, "result", String.class, ""));
		PagerUtil.initPage(request, monitorBO);
		return monitorBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return monitorManager;
	}

}
