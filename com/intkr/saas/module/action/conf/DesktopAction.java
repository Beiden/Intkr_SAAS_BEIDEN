package com.intkr.saas.module.action.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.conf.DesktopBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.conf.DesktopManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 上午9:10:56
 * @version 1.0
 */
public class DesktopAction extends BaseAction<DesktopBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DesktopManager carDesktopManager = IOC.get(DesktopManager.class);

	public DesktopBO getBO(HttpServletRequest request) {
		DesktopBO carDesktopBO = getParameter(request);
		return carDesktopBO;
	}

	public static DesktopBO getParameter(HttpServletRequest request) {
		DesktopBO carDesktopBO = new DesktopBO();
		carDesktopBO.setId(RequestUtil.getParam(request, "id", Long.class));
		carDesktopBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		carDesktopBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		carDesktopBO.setName(RequestUtil.getParam(request, "name"));
		carDesktopBO.setImg(RequestUtil.getParam(request, "img"));
		carDesktopBO.setUrl(RequestUtil.getParam(request, "url"));
		carDesktopBO.setFeature(RequestUtil.getParam(request, "feature"));
		carDesktopBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		PagerUtil.initPage(request, carDesktopBO);
		return carDesktopBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return carDesktopManager;
	}

	public void doSet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] names = request.getParameterValues("name");
		String[] urls = request.getParameterValues("url");
		String[] imgs = request.getParameterValues("img");
		carDesktopManager.clear(SessionClient.getLoginUserId(request));
		if (names != null) {
			for (Integer i = 0; i < names.length; i++) {
				DesktopBO qb = new DesktopBO();
				qb.setId(carDesktopManager.getId());
				qb.setUserId(SessionClient.getLoginUserId(request));
				qb.setName(names[i]);
				qb.setImg(imgs[i]);
				qb.setUrl(urls[i]);
				qb.setSort(i.doubleValue());
				carDesktopManager.insert(qb);
			}
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "编辑成功");
		UserLogClient.log(request, "编辑", "首页快捷按钮");
	}

}
