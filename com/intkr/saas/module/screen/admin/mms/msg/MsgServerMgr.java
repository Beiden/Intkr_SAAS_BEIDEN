package com.intkr.saas.module.screen.admin.mms.msg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.mms.MsgServerBO;
import com.intkr.saas.manager.mms.MsgServerManager;
import com.intkr.saas.manager.mms.impl.MsgServerManagerImpl;
import com.intkr.saas.module.action.mms.MsgServerAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class MsgServerMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgServerManager manager = IOC.get(MsgServerManagerImpl.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MsgServerBO query = MsgServerAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
