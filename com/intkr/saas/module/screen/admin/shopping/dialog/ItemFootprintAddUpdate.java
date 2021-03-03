package com.intkr.saas.module.screen.admin.shopping.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.shopping.ItemFootprintBO;
import com.intkr.saas.manager.shopping.ItemFootprintManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ItemFootprintAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFootprintManager manager = IOC.get("ShopFootprintManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String shopFootprintId = RequestUtil.getParam(request, "shopFootprintId");
		ItemFootprintBO shopFootprint = manager.get(shopFootprintId);
		request.setAttribute("shopFootprint", shopFootprint);
		request.setAttribute("addId", manager.getId());
	}

}
