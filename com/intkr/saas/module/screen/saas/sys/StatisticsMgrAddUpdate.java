package com.intkr.saas.module.screen.saas.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sys.StatisticsBO;
import com.intkr.saas.manager.sys.StatisticsManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Administrator
 * 
 */
public class StatisticsMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private StatisticsManager statisticsManager = IOC.get(StatisticsManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = request.getParameter("id");
			StatisticsBO statisticsBO = statisticsManager.get(id);
			request.setAttribute("dto", statisticsBO);
		} else {
			request.setAttribute("addId", statisticsManager.getId());
		}
	}

}
