package com.intkr.saas.valve.lang;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.pipeline.PipelineContext;

/**
 * 翻译不同语言
 * 
 * @author Beiden
 * @date 2011-5-24 上午10:50:03
 * @version 1.0
 */
public class LangValve implements com.alibaba.citrus.service.pipeline.Valve {

	public static String serverEndTimeKey = "server_end_time";

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession session;

	public void invoke(PipelineContext pipelineContext) throws Exception {
		try {
			String msg = (String) request.getAttribute("msg");
			if (msg != null || !"".equals(msg)) {
				// 翻译不同语言
			}
		} catch (Exception e) {
			//
		} finally {
			pipelineContext.invokeNext();
		}
	}

}
