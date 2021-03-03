package com.intkr.saas.module.action.opa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.opa.ApiInvokeLogBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.opa.ApiInvokeLogManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table api_invoke_log_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public class ApiInvokeLogAction extends BaseAction<ApiInvokeLogBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ApiInvokeLogManager apiInvokeLogManager = IOC.get(ApiInvokeLogManager.class);

	public ApiInvokeLogBO getBO(HttpServletRequest request) {
		ApiInvokeLogBO apiInvokeLogBO = getParameter(request);
		return apiInvokeLogBO;
	}

	public static ApiInvokeLogBO getParameter(HttpServletRequest request) {
		ApiInvokeLogBO apiInvokeLogBO = new ApiInvokeLogBO();
		apiInvokeLogBO.setId(RequestUtil.getParam(request, "id", Long.class));
		apiInvokeLogBO.setApiId(RequestUtil.getParam(request, "apiId", Long.class));
		apiInvokeLogBO.setApiMethod(RequestUtil.getParam(request, "apiMethod", String.class));
		apiInvokeLogBO.setName(RequestUtil.getParam(request, "name", String.class));
		apiInvokeLogBO.setType(RequestUtil.getParam(request, "type", String.class));
		apiInvokeLogBO.setReq(RequestUtil.getParam(request, "req", String.class));
		apiInvokeLogBO.setRsp(RequestUtil.getParam(request, "rsp", String.class));
		PagerUtil.initPage(request, apiInvokeLogBO);
		return apiInvokeLogBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return apiInvokeLogManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ApiInvokeLogBO bo = getBO(request);
			long id = apiInvokeLogManager.insert(bo);
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
			ApiInvokeLogBO bo = getBO(request);
			apiInvokeLogManager.update(bo);
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
			ApiInvokeLogBO query = ApiInvokeLogAction.getParameter(request);
			query.set_pageSize(300);
			query.setQuery("orderBy", "id");
			query.setQuery("order", "desc");
			List<ApiInvokeLogBO> list = apiInvokeLogManager.select(query);
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
