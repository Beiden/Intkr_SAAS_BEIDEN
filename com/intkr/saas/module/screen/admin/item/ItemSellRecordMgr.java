package com.intkr.saas.module.screen.admin.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ItemSellRecordBO;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.shop.ItemSellRecordManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.shop.ItemSellRecordAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-23 上午10:58:28
 * @version 1.0
 */
public class ItemSellRecordMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSellRecordManager itemSellRecordManager = IOC.get("ShopItemSellRecordManager");

	private UserManager userManager = IOC.get("UserManager");

	private ItemManager itemManager = IOC.get("ItemManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ItemSellRecordBO query = ItemSellRecordAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query = itemSellRecordManager.selectAndCount(query);
		query.setDatas(userManager.fill(query.getDatas()));
		query.setDatas(itemManager.fill(query.getDatas()));
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
