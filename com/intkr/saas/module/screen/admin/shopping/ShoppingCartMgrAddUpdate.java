package com.intkr.saas.module.screen.admin.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shopping.ShoppingCartBO;
import com.intkr.saas.manager.shopping.ShoppingCartManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-7-28 上午10:50:37
 * @version 1.0
 */
public class ShoppingCartMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShoppingCartManager favoriteManager = IOC.get("ShoppingCartManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			ShoppingCartBO favoriteBO = favoriteManager.get(id);
			request.setAttribute("dto", favoriteBO);
		} else {
			request.setAttribute("addId", favoriteManager.getId());
		}
	}

}
