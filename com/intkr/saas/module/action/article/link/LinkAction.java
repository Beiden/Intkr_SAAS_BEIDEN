package com.intkr.saas.module.action.article.link;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.link.LinkBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.saas.LinkManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-10-2 下午9:51:51
 * @version 1.0
 */
public class LinkAction extends BaseAction<LinkBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LinkManager linkManager = IOC.get(LinkManager.class);

	public LinkBO getBO(HttpServletRequest request) {
		LinkBO linkBO = getParameter(request);
		return linkBO;
	}

	public static LinkBO getParameter(HttpServletRequest request) {
		LinkBO linkBO = new LinkBO();
		linkBO.setId(RequestUtil.getParam(request, "id", Long.class));
		linkBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		linkBO.setType(RequestUtil.getParam(request, "type"));
		linkBO.setName(RequestUtil.getParam(request, "name"));
		linkBO.setUrl(RequestUtil.getParam(request, "url"));
		linkBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		PagerUtil.initPage(request, linkBO);
		return linkBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return linkManager;
	}
}
