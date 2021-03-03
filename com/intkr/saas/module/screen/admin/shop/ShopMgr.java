package com.intkr.saas.module.screen.admin.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.domain.type.shop.ShopStatus;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.shop.ShopAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-10 下午8:43:58
 * @version 1.0
 */
public class ShopMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopManager shopManager = IOC.get("ShopManager");

	private UserManager userManager = IOC.get("UserManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ShopBO query = ShopAction.getParameter(request);
		query = shopManager.selectAndCount(query);
		List<ShopBO> list = query.getDatas();
		userManager.fill(list);
		request.setAttribute("query", query);
		request.setAttribute("list", list);
		request.setAttribute("ShopStatus", ShopStatus.Error);
	}

}
