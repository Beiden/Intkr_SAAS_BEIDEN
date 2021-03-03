package com.intkr.saas.module.action.opa;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.opa.ApiExtBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.opa.ApiExtManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table api_ext_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public class ApiExtAction extends BaseAction<ApiExtBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ApiExtManager apiExtManager = IOC.get(ApiExtManager.class);

	public ApiExtBO getBO(HttpServletRequest request) {
		ApiExtBO apiExtBO = getParameter(request);
		return apiExtBO;
	}

	public static ApiExtBO getParameter(HttpServletRequest request) {
		ApiExtBO apiExtBO = new ApiExtBO();
		apiExtBO.setId(RequestUtil.getParam(request, "id", Long.class));
		apiExtBO.setApiId(RequestUtil.getParam(request, "apiId", Long.class));
		apiExtBO.setResponseExample(RequestUtil.getParam(request, "responseExample", String.class));
		apiExtBO.setOtherContent(RequestUtil.getParam(request, "otherContent", String.class));
		apiExtBO.setResponseType(RequestUtil.getParam(request, "responseType", String.class));
		apiExtBO.setPreRequestScript(RequestUtil.getParam(request, "preRequestScript", String.class));
		apiExtBO.setOther(RequestUtil.getParam(request, "other", String.class));
		apiExtBO.setOtherType(RequestUtil.getParam(request, "otherType", String.class));
		apiExtBO.setHeader(RequestUtil.getParam(request, "header", String.class));
		apiExtBO.setBody(RequestUtil.getParam(request, "body", String.class));
		apiExtBO.setNote(RequestUtil.getParam(request, "note", String.class));
		PagerUtil.initPage(request, apiExtBO);
		return apiExtBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return apiExtManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ApiExtBO bo = getBO(request);
			long id = apiExtManager.insert(bo);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", bo.getId());
			request.setAttribute("result", true);
			request.setAttribute("data", data);
			request.setAttribute("msg", "添加成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ApiExtBO bo = getBO(request);
			apiExtManager.update(bo);
			request.setAttribute("result", true);
			request.setAttribute("msg", "更新成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
