package com.intkr.saas.module.action.opa;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.opa.ApiTestCaseStepBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.opa.ApiTestCaseStepManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table api_test_case_step_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
public class ApiTestCaseStepAction extends BaseAction<ApiTestCaseStepBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ApiTestCaseStepManager apiTestCaseStepManager = IOC.get(ApiTestCaseStepManager.class);

	public ApiTestCaseStepBO getBO(HttpServletRequest request) {
		ApiTestCaseStepBO apiTestCaseStepBO = getParameter(request);
		return apiTestCaseStepBO;
	}

	public static ApiTestCaseStepBO getParameter(HttpServletRequest request) {
		ApiTestCaseStepBO apiTestCaseStepBO = new ApiTestCaseStepBO();
		apiTestCaseStepBO.setId(RequestUtil.getParam(request, "id", Long.class));
		apiTestCaseStepBO.setApiId(RequestUtil.getParam(request, "apiId", Long.class));
		apiTestCaseStepBO.setApiMethod(RequestUtil.getParam(request, "apiMethod", String.class));
		apiTestCaseStepBO.setName(RequestUtil.getParam(request, "name", String.class));
		apiTestCaseStepBO.setType(RequestUtil.getParam(request, "type", String.class));
		apiTestCaseStepBO.setReq(RequestUtil.getParam(request, "req", String.class));
		apiTestCaseStepBO.setRsp(RequestUtil.getParam(request, "rsp", String.class));
		PagerUtil.initPage(request, apiTestCaseStepBO);
		return apiTestCaseStepBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return apiTestCaseStepManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ApiTestCaseStepBO bo = getBO(request);
			long id = apiTestCaseStepManager.insert(bo);
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
			ApiTestCaseStepBO bo = getBO(request);
			apiTestCaseStepManager.update(bo);
			request.setAttribute("result", true);
			request.setAttribute("msg", "更新成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
