package com.intkr.saas.module.action.mms;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.mms.MsgTemplateManager;
import com.intkr.saas.manager.mms.impl.MsgTemplateManagerImpl;
import com.intkr.saas.util.claz.IOC;
import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.mms.MsgTemplateBO;
import com.intkr.saas.util.PagerUtil;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class MsgTemplateAction extends BaseAction<MsgTemplateBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgTemplateManager mmsMsgTemplateManager = IOC.get(MsgTemplateManagerImpl.class);

	public MsgTemplateBO getBO(HttpServletRequest request) {
		MsgTemplateBO mmsMsgTemplateBO = getParameter(request);
		return mmsMsgTemplateBO;
	}

	public static MsgTemplateBO getParameter(HttpServletRequest request) {
		MsgTemplateBO mmsMsgTemplateBO = new MsgTemplateBO();
		mmsMsgTemplateBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mmsMsgTemplateBO.setName(RequestUtil.getParam(request, "name"));
		mmsMsgTemplateBO.setType(RequestUtil.getParam(request, "type"));
		mmsMsgTemplateBO.setStatus(RequestUtil.getParam(request, "status"));
		mmsMsgTemplateBO.setNote(RequestUtil.getParam(request, "note"));
		mmsMsgTemplateBO.setFeature(RequestUtil.getParam(request, "feature"));
		mmsMsgTemplateBO.setSort(RequestUtil.getParam(request, "sort" , Double.class));
		PagerUtil.initPage(request, mmsMsgTemplateBO);
		return mmsMsgTemplateBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return mmsMsgTemplateManager;
	}

}
