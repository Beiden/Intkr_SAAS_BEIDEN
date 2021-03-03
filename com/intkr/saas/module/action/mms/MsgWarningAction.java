package com.intkr.saas.module.action.mms;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.mms.MsgWarningManager;
import com.intkr.saas.manager.mms.impl.MsgWarningManagerImpl;
import com.intkr.saas.util.claz.IOC;
import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.mms.MsgWarningBO;
import com.intkr.saas.util.PagerUtil;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class MsgWarningAction extends BaseAction<MsgWarningBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgWarningManager mmsMsgWarningManager = IOC.get(MsgWarningManagerImpl.class);

	public MsgWarningBO getBO(HttpServletRequest request) {
		MsgWarningBO mmsMsgWarningBO = getParameter(request);
		return mmsMsgWarningBO;
	}

	public static MsgWarningBO getParameter(HttpServletRequest request) {
		MsgWarningBO mmsMsgWarningBO = new MsgWarningBO();
		mmsMsgWarningBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mmsMsgWarningBO.setName(RequestUtil.getParam(request, "name"));
		mmsMsgWarningBO.setType(RequestUtil.getParam(request, "type"));
		mmsMsgWarningBO.setStatus(RequestUtil.getParam(request, "status"));
		mmsMsgWarningBO.setNote(RequestUtil.getParam(request, "note"));
		mmsMsgWarningBO.setFeature(RequestUtil.getParam(request, "feature"));
		mmsMsgWarningBO.setSort(RequestUtil.getParam(request, "sort" , Double.class));
		PagerUtil.initPage(request, mmsMsgWarningBO);
		return mmsMsgWarningBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return mmsMsgWarningManager;
	}

}
