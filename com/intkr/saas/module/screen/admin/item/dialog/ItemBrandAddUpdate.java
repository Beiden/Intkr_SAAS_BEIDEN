package com.intkr.saas.module.screen.admin.item.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.item.ItemBrandBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.item.ItemBrandManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 品牌
 * 
 * @table item_brand
 * 
 * @author Beiden
 * @date 2020-11-11 22:33:08
 * @version 1.0
 */
public class ItemBrandAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemBrandManager manager = IOC.get(ItemBrandManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String itemBrandId = RequestUtil.getParam(request, "itemBrandId");
		ItemBrandBO itemBrand = manager.get(itemBrandId);
		request.setAttribute("itemBrand", itemBrand);
		request.setAttribute("addId", manager.getId());
	}

}
