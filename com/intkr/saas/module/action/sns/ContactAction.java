package com.intkr.saas.module.action.sns;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.sns.ContactManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.sns.ContactBO;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-7-8 上午10:22:24
 * @version 1.0
 */
public class ContactAction extends BaseAction<ContactBO> {

	private ContactManager contactManager = IOC.get(ContactManager.class);

	public ContactBO getBO(HttpServletRequest request) {
		return getParameter(request);
	}

	public static ContactBO getParameter(HttpServletRequest request) {
		ContactBO contactBO = new ContactBO();
		contactBO.setId(RequestUtil.getParam(request, "id", Long.class));
		contactBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		contactBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		contactBO.setContactId(RequestUtil.getParam(request, "contactId", Long.class));
		contactBO.setLastContactTime(DateUtil.parse(RequestUtil.getParam(request, "lastContactTime")));
		PagerUtil.initPage(request, contactBO);
		return contactBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return contactManager;
	}

}
