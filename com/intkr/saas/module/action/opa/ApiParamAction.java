package com.intkr.saas.module.action.opa;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.opa.ApiParamBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.opa.ApiParamManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table api_param_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public class ApiParamAction extends BaseAction<ApiParamBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ApiParamManager apiParamManager = IOC.get(ApiParamManager.class);

	public ApiParamBO getBO(HttpServletRequest request) {
		ApiParamBO apiParamBO = getParameter(request);
		return apiParamBO;
	}

	public static ApiParamBO getParameter(HttpServletRequest request) {
		ApiParamBO apiParamBO = new ApiParamBO();
		apiParamBO.setId(RequestUtil.getParam(request, "id", Long.class));
		apiParamBO.setApiId(RequestUtil.getParam(request, "apiId", Long.class));
		apiParamBO.setType(RequestUtil.getParam(request, "type", String.class));
		apiParamBO.setSort(RequestUtil.getParam(request, "sort", Long.class));
		apiParamBO.setKey(RequestUtil.getParam(request, "key", String.class));
		apiParamBO.setValue(RequestUtil.getParam(request, "value", String.class));
		apiParamBO.setNote(RequestUtil.getParam(request, "desc", String.class));
		apiParamBO.setRequired(RequestUtil.getParam(request, "required", String.class));
		apiParamBO.setRemark(RequestUtil.getParam(request, "remark", String.class));
		apiParamBO.setName(RequestUtil.getParam(request, "name", String.class));
		PagerUtil.initPage(request, apiParamBO);
		return apiParamBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return apiParamManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ApiParamBO bo = getBO(request);
			long id = apiParamManager.insert(bo);
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
			ApiParamBO bo = getBO(request);
			long id = apiParamManager.insert(bo);
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

}
