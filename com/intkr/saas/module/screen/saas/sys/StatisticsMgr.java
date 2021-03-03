package com.intkr.saas.module.screen.saas.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sys.StatisticsBO;
import com.intkr.saas.manager.sys.StatisticsManager;
import com.intkr.saas.module.action.sys.StatisticsAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-10-2 上午11:31:26
 * @version 1.0
 */
public class StatisticsMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private StatisticsManager statisticsManager = IOC.get(StatisticsManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		StatisticsBO query = StatisticsAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		statisticsManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
