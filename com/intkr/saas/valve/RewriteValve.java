package com.intkr.saas.valve;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.intkr.saas.engine.UrlRewriteEngine;
import com.intkr.saas.engine.auth.AuthorityEngine;

/**
 * 
 * @author Beiden
 * @date 2011-10-3 上午11:12:35
 * @version 1.0
 */
public class RewriteValve implements com.alibaba.citrus.service.pipeline.Valve {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession session;

	public void invoke(PipelineContext pipelineContext) throws Exception {
		if (!AuthorityEngine.hasRight(request, response)) {
			pipelineContext.invokeNext();
			return;
		}
		TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
		if (UrlRewriteEngine.needRewrite(request) && request.getAttribute("hasRewrite") == null) {
			runData.setRedirectTarget(UrlRewriteEngine.getRealTarget(request));
			request.setAttribute("hasRewrite", true);
		}
		pipelineContext.invokeNext();
	}

}