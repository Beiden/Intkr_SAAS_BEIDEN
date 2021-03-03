package com.intkr.saas.module.screen.admin.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shopping.ItemBuyConsultBO;
import com.intkr.saas.domain.type.item.ItemBuyConsultType;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.shopping.ItemBuyConsultManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.shopping.ItemBuyConsultAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-23 上午10:39:29
 * @version 1.0
 */
public class ItemBuyConsultMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemBuyConsultManager itemBuyConsultManager = IOC.get("ItemBuyConsultManager");

	private UserManager userManager = IOC.get("UserManager");

	private ItemManager itemManager = IOC.get("ItemManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ItemBuyConsultBO query = ItemBuyConsultAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query = itemBuyConsultManager.selectAndCount(query);
		query.setDatas(userManager.fill(query.getDatas()));
		query.setDatas(itemManager.fill(query.getDatas()));
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("ItemBuyConsultType", ItemBuyConsultType.Error);
	}

}
