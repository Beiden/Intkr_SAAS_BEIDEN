package com.intkr.saas.module.action.opa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.opa.ApiTestCaseBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.opa.ApiTestCaseManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table api_test_case_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
public class ApiTestCaseAction extends BaseAction<ApiTestCaseBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ApiTestCaseManager apiTestCaseManager = IOC.get(ApiTestCaseManager.class);

	public ApiTestCaseBO getBO(HttpServletRequest request) {
		ApiTestCaseBO apiTestCaseBO = getParameter(request);
		return apiTestCaseBO;
	}

	public static ApiTestCaseBO getParameter(HttpServletRequest request) {
		ApiTestCaseBO apiTestCaseBO = new ApiTestCaseBO();
		apiTestCaseBO.setId(RequestUtil.getParam(request, "id", Long.class));
		apiTestCaseBO.setApiId(RequestUtil.getParam(request, "apiId", Long.class));
		apiTestCaseBO.setTargetApiId(RequestUtil.getParam(request, "targetApiId", Long.class));
		apiTestCaseBO.setName(RequestUtil.getParam(request, "name", String.class));
		apiTestCaseBO.setApiMethod(RequestUtil.getParam(request, "apiMethod", String.class));
		apiTestCaseBO.setUri(RequestUtil.getParam(request, "uri", String.class));
		apiTestCaseBO.setPreReq(RequestUtil.getParam(request, "preReq", String.class));
		apiTestCaseBO.setReq(RequestUtil.getParam(request, "req", String.class));
		apiTestCaseBO.setRsp(RequestUtil.getParam(request, "rsp", String.class));
		apiTestCaseBO.setResult(RequestUtil.getParam(request, "result", String.class));
		PagerUtil.initPage(request, apiTestCaseBO);
		return apiTestCaseBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return apiTestCaseManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ApiTestCaseBO bo = getBO(request);
			long id = apiTestCaseManager.insert(bo);
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
			ApiTestCaseBO bo = getBO(request);
			apiTestCaseManager.update(bo);
			request.setAttribute("result", true);
			request.setAttribute("msg", "更新成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ApiTestCaseBO query = ApiTestCaseAction.getParameter(request);
			query.set_pageSize(300);
			query.setQuery("orderBy", "id");
			query.setQuery("order", "desc");
			List<ApiTestCaseBO> list = apiTestCaseManager.select(query);
			request.setAttribute("data", list);
			request.setAttribute("result", true);
			request.setAttribute("msg", "搜索成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
