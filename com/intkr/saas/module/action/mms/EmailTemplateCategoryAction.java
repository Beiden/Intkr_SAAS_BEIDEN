package com.intkr.saas.module.action.mms;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.mms.EmailTemplateCategoryBO;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.mms.EmailTemplateCategoryManager;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class EmailTemplateCategoryAction extends BaseAction<EmailTemplateCategoryBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmailTemplateCategoryManager mmsEmailTemplateCategoryManager = IOC.get(EmailTemplateCategoryManager.class);

	public EmailTemplateCategoryBO getBO(HttpServletRequest request) {
		EmailTemplateCategoryBO mmsEmailTemplateCategoryBO = getParameter(request);
		return mmsEmailTemplateCategoryBO;
	}

	public static EmailTemplateCategoryBO getParameter(HttpServletRequest request) {
		EmailTemplateCategoryBO mmsEmailTemplateCategoryBO = new EmailTemplateCategoryBO();
		mmsEmailTemplateCategoryBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mmsEmailTemplateCategoryBO.setName(RequestUtil.getParam(request, "name"));
		mmsEmailTemplateCategoryBO.setType(RequestUtil.getParam(request, "type"));
		mmsEmailTemplateCategoryBO.setStatus(RequestUtil.getParam(request, "status"));
		mmsEmailTemplateCategoryBO.setNote(RequestUtil.getParam(request, "note"));
		mmsEmailTemplateCategoryBO.setFeature(RequestUtil.getParam(request, "feature"));
		mmsEmailTemplateCategoryBO.setSort(RequestUtil.getParam(request, "sort" , Double.class));
		PagerUtil.initPage(request, mmsEmailTemplateCategoryBO);
		return mmsEmailTemplateCategoryBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return mmsEmailTemplateCategoryManager;
	}

}
