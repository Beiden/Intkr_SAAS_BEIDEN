package com.intkr.saas.module.screen.admin.system.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.shop.ShopAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-21 上午9:19:33
 * @version 1.0
 */
public class ShopSelect {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopManager shopManager = IOC.get("ShopManager");

	private UserManager userManager = IOC.get("UserManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ShopBO query = ShopAction.getParameter(request);
		query = shopManager.selectAndCount(query);
		query.setDatas(userManager.fill(query.getDatas()));
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}