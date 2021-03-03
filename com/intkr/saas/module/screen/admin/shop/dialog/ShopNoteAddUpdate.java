package com.intkr.saas.module.screen.admin.shop.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ShopNoteBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.shop.ShopNoteManager;
import com.intkr.saas.util.RequestUtil;
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
public class ShopNoteAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopNoteManager manager = IOC.get(ShopNoteManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String shopNoteId = RequestUtil.getParam(request, "shopNoteId");
		ShopNoteBO shopNote = manager.get(shopNoteId);
		request.setAttribute("shopNote", shopNote);
		request.setAttribute("addId", manager.getId());
	}

}
