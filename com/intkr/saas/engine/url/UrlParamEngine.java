package com.intkr.saas.engine.url;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.intkr.saas.engine.UrlRewriteEngine;

public class UrlParamEngine {

	public static void processParams(HttpServletRequest request) {
		TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
		String target = runData.getTarget();
		String uri = request.getRequestURI();
		if (uri != null && uri.length() > target.length()) {
			target = request.getRequestURI().substring(0, target.length());
		}
		Map<String, String> parameter = (Map<String, String>) request.getAttribute("_parameter");
		if (parameter == null) {
			parameter = new HashMap<String, String>();
		}
		if (parameter.containsKey("hasProcessParams")) {
			return;
		}
		if (UrlRewriteEngine.needRewrite(request)) {
			String parameterString = null;
			if (target.contains("-param-")) {
				parameterString = target.substring(target.indexOf("-param-") + 7);
			} else {
				parameterString = target.substring(target.indexOf("-p-") + 3);
			}
			String[] parameters = parameterString.split("-");
			for (int i = 0; i < parameters.length - 1; i += 2) {
				if (parameters[i] != null && !"".equals(parameters[i]) && parameters[i + 1] != null && !"".equals(parameters[i + 1])) {
					parameter.put(parameters[i], parameters[i + 1]);
				}
			}
		}
		parameter.put("hasProcessParams", "true");
		request.setAttribute("_parameter", parameter);
	}

	public static String removeParams(String uri) {
		if (uri.contains("-param-") || uri.contains("-p-")) {
			String uriPre = null;
			if (uri.contains("-param-")) {
				uriPre = uri.substring(0, uri.indexOf("-param-"));
			} else {
				uriPre = uri.substring(0, uri.indexOf("-p-"));
			}
			String uriSup = "";
			if (uri.contains(".")) {
				uriSup = uri.substring(uri.indexOf("."));
			}
			return uriPre + uriSup;
		}
		return uri;
	}

}
