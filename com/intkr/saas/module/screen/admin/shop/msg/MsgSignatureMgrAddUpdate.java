package com.intkr.saas.module.screen.admin.shop.msg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sns.MsgSignatureBO;
import com.intkr.saas.manager.sns.MsgSignatureManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 下午12:49:21
 * @version 1.0
 */
public class MsgSignatureMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgSignatureManager snsMsgSignatureManager = IOC.get(MsgSignatureManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			MsgSignatureBO snsMsgSignatureBO = snsMsgSignatureManager.get(id);
			request.setAttribute("dto", snsMsgSignatureBO);
		} else {
			request.setAttribute("addId", snsMsgSignatureManager.getId());
		}
	}

}
