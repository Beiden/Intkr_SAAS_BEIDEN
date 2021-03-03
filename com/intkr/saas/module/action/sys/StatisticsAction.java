package com.intkr.saas.module.action.sys;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sys.StatisticsBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.sys.StatisticsManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Administrator
 * 
 */
public class StatisticsAction extends BaseAction<StatisticsBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private StatisticsManager statisticsManager = IOC.get(StatisticsManager.class);

	public StatisticsBO getBO(HttpServletRequest request) {
		StatisticsBO statisticsBO = getParameter(request);
		return statisticsBO;
	}

	public static StatisticsBO getParameter(HttpServletRequest request) {
		StatisticsBO statisticsBO = new StatisticsBO();
		statisticsBO.setId(RequestUtil.getParam(request, "id", Long.class));
		statisticsBO.setType(RequestUtil.getParam(request, "type", String.class));
		statisticsBO.setNum(RequestUtil.getParam(request, "num", Long.class));
		PagerUtil.initPage(request, statisticsBO);
		return statisticsBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return statisticsManager;
	}

}
