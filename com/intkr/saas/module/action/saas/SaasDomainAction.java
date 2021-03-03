package com.intkr.saas.module.action.saas;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.saas.SaasDomainBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.saas.SaasDomainManager;
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
public class SaasDomainAction extends BaseAction<SaasDomainBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SaasDomainManager appDomainManager = IOC.get(SaasDomainManager.class);

	public BaseManager getBaseManager() {
		return appDomainManager;
	}

	public static SaasDomainBO getParameter(HttpServletRequest request) {
		SaasDomainBO saas = new SaasDomainBO();
		saas.setId(RequestUtil.getParam(request, "id", Long.class));
		saas.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		saas.setName(RequestUtil.getParam(request, "name"));
		saas.setDomain(RequestUtil.getParam(request, "domain"));
		saas.setStatus(RequestUtil.getParam(request, "status"));
		saas.setNote(RequestUtil.getParam(request, "note"));
		saas.setThemeId(RequestUtil.getParam(request, "themeId", Long.class));
		saas.setThemeName(RequestUtil.getParam(request, "themeName"));
		if (RequestUtil.existParam(request, "domainLike")) {
			saas.setQuery("domainLike", RequestUtil.getParam(request, "domainLike"));
			saas.setQuery("domainLikeSQL", "%" + RequestUtil.getParam(request, "domainLike") + "%");
		}
		PagerUtil.initPage(request, saas);
		return saas;
	}

	public SaasDomainBO getBO(HttpServletRequest request) {
		return getParameter(request);
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			SaasDomainBO insert = getBO(request);
			if (appDomainManager.getByDomain(insert.getDomain()) != null) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "添加失败，域名已被使用");
				return;
			}
			long id = appDomainManager.insert(insert);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", insert.getId());
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
			SaasDomainBO update = getBO(request);
			SaasDomainBO old = appDomainManager.get(update.getId());
			if (!old.getDomain().equals(update.getDomain()) && appDomainManager.getByDomain(update.getDomain()) != null) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "更新失败，域名已被使用");
				return;
			}
			appDomainManager.invalidateCache(old.getDomain());
			appDomainManager.update(update);
			request.setAttribute("result", true);
			request.setAttribute("msg", "更新成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
