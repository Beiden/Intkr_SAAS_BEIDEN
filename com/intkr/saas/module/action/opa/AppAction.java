package com.intkr.saas.module.action.opa;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.opa.AppBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.opa.AppManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table app_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
public class AppAction extends BaseAction<AppBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AppManager appManager = IOC.get(AppManager.class);

	public AppBO getBO(HttpServletRequest request) {
		AppBO appBO = getParameter(request);
		return appBO;
	}

	public static AppBO getParameter(HttpServletRequest request) {
		AppBO appBO = new AppBO();
		appBO.setId(RequestUtil.getParam(request, "id", Long.class));
		appBO.setName(RequestUtil.getParam(request, "name", String.class));
		appBO.setDomain(RequestUtil.getParam(request, "domain", String.class));
		PagerUtil.initPage(request, appBO);
		return appBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return appManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			AppBO bo = getBO(request);
			long id = appManager.insert(bo);
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
			AppBO bo = getBO(request);
			appManager.update(bo);
			request.setAttribute("result", true);
			request.setAttribute("msg", "更新成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
