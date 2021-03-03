package com.intkr.saas.module.action.mms;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.mms.EmailTemplateBO;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.mms.EmailTemplateManager;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class EmailTemplateAction extends BaseAction<EmailTemplateBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmailTemplateManager mmsEmailTemplateManager = IOC.get(EmailTemplateManager.class);

	public EmailTemplateBO getBO(HttpServletRequest request) {
		EmailTemplateBO mmsEmailTemplateBO = getParameter(request);
		return mmsEmailTemplateBO;
	}

	public static EmailTemplateBO getParameter(HttpServletRequest request) {
		EmailTemplateBO mmsEmailTemplateBO = new EmailTemplateBO();
		mmsEmailTemplateBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mmsEmailTemplateBO.setName(RequestUtil.getParam(request, "name"));
		mmsEmailTemplateBO.setType(RequestUtil.getParam(request, "type"));
		mmsEmailTemplateBO.setStatus(RequestUtil.getParam(request, "status"));
		mmsEmailTemplateBO.setNote(RequestUtil.getParam(request, "note"));
		mmsEmailTemplateBO.setFeature(RequestUtil.getParam(request, "feature"));
		mmsEmailTemplateBO.setSort(RequestUtil.getParam(request, "sort" , Double.class));
		PagerUtil.initPage(request, mmsEmailTemplateBO);
		return mmsEmailTemplateBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return mmsEmailTemplateManager;
	}

}
