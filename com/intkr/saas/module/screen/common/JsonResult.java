package com.intkr.saas.module.screen.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.intkr.saas.util.RequestUtil;

/**
 * 
 * @author Beiden
 * @date 2017-1-25 下午3:50:58
 * @version 1.0
 */
public class JsonResult {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	public static Boolean jsonResultWarning = false;

	public static String jsonResultWarningMsg = "";

	public Object execute(Context context) {
		return processJsonResult(request, context);
	}

	private Object processJsonResult(HttpServletRequest request, Context context) {
		Map<String, Object> jsonResultMap = new HashMap<String, Object>();
		if (jsonResultWarning && request.getRequestURI().contains("jsonResult")) {
			jsonResultMap.put("result", "false");
			jsonResultMap.put("msg", jsonResultWarningMsg);
			return jsonResultMap;
		}
		if (RequestUtil.existAttr(request, "result")) {
			jsonResultMap.put("result", request.getAttribute("result"));
		}
		if (RequestUtil.existAttr(request, "msg")) {
			jsonResultMap.put("msg", request.getAttribute("msg"));
		}
		if (RequestUtil.existAttr(request, "data")) {
			jsonResultMap.put("data", request.getAttribute("data"));
		}
		if (context.containsKey("result")) {
			jsonResultMap.put("result", context.get("result"));
		}
		if (context.containsKey("msg")) {
			jsonResultMap.put("msg", context.get("msg"));
		}
		if (context.containsKey("data")) {
			jsonResultMap.put("data", context.get("data"));
		}
		if (!jsonResultMap.containsKey("result")) {
			jsonResultMap.put("result", false);
			jsonResultMap.put("msg", "系统异常！");
		}
		return jsonResultMap;
	}

	public Boolean getJsonResultWarning() {
		return jsonResultWarning;
	}

	public void setJsonResultWarning(Boolean jsonResultWarning) {
		this.jsonResultWarning = jsonResultWarning;
	}

	public String getJsonResultWarningMsg() {
		return jsonResultWarningMsg;
	}

	public void setJsonResultWarningMsg(String jsonResultWarningMsg) {
		this.jsonResultWarningMsg = jsonResultWarningMsg;
	}

}
