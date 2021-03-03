package com.intkr.saas.module.screen.admin.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ShopCommentBO;
import com.intkr.saas.manager.shop.ShopCommentManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午3:45:04
 * @version 1.0
 */
public class ShopCommentMgrReply {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopCommentManager commentManager = IOC.get("ShopCommentManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			ShopCommentBO commentBO = commentManager.get(id);
			request.setAttribute("dto", commentBO);
		} else {
			request.setAttribute("addId", commentManager.getId());
		}
	}

}
