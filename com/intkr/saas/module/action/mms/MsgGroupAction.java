package com.intkr.saas.module.action.mms;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.mms.MsgGroupManager;
import com.intkr.saas.manager.mms.impl.MsgGroupManagerImpl;
import com.intkr.saas.util.claz.IOC;
import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.mms.MsgGroupBO;
import com.intkr.saas.util.PagerUtil;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class MsgGroupAction extends BaseAction<MsgGroupBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgGroupManager mmsMsgGroupManager = IOC.get(MsgGroupManagerImpl.class);

	public MsgGroupBO getBO(HttpServletRequest request) {
		MsgGroupBO mmsMsgGroupBO = getParameter(request);
		return mmsMsgGroupBO;
	}

	public static MsgGroupBO getParameter(HttpServletRequest request) {
		MsgGroupBO mmsMsgGroupBO = new MsgGroupBO();
		mmsMsgGroupBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mmsMsgGroupBO.setName(RequestUtil.getParam(request, "name"));
		mmsMsgGroupBO.setType(RequestUtil.getParam(request, "type"));
		mmsMsgGroupBO.setStatus(RequestUtil.getParam(request, "status"));
		mmsMsgGroupBO.setNote(RequestUtil.getParam(request, "note"));
		mmsMsgGroupBO.setFeature(RequestUtil.getParam(request, "feature"));
		mmsMsgGroupBO.setSort(RequestUtil.getParam(request, "sort" , Double.class));
		PagerUtil.initPage(request, mmsMsgGroupBO);
		return mmsMsgGroupBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return mmsMsgGroupManager;
	}

}
