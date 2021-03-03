package com.intkr.saas.module.screen.admin.shop.sns;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sns.ContactBO;
import com.intkr.saas.manager.sns.ContactManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.sns.ContactAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-7-8 上午10:24:40
 * @version 1.0
 */
public class ContactMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ContactManager contactManager = IOC.get(ContactManager.class);

	private UserManager userManager = IOC.get("UserManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ContactBO query = ContactAction.getParameter(request);
		query = contactManager.selectAndCount(query);
		query.setDatas(userManager.fill(query.getDatas()));
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
