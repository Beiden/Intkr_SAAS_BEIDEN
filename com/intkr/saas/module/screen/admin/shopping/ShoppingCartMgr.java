package com.intkr.saas.module.screen.admin.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shopping.ShoppingCartBO;
import com.intkr.saas.manager.shopping.ShoppingCartManager;
import com.intkr.saas.module.action.shopping.ShoppingCartAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-7-28 上午10:50:15
 * @version 1.0
 */
public class ShoppingCartMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShoppingCartManager favoriteManager = IOC.get("ShoppingCartManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ShoppingCartBO query = ShoppingCartAction.getParameter(request);
		query = favoriteManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
