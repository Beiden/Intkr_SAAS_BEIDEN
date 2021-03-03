package com.intkr.saas.module.screen.admin.shop.basic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.link.LinkBO;
import com.intkr.saas.manager.saas.LinkManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-10-3 下午2:22:28
 * @version 1.0
 */
public class FriendLinkMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LinkManager linkManager = IOC.get("LinkManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			LinkBO linkBO = linkManager.get(id);
			request.setAttribute("dto", linkBO);
		} else {
			request.setAttribute("addId", linkManager.getId());
		}
	}

}
