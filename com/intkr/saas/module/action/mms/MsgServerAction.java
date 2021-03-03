package com.intkr.saas.module.action.mms;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.mms.MsgServerManager;
import com.intkr.saas.manager.mms.impl.MsgServerManagerImpl;
import com.intkr.saas.util.claz.IOC;
import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.mms.MsgServerBO;
import com.intkr.saas.util.PagerUtil;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class MsgServerAction extends BaseAction<MsgServerBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgServerManager mmsMsgServerManager = IOC.get(MsgServerManagerImpl.class);

	public MsgServerBO getBO(HttpServletRequest request) {
		MsgServerBO mmsMsgServerBO = getParameter(request);
		return mmsMsgServerBO;
	}

	public static MsgServerBO getParameter(HttpServletRequest request) {
		MsgServerBO mmsMsgServerBO = new MsgServerBO();
		mmsMsgServerBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mmsMsgServerBO.setName(RequestUtil.getParam(request, "name"));
		mmsMsgServerBO.setType(RequestUtil.getParam(request, "type"));
		mmsMsgServerBO.setStatus(RequestUtil.getParam(request, "status"));
		mmsMsgServerBO.setNote(RequestUtil.getParam(request, "note"));
		mmsMsgServerBO.setFeature(RequestUtil.getParam(request, "feature"));
		mmsMsgServerBO.setSort(RequestUtil.getParam(request, "sort" , Double.class));
		PagerUtil.initPage(request, mmsMsgServerBO);
		return mmsMsgServerBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return mmsMsgServerManager;
	}

}
