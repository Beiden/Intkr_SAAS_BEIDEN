package com.intkr.saas.module.screen.admin.item.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.item.ItemSkuBO;
import com.intkr.saas.manager.item.ItemSkuManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ItemSkuInventoryUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSkuManager itemSkuManager = IOC.get("ItemSkuManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String itemSkuId = RequestUtil.getParam(request, "itemSkuId");
		ItemSkuBO itemSku = itemSkuManager.get(itemSkuId);
		request.setAttribute("itemSku", itemSku);
		request.setAttribute("addId", itemSkuManager.getId());
	}

}
