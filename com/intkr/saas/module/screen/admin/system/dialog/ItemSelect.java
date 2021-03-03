package com.intkr.saas.module.screen.admin.system.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.item.ItemAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-10 上午8:36:21
 * @version 1.0
 */
public class ItemSelect {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemManager itemManager = IOC.get("ItemManager");
	
	private ImgManager imgManager = IOC.get("ImgManager");

	private UserManager userManager = IOC.get("UserManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ItemBO query = ItemAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		itemManager.selectAndCount(query);
		imgManager.fill(query.getDatas());
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}