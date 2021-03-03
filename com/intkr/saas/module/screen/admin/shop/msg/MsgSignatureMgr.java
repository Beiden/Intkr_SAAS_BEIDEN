package com.intkr.saas.module.screen.admin.shop.msg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sns.MsgSignatureBO;
import com.intkr.saas.manager.sns.MsgSignatureManager;
import com.intkr.saas.module.action.sns.MsgSignatureAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 下午12:49:14
 * @version 1.0
 */
public class MsgSignatureMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgSignatureManager snsMsgSignatureManager = IOC.get(MsgSignatureManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MsgSignatureBO query = MsgSignatureAction.getParameter(request);
		query = snsMsgSignatureManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
