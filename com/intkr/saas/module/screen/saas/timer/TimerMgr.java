package com.intkr.saas.module.screen.saas.timer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.timer.TimerBO;
import com.intkr.saas.manager.timer.TimerManager;
import com.intkr.saas.module.action.saas.SaasAction;
import com.intkr.saas.module.action.sys.TimerAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class TimerMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private TimerManager manager = IOC.get(TimerManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		TimerBO query = TimerAction.getParameter(request);
//		query.set_pageSize(1000);
		manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		SaasAction.fillSaas(request);
	}

}
