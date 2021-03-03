package com.intkr.saas.valve.log;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.intkr.saas.client.log.SysLogClient;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.log.SysLogBO;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.log.SysLogManager;
import com.intkr.saas.util.IpUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.ResponseUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-5-24 上午10:50:03
 * @version 1.0
 */
public class SysLogValveAfter implements com.alibaba.citrus.service.pipeline.Valve {

	public static String serverEndTimeKey = "server_end_time";

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OptionManager optionManager = IOC.get(OptionManager.class);

	protected SysLogManager sysLogManager = IOC.get(SysLogManager.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession session;

	private Runnable getViewRecord(HttpServletRequest request, HttpServletResponse response) {
		Date startTime = (Date) request.getAttribute(SysLogValveBefore.serverStartTimeKey);
		Date endTime = (Date) request.getAttribute(serverEndTimeKey);
		Long serverCostTime = endTime.getTime() - startTime.getTime();
		Long userId = null;
		if (userId == null && SessionClient.isLogin(request)) {
			userId = SessionClient.getLoginUserId(request);
		}
		String goUrl = RequestUtil.getGoUrl(request);
		String queryString = request.getQueryString();
		TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
		String goAction = runData.getAction();
		String actionEvent = runData.getActionEvent();
		if (goAction == null || actionEvent == null) {
			goAction = null;
		} else {
			goAction = goAction + "." + actionEvent;
		}
		String getUrl = ResponseUtil.getSenRedirect(request);
		if (getUrl == null) {
			getUrl = request.getRequestURI();
		}
		String ip = IpUtil.getIp(request);
		String referer = request.getHeader("Referer");
		SysLogBO viewRecord = new SysLogBO();
		viewRecord.setId((Long) request.getAttribute("sysLog_id"));
		viewRecord.setSaasId(SessionClient.getSaasId(request));
		viewRecord.setServerCostTime(serverCostTime);
		viewRecord.setUserId(userId);
		viewRecord.setGoAction(goAction);
		viewRecord.setGoUrl(goUrl);
		viewRecord.setQueryString(queryString);
		viewRecord.setGetUrl(getUrl);
		viewRecord.setIp(ip);
		viewRecord.setReferer(referer);
		viewRecord.setParams(request.getParameterMap().toString());
		Runnable run = new SysLogRecordRunner(viewRecord);
		return run;
	}

	public void invoke(PipelineContext pipelineContext) throws Exception {
		try {
			if (SysLogClient.isIgnore(request)) {
				return;
			}
			request.setAttribute(serverEndTimeKey, new Date());
			Runnable run = getViewRecord(request, response);
			SysLogClient.threadPool.execute(run);
		} catch (Exception e) {
			//
		} finally {
			pipelineContext.invokeNext();
		}
	}

}
