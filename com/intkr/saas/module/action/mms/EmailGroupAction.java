package com.intkr.saas.module.action.mms;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.mms.EmailGroupBO;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.mms.EmailGroupManager;
import com.intkr.saas.manager.mms.EmailSettingManager;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class EmailGroupAction extends BaseAction<EmailGroupBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmailGroupManager mmsEmailGroupManager = IOC.get(EmailGroupManager.class);

	public EmailGroupBO getBO(HttpServletRequest request) {
		EmailGroupBO mmsEmailGroupBO = getParameter(request);
		return mmsEmailGroupBO;
	}

	public static EmailGroupBO getParameter(HttpServletRequest request) {
		EmailGroupBO mmsEmailGroupBO = new EmailGroupBO();
		mmsEmailGroupBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mmsEmailGroupBO.setName(RequestUtil.getParam(request, "name"));
		mmsEmailGroupBO.setType(RequestUtil.getParam(request, "type"));
		mmsEmailGroupBO.setStatus(RequestUtil.getParam(request, "status"));
		mmsEmailGroupBO.setNote(RequestUtil.getParam(request, "note"));
		mmsEmailGroupBO.setFeature(RequestUtil.getParam(request, "feature"));
		mmsEmailGroupBO.setSort(RequestUtil.getParam(request, "sort" , Double.class));
		PagerUtil.initPage(request, mmsEmailGroupBO);
		return mmsEmailGroupBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return mmsEmailGroupManager;
	}

}
