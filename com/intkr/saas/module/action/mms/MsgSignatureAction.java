package com.intkr.saas.module.action.mms;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.mms.MsgSignatureManager;
import com.intkr.saas.manager.mms.impl.MsgSignatureManagerImpl;
import com.intkr.saas.util.claz.IOC;
import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.mms.MsgSignatureBO;
import com.intkr.saas.util.PagerUtil;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class MsgSignatureAction extends BaseAction<MsgSignatureBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgSignatureManager mmsMsgSignatureManager = IOC.get(MsgSignatureManagerImpl.class);

	public MsgSignatureBO getBO(HttpServletRequest request) {
		MsgSignatureBO mmsMsgSignatureBO = getParameter(request);
		return mmsMsgSignatureBO;
	}

	public static MsgSignatureBO getParameter(HttpServletRequest request) {
		MsgSignatureBO mmsMsgSignatureBO = new MsgSignatureBO();
		mmsMsgSignatureBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mmsMsgSignatureBO.setName(RequestUtil.getParam(request, "name"));
		mmsMsgSignatureBO.setType(RequestUtil.getParam(request, "type"));
		mmsMsgSignatureBO.setStatus(RequestUtil.getParam(request, "status"));
		mmsMsgSignatureBO.setNote(RequestUtil.getParam(request, "note"));
		mmsMsgSignatureBO.setFeature(RequestUtil.getParam(request, "feature"));
		mmsMsgSignatureBO.setSort(RequestUtil.getParam(request, "sort" , Double.class));
		PagerUtil.initPage(request, mmsMsgSignatureBO);
		return mmsMsgSignatureBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return mmsMsgSignatureManager;
	}

}
