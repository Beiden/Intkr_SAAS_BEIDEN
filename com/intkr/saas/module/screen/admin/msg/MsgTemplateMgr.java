package com.intkr.saas.module.screen.admin.msg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.sns.MsgTemplateBO;
import com.intkr.saas.manager.sns.MsgTemplateManager;
import com.intkr.saas.module.action.sns.MsgTemplateAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 上午10:50:22
 * @version 1.0
 */
public class MsgTemplateMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgTemplateManager snsMsgTemplateManager = IOC.get(MsgTemplateManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MsgTemplateBO query = MsgTemplateAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		query = snsMsgTemplateManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
