package com.intkr.saas.valve;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.pipeline.PipelineContext;

/**
 * 
 * @author Beiden
 * @date 2011-11-4 上午11:40:06
 * @version 1.0
 */
public class ValveEngine implements com.alibaba.citrus.service.pipeline.Valve {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	public static boolean valve(HttpServletRequest request, HttpServletResponse response) {
		for (Valve valve : ValveContext.valves) {
			if (!valve.execute(request, response)) {
				return false;
			}
		}
		return true;
	}

	public void invoke(PipelineContext pipelineContext) throws Exception {
		valve(request, response);
		pipelineContext.invokeNext();
	}

}
