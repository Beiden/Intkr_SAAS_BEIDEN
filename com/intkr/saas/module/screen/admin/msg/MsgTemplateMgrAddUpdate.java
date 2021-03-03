package com.intkr.saas.module.screen.admin.msg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sns.MsgTemplateBO;
import com.intkr.saas.manager.sns.MsgTemplateManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 上午10:50:35
 * @version 1.0
 */
public class MsgTemplateMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgTemplateManager snsMsgTemplateManager = IOC.get(MsgTemplateManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			MsgTemplateBO snsMsgTemplateBO = snsMsgTemplateManager.get(id);
			request.setAttribute("dto", snsMsgTemplateBO);
		} else {
			request.setAttribute("addId", snsMsgTemplateManager.getId());
		}
	}

}
