package com.intkr.saas.module.action.mms;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.mms.EmailSettingBO;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.manager.BaseManager;
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
public class EmailSettingAction extends BaseAction<EmailSettingBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmailSettingManager mmsEmailSettingManager = IOC.get(EmailSettingManager.class);

	public EmailSettingBO getBO(HttpServletRequest request) {
		EmailSettingBO mmsEmailSettingBO = getParameter(request);
		return mmsEmailSettingBO;
	}

	public static EmailSettingBO getParameter(HttpServletRequest request) {
		EmailSettingBO mmsEmailSettingBO = new EmailSettingBO();
		mmsEmailSettingBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mmsEmailSettingBO.setName(RequestUtil.getParam(request, "name"));
		mmsEmailSettingBO.setType(RequestUtil.getParam(request, "type"));
		mmsEmailSettingBO.setStatus(RequestUtil.getParam(request, "status"));
		mmsEmailSettingBO.setNote(RequestUtil.getParam(request, "note"));
		mmsEmailSettingBO.setFeature(RequestUtil.getParam(request, "feature"));
		mmsEmailSettingBO.setSort(RequestUtil.getParam(request, "sort" , Double.class));
		PagerUtil.initPage(request, mmsEmailSettingBO);
		return mmsEmailSettingBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return mmsEmailSettingManager;
	}

}
