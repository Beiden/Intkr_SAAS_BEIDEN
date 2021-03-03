package com.intkr.saas.module.action.mms;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.mms.MsgTemplateCategoryManager;
import com.intkr.saas.manager.mms.impl.MsgTemplateCategoryManagerImpl;
import com.intkr.saas.util.claz.IOC;
import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.mms.MsgTemplateCategoryBO;
import com.intkr.saas.util.PagerUtil;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class MsgTemplateCategoryAction extends BaseAction<MsgTemplateCategoryBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgTemplateCategoryManager mmsMsgTemplateCategoryManager = IOC.get(MsgTemplateCategoryManagerImpl.class);

	public MsgTemplateCategoryBO getBO(HttpServletRequest request) {
		MsgTemplateCategoryBO mmsMsgTemplateCategoryBO = getParameter(request);
		return mmsMsgTemplateCategoryBO;
	}

	public static MsgTemplateCategoryBO getParameter(HttpServletRequest request) {
		MsgTemplateCategoryBO mmsMsgTemplateCategoryBO = new MsgTemplateCategoryBO();
		mmsMsgTemplateCategoryBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mmsMsgTemplateCategoryBO.setName(RequestUtil.getParam(request, "name"));
		mmsMsgTemplateCategoryBO.setType(RequestUtil.getParam(request, "type"));
		mmsMsgTemplateCategoryBO.setStatus(RequestUtil.getParam(request, "status"));
		mmsMsgTemplateCategoryBO.setNote(RequestUtil.getParam(request, "note"));
		mmsMsgTemplateCategoryBO.setFeature(RequestUtil.getParam(request, "feature"));
		mmsMsgTemplateCategoryBO.setSort(RequestUtil.getParam(request, "sort" , Double.class));
		PagerUtil.initPage(request, mmsMsgTemplateCategoryBO);
		return mmsMsgTemplateCategoryBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return mmsMsgTemplateCategoryManager;
	}

}
