package com.intkr.saas.module.action.sns;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.sns.MsgTemplateManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.sns.MsgTemplateBO;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 上午10:49:17
 * @version 1.0
 */
public class MsgTemplateAction extends BaseAction<MsgTemplateBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgTemplateManager snsMsgTemplateManager = IOC.get(MsgTemplateManager.class);

	public MsgTemplateBO getBO(HttpServletRequest request) {
		MsgTemplateBO snsMsgTemplateBO = getParameter(request);
		return snsMsgTemplateBO;
	}

	public static MsgTemplateBO getParameter(HttpServletRequest request) {
		MsgTemplateBO snsMsgTemplateBO = new MsgTemplateBO();
		snsMsgTemplateBO.setId(RequestUtil.getParam(request, "id", Long.class));
		snsMsgTemplateBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		snsMsgTemplateBO.setCode(RequestUtil.getParam(request, "code"));
		snsMsgTemplateBO.setName(RequestUtil.getParam(request, "name"));
		snsMsgTemplateBO.setContent(RequestUtil.getParam(request, "content"));
		snsMsgTemplateBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, snsMsgTemplateBO);
		return snsMsgTemplateBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return snsMsgTemplateManager;
	}

}
