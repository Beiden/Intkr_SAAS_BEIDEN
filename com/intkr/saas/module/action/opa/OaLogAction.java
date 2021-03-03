package com.intkr.saas.module.action.opa;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.manager.BaseManager;
import javax.servlet.http.HttpServletRequest;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.opa.OaLogBO;
import com.intkr.saas.manager.opa.OaLogManager;
import com.intkr.saas.util.PagerUtil;

/**
 * 接口日志
 * 
 * @table oa_log
 * 
 * @author Beiden
 * @date 2020-11-04 20:43:19
 * @version 1.0
 */
public class OaLogAction extends BaseAction<OaLogBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OaLogManager oaLogManager = IOC.get(OaLogManager.class);

	public OaLogBO getBO(HttpServletRequest request) {
		OaLogBO oaLogBO = getParameter(request);
		return oaLogBO;
	}

	public static OaLogBO getParameter(HttpServletRequest request) {
		OaLogBO oaLogBO = new OaLogBO();
		oaLogBO.setId(RequestUtil.getParam(request, "id", Long.class));
		oaLogBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		oaLogBO.setIp(RequestUtil.getParam(request, "ip", String.class));
		oaLogBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		oaLogBO.setReferer(RequestUtil.getParam(request, "referer", String.class));
		oaLogBO.setGoUrl(RequestUtil.getParam(request, "goUrl", String.class));
		oaLogBO.setQueryString(RequestUtil.getParam(request, "queryString", String.class));
		oaLogBO.setGoAction(RequestUtil.getParam(request, "goAction", String.class));
		oaLogBO.setGetUrl(RequestUtil.getParam(request, "getUrl", String.class));
		oaLogBO.setServerCostTime(RequestUtil.getParam(request, "serverCostTime", Long.class));
		oaLogBO.setClientCostTime(RequestUtil.getParam(request, "clientCostTime", Long.class));
		oaLogBO.setParams(RequestUtil.getParam(request, "params", String.class));
		oaLogBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, oaLogBO);
		return oaLogBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return oaLogManager;
	}

}
