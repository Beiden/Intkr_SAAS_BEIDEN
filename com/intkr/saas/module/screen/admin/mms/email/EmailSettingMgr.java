package com.intkr.saas.module.screen.admin.mms.email;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.mms.EmailSettingBO;
import com.intkr.saas.manager.mms.EmailSettingManager;
import com.intkr.saas.module.action.mms.EmailSettingAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class EmailSettingMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmailSettingManager manager = IOC.get(EmailSettingManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		EmailSettingBO query = EmailSettingAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
