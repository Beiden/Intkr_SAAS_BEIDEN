package com.intkr.saas.module.action.saas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.saas.SaasClientBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.saas.SaasClientManager;
import com.intkr.saas.manager.saas.SaasConfigManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-5-7 下午12:46:55
 * @version 1.0
 */
public class SaasAction extends BaseAction<SaasClientBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SaasClientManager saasManager = IOC.get(SaasClientManager.class);

	private SaasConfigManager appConfigManager = IOC.get(SaasConfigManager.class);

	public void doSetTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long themeId = RequestUtil.getParam(request, "themeId", Long.class);
		SaasClientBO app = SessionClient.getSaas(request);
		app.getConfig().setThemeId(themeId);
		appConfigManager.update(app.getConfig());
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

	public void doGetFull(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SaasClientBO saas = SessionClient.getSaas(request);
		request.setAttribute("result", true);
		request.setAttribute("data", saas);
		request.setAttribute("msg", "获取成功");
	}

	public BaseManager getBaseManager() {
		return saasManager;
	}

	public static SaasClientBO getParameter(HttpServletRequest request) {
		SaasClientBO saas = new SaasClientBO();
		saas.setId(RequestUtil.getParam(request, "id", Long.class));
		saas.setName(RequestUtil.getParam(request, "name"));
		saas.setFeature(RequestUtil.getParam(request, "feature"));
		saas.setNote(RequestUtil.getParam(request, "note"));
		saas.setSort(RequestUtil.getParam(request, "sort", Double.class));
		saas.setIsDefault(RequestUtil.getParam(request, "isDefault", Integer.class));
		PagerUtil.initPage(request, saas);
		return saas;
	}

	public SaasClientBO getBO(HttpServletRequest request) {
		return getParameter(request);
	}

	public static void fillSaas(HttpServletRequest request) {
		SaasClientManager saasManager = IOC.get(SaasClientManager.class);
		Long saasId = RequestUtil.getParam(request, "saasId", Long.class);
		SaasClientBO saas = saasManager.getFull(saasId);
		request.setAttribute("saas", saas);
	}

	/**
	 * saas用户注册
	 */
	public void doRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SaasClientBO bo = getBO(request);
		long id = getBaseManager().insert(bo);
		request.setAttribute("result", true);
		request.setAttribute("msg", "注册成功！");
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doAdd(request, response);
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			SaasClientBO bo = getBO(request);
			if (bo.getIsDefault() == 1) {
				SaasClientBO defaultBO = saasManager.getDefault();
				if (defaultBO != null && !defaultBO.getId().equals(bo.getId())) {
					defaultBO.setIsDefault(2);
					saasManager.update(defaultBO);
				}
			}
			saasManager.update(bo);
			request.setAttribute("result", true);
			request.setAttribute("msg", "更新成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
