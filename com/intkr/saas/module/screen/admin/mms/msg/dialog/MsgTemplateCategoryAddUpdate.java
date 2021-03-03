package com.intkr.saas.module.screen.admin.mms.msg.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.mms.MsgTemplateCategoryBO;
import com.intkr.saas.manager.mms.MsgTemplateCategoryManager;
import com.intkr.saas.manager.mms.impl.MsgTemplateCategoryManagerImpl;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class MsgTemplateCategoryAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgTemplateCategoryManager manager = IOC.get(MsgTemplateCategoryManagerImpl.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mmsMsgTemplateCategoryId = RequestUtil.getParam(request, "mmsMsgTemplateCategoryId");
		MsgTemplateCategoryBO mmsMsgTemplateCategory = manager.get(mmsMsgTemplateCategoryId);
		request.setAttribute("mmsMsgTemplateCategory", mmsMsgTemplateCategory);
		request.setAttribute("addId", manager.getId());
	}

}
