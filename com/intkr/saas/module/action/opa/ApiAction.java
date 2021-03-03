package com.intkr.saas.module.action.opa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.opa.ApiBO;
import com.intkr.saas.domain.bo.opa.ApiExtBO;
import com.intkr.saas.domain.bo.opa.ApiParamBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.opa.ApiExtManager;
import com.intkr.saas.manager.opa.ApiManager;
import com.intkr.saas.manager.opa.ApiParamManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table api_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
public class ApiAction extends BaseAction<ApiBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ApiManager apiManager = IOC.get(ApiManager.class);

	private ApiExtManager apiExtManager = IOC.get(ApiExtManager.class);

	private ApiParamManager apiParamManager = IOC.get(ApiParamManager.class);

	public ApiBO getBO(HttpServletRequest request) {
		ApiBO apiBO = getParameter(request);
		return apiBO;
	}

	public static ApiBO getParameter(HttpServletRequest request) {
		ApiBO apiBO = new ApiBO();
		apiBO.setId(RequestUtil.getParam(request, "id", Long.class));
		apiBO.setName(RequestUtil.getParam(request, "name", String.class));
		apiBO.setTitle(RequestUtil.getParam(request, "title", String.class));
		apiBO.setPid(RequestUtil.getParam(request, "pid", Long.class));
		apiBO.setAppId(RequestUtil.getParam(request, "appId", Long.class));
		apiBO.setType(RequestUtil.getParam(request, "type", String.class));
		apiBO.setUri(RequestUtil.getParam(request, "uri", String.class));
		apiBO.setFormMethod(RequestUtil.getParam(request, "formMethod", String.class));
		apiBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		apiBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, apiBO);
		return apiBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return apiManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ApiBO bo = getBO(request);
			apiManager.insert(bo);
			bo.setSort(bo.getId().doubleValue());
			apiManager.update(bo);
			insertParams(request, bo.getId());
			insertExt(request, bo.getId());
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

	public void doMoveUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long apiId = RequestUtil.getParam(request, "apiId", Long.class);
			move(apiId, "up");
			request.setAttribute("result", true);
			request.setAttribute("msg", "上移成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doMoveDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long apiId = RequestUtil.getParam(request, "apiId", Long.class);
			move(apiId, "down");
			request.setAttribute("result", true);
			request.setAttribute("msg", "下移成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	private void move(Long apiId, String move) {
		ApiBO api = apiManager.get(apiId);
		List<ApiBO> menuTree = apiManager.getMenuTree(api.getAppId());
		List<ApiBO> findLevel = findLevel(apiId, menuTree);
		for (int i = 0; i < findLevel.size(); i++) {
			if (api.getId().equals(apiId)) {
				if (move.equals("up")) {
					if (i >= 1) {
						ApiBO prev = findLevel.get(i - 1);
						Double sort = prev.getSort();
						prev.setSort(api.getSort());
						api.setSort(sort);
						apiManager.update(api);
						apiManager.update(prev);
						return;
					}
				} else {
					if (i <= findLevel.size() - 2) {
						ApiBO next = findLevel.get(i + 1);
						Double sort = next.getSort();
						next.setSort(api.getSort());
						api.setSort(sort);
						apiManager.update(api);
						apiManager.update(next);
						return;
					}
				}
			}
		}

	}

	private List<ApiBO> findLevel(Long apiId, List<ApiBO> menuTree) {
		if (menuTree == null) {
			return null;
		}
		for (ApiBO api : menuTree) {
			if (api.getId().equals(apiId)) {
				return menuTree;
			} else {
				List<ApiBO> findLevel = findLevel(apiId, api.getChilds());
				if (findLevel != null) {
					return findLevel;
				}
			}
		}
		return null;
	}

	public void doCopy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ApiBO api = getBO(request);
			api.setSort(1d);
			Long apiId = apiManager.insert(api);
			api.setId(apiId);
			api.setSort(apiId.doubleValue());
			apiManager.update(api);
			insertParams(request, apiId);
			insertExt(request, apiId);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", api.getId());
			request.setAttribute("result", true);
			request.setAttribute("data", data);
			request.setAttribute("msg", "复制成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	private void insertParams(HttpServletRequest request, long apiId) {
		List<ApiParamBO> params = apiParamManager.selectByApiId(apiId);
		for (ApiParamBO param : params) {
			apiParamManager.delete(param.getId());
		}
		for (int index = 0; index < 100; index++) {
			String key = RequestUtil.getParam(request, "api_params[" + index + "][key]");
			if (key == null) {
				break;
			}
			String value = RequestUtil.getParam(request, "api_params[" + index + "][value]");
			String type = RequestUtil.getParam(request, "api_params[" + index + "][type]", "string");
			String required = RequestUtil.getParam(request, "api_params[" + index + "][required]", "false");
			String desc = RequestUtil.getParam(request, "api_params[" + index + "][description]");
			String remark = RequestUtil.getParam(request, "api_params[" + index + "][remark]");
			ApiParamBO apiParam = new ApiParamBO();
			apiParam.setApiId(apiId);
			apiParam.setSort(Long.parseLong(index + ""));
			apiParam.setKey(key);
			apiParam.setValue(value);
			apiParam.setType(type);
			apiParam.setRequired(required);
			apiParam.setNote(desc);
			apiParam.setRemark(remark);
			apiParamManager.insert(apiParam);
		}
	}

	private void insertExt(HttpServletRequest request, long apiId) {
		ApiExtBO old = apiExtManager.getByApiId(apiId);
		if (old != null) {
			apiExtManager.delete(old.getId());
		}

		String responseExample = RequestUtil.getParam(request, "response_example");
		String otherContent = RequestUtil.getParam(request, "other_content");
		String note = RequestUtil.getParam(request, "note");
		String header = RequestUtil.getParam(request, "header");
		String body = RequestUtil.getParam(request, "body");
		String preRequestScript = RequestUtil.getParam(request, "pre_request_script");

		ApiExtBO ext = new ApiExtBO();
		ext.setApiId(apiId);
		ext.setResponseExample(responseExample);
		ext.setOtherContent(otherContent);
		ext.setNote(note);
		ext.setHeader(header);
		ext.setBody(body);
		ext.setPreRequestScript(preRequestScript);
		apiExtManager.insert(ext);
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!RequestUtil.existParam(request, "id")) {
			doAdd(request, response);
			return;
		}
		try {
			ApiBO bo = getBO(request);
			insertParams(request, bo.getId());
			insertExt(request, bo.getId());
			apiManager.update(bo);
			request.setAttribute("result", true);
			request.setAttribute("msg", "更新成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doGetDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long apiId = RequestUtil.getParam(request, "id", Long.class);
		ApiBO api = apiManager.getDetail(apiId);
		if (api != null) {
			api.parseFeature();
		}
		request.setAttribute("data", api);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功！");
	}

}
