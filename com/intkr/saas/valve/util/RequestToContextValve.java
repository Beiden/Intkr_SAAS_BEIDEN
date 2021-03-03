package com.intkr.saas.valve.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunDataInternal;
import com.alibaba.citrus.turbine.util.TurbineUtil;

/**
 * 
 * @author Beiden
 * @date 2017-1-27 下午5:49:15
 * @version 1.0
 */
public class RequestToContextValve implements com.alibaba.citrus.service.pipeline.Valve {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession session;

	public void invoke(PipelineContext pipelineContext) throws Exception {
		TurbineRunDataInternal rundata = (TurbineRunDataInternal) TurbineUtil.getTurbineRunData(request);
		Context context = rundata.getContext();
		context.put("request", request);
		context.put("response", response);
		context.put("session", session);
		transfer(request, context);
		pipelineContext.invokeNext();
	}

	public static String devCompanyName = "小贝商城";

	public static String devCompanySlogan = "科技创造未来";

	public static String devCompanyShotName = "小贝商城";

	public static void transfer(HttpServletRequest request, Context context) {
		context.put("devCompanyName", devCompanyName);
		context.put("devCompanySlogan", devCompanySlogan);
		context.put("devCompanyShotName", devCompanyShotName);
		Map<String, String[]> param = request.getParameterMap();
		Map<String, Object> paramReal = new HashMap<String, Object>();
		if (param != null) {
			for (String key : param.keySet()) {
				String[] values = param.get(key);
				if (values != null && values.length == 1) {
					paramReal.put(key, values[0]);
				} else if (values != null && values.length == 0) {

				} else {
					paramReal.put(key, values);
				}
			}
		}
		Map<String, String> param2 = (Map<String, String>) request.getAttribute("_parameter");
		if (param2 != null) {
			for (String key : param2.keySet()) {
				String values = param2.get(key);
				paramReal.put(key, values);
			}
		}
		context.put("param", paramReal);
		{
			Enumeration<String> attrNames = request.getAttributeNames();
			while (attrNames.hasMoreElements()) {
				String name = attrNames.nextElement();
				context.put(name, request.getAttribute(name));
			}
		}
		{
			Enumeration<String> attrNames = request.getSession().getAttributeNames();
			while (attrNames.hasMoreElements()) {
				String name = attrNames.nextElement();
				if (context.containsKey(name)) {
					context.put("request_" + name, context.get(name));
				}
				context.put(name, request.getSession().getAttribute(name));
			}
		}
	}

}
