package com.intkr.saas.valve.log;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.intkr.saas.client.log.SysLogClient;
import com.intkr.saas.module.action.log.SysLogAction;

/**
 * 
 * @author Beiden
 * @date 2011-5-24 上午10:50:03
 * @version 1.0
 */
public class SysLogValveBefore implements com.alibaba.citrus.service.pipeline.Valve {

	public static String serverStartTimeKey = "_server_start_time";

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession session;

	public void invoke(PipelineContext pipelineContext) throws Exception {
		try {
			if (SysLogClient.isIgnore(request)) {
				return;
			}
			request.setAttribute(serverStartTimeKey, new Date());
			Long id = SysLogClient.getId();
			request.setAttribute("sysLog_id", id);
			SysLogAction.cahceBuilder.put(id, (Date) request.getAttribute(serverStartTimeKey));
		} catch (Exception e) {
		} finally {
			pipelineContext.invokeNext();
		}
	}

}
