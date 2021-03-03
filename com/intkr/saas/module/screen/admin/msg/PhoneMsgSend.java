package com.intkr.saas.module.screen.admin.msg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-24 上午10:42:14
 * @version 1.0
 */
public class PhoneMsgSend {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgManager msgManager = IOC.get(MsgManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			MsgBO msgBO = msgManager.get(id);
			request.setAttribute("dto", msgBO);
		} else {
			request.setAttribute("addId", msgManager.getId());
		}
	}

}
