package com.intkr.saas.module.action.sns;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.sns.MsgSignatureManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.sns.MsgSignatureBO;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 下午12:49:51
 * @version 1.0
 */
public class MsgSignatureAction extends BaseAction<MsgSignatureBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgSignatureManager snsMsgSignatureManager = IOC.get(MsgSignatureManager.class);

	public MsgSignatureBO getBO(HttpServletRequest request) {
		MsgSignatureBO snsMsgSignatureBO = getParameter(request);
		return snsMsgSignatureBO;
	}

	public static MsgSignatureBO getParameter(HttpServletRequest request) {
		MsgSignatureBO snsMsgSignatureBO = new MsgSignatureBO();
		snsMsgSignatureBO.setId(RequestUtil.getParam(request, "id", Long.class));
		snsMsgSignatureBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		snsMsgSignatureBO.setContent(RequestUtil.getParam(request, "content"));
		snsMsgSignatureBO.setIsDefault(RequestUtil.getParam(request, "isDefault", Integer.class));
		PagerUtil.initPage(request, snsMsgSignatureBO);
		return snsMsgSignatureBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return snsMsgSignatureManager;
	}

}
