package com.intkr.saas.module.action.opa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.opa.ApiMockBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.opa.ApiMockManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table api_mock_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public class ApiMockAction extends BaseAction<ApiMockBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ApiMockManager apiMockManager = IOC.get(ApiMockManager.class);

	public ApiMockBO getBO(HttpServletRequest request) {
		ApiMockBO apiMockBO = getParameter(request);
		return apiMockBO;
	}

	public static ApiMockBO getParameter(HttpServletRequest request) {
		ApiMockBO apiMockBO = new ApiMockBO();
		apiMockBO.setId(RequestUtil.getParam(request, "id", Long.class));
		apiMockBO.setApiId(RequestUtil.getParam(request, "apiId", Long.class));
		apiMockBO.setName(RequestUtil.getParam(request, "name", String.class));
		apiMockBO.setReq(RequestUtil.getParam(request, "req", String.class));
		apiMockBO.setRsp(RequestUtil.getParam(request, "rsp", String.class));
		PagerUtil.initPage(request, apiMockBO);
		return apiMockBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return apiMockManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ApiMockBO bo = getBO(request);
			long id = apiMockManager.insert(bo);
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
			ApiMockBO bo = getBO(request);
			apiMockManager.update(bo);
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
			ApiMockBO query = ApiMockAction.getParameter(request);
			query.set_pageSize(300);
			query.setQuery("orderBy", "id");
			query.setQuery("order", "desc");
			List<ApiMockBO> list = apiMockManager.select(query);
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
