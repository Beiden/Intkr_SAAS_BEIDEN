package com.intkr.saas.module.action.mms;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.mms.EmailSignatureBO;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.mms.EmailSignatureManager;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class EmailSignatureAction extends BaseAction<EmailSignatureBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmailSignatureManager mmsEmailSignatureManager = IOC.get(EmailSignatureManager.class);

	public EmailSignatureBO getBO(HttpServletRequest request) {
		EmailSignatureBO mmsEmailSignatureBO = getParameter(request);
		return mmsEmailSignatureBO;
	}

	public static EmailSignatureBO getParameter(HttpServletRequest request) {
		EmailSignatureBO mmsEmailSignatureBO = new EmailSignatureBO();
		mmsEmailSignatureBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mmsEmailSignatureBO.setName(RequestUtil.getParam(request, "name"));
		mmsEmailSignatureBO.setType(RequestUtil.getParam(request, "type"));
		mmsEmailSignatureBO.setStatus(RequestUtil.getParam(request, "status"));
		mmsEmailSignatureBO.setNote(RequestUtil.getParam(request, "note"));
		mmsEmailSignatureBO.setFeature(RequestUtil.getParam(request, "feature"));
		mmsEmailSignatureBO.setSort(RequestUtil.getParam(request, "sort" , Double.class));
		PagerUtil.initPage(request, mmsEmailSignatureBO);
		return mmsEmailSignatureBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return mmsEmailSignatureManager;
	}

}
