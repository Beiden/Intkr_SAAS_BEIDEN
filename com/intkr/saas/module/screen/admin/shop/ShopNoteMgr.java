package com.intkr.saas.module.screen.admin.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.shop.ShopNoteAction;
import com.intkr.saas.domain.bo.shop.ShopNoteBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.shop.ShopNoteManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.util.claz.IOC;

/**
 * 店铺公告
 * 
 * @table shop_note
 * 
 * @author Beiden
 * @date 2020-10-30 18:05:17
 * @version 1.0
 */
public class ShopNoteMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopNoteManager manager = IOC.get(ShopNoteManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ShopNoteBO query = ShopNoteAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
