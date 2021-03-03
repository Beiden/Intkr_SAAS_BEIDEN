package com.intkr.saas.module.action.article.tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.tool.ToolBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.cms.tool.ToolManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class ToolAction extends BaseAction<ToolBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ToolManager cmsToolManager = IOC.get(ToolManager.class);

	public ToolBO getBO(HttpServletRequest request) {
		ToolBO cmsToolBO = getParameter(request);
		return cmsToolBO;
	}

	public static ToolBO getParameter(HttpServletRequest request) {
		ToolBO cmsToolBO = new ToolBO();
		cmsToolBO.setId(RequestUtil.getParam(request, "id", Long.class));
		cmsToolBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		cmsToolBO.setSidebar(RequestUtil.getParam(request, "sidebar"));
		cmsToolBO.setType(RequestUtil.getParam(request, "type"));
		cmsToolBO.setTitle(RequestUtil.getParam(request, "title"));
		cmsToolBO.setFeature(RequestUtil.getParam(request, "feature"));
		cmsToolBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		PagerUtil.initPage(request, cmsToolBO);
		return cmsToolBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return cmsToolManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ToolBO bo = getBO(request);
		bo.setId(cmsToolManager.getId());
		bo.setSaasId(SessionClient.getSaasId(request));
		cmsToolManager.insert(bo);
		request.setAttribute("result", true);
		request.setAttribute("msg", "小工具添加成功！");
	}

}
