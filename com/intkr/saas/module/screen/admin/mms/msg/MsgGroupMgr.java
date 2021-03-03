package com.intkr.saas.module.screen.admin.mms.msg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.mms.MsgGroupBO;
import com.intkr.saas.manager.mms.MsgGroupManager;
import com.intkr.saas.manager.mms.impl.MsgGroupManagerImpl;
import com.intkr.saas.module.action.mms.MsgGroupAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class MsgGroupMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgGroupManager manager = IOC.get(MsgGroupManagerImpl.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MsgGroupBO query = MsgGroupAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
