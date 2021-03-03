package com.intkr.saas.module.screen.admin.mms.msg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.mms.MsgWarningBO;
import com.intkr.saas.manager.mms.MsgWarningManager;
import com.intkr.saas.manager.mms.impl.MsgWarningManagerImpl;
import com.intkr.saas.module.action.mms.MsgWarningAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class MsgWarningMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgWarningManager manager = IOC.get(MsgWarningManagerImpl.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MsgWarningBO query = MsgWarningAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
