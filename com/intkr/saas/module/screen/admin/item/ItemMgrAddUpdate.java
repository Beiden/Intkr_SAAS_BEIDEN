package com.intkr.saas.module.screen.admin.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.type.item.ItemStatus;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.item.ItemCategoryManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.item.ItemSkuManager;
import com.intkr.saas.manager.item.ItemSkuValueManager;
import com.intkr.saas.manager.item.ItemSpuManager;
import com.intkr.saas.manager.item.ItemSpuValueManager;
import com.intkr.saas.manager.item.ItemTagManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午10:26:36
 * @version 1.0
 */
public class ItemMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemManager itemManager = IOC.get("ItemManager");

	private ImgManager imgManager = IOC.get("ImgManager");

	private ItemCategoryManager itemCategoryManager = IOC.get("ItemCategoryManager");

	private ItemTagManager itemTagManager = IOC.get("ItemTagManager");

	private ShopManager shopManager = IOC.get("ShopManager");

	private ItemSpuManager itemSpuManager = IOC.get("ItemSpuManager");

	private ItemSpuValueManager itemSpuValueManager = IOC.get("ItemSpuValueManager");

	private ItemSkuManager itemSkuManager = IOC.get("ItemSkuManager");

	private ItemSkuManager itemSkuPropertyManager = IOC.get("ItemSkuManager");

	private ItemSkuValueManager itemSkuValueManager = IOC.get("ItemSkuValueManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			ItemBO itemBO = itemManager.get(id);
			itemCategoryManager.fill(itemBO);
			itemTagManager.fills(itemBO);
			imgManager.fills(itemBO);
			shopManager.fill(itemBO);
			itemSpuManager.fill(itemBO);
			itemSpuValueManager.fill(itemBO.getSpus());
			itemSkuManager.fill(itemBO);
			itemSkuValueManager.fill(itemBO.getSkus());
			itemSkuPropertyManager.fillFull(itemBO);
			request.setAttribute("dto", itemBO);
		} else {
			request.setAttribute("addId", itemManager.getId());
			request.setAttribute("shop", shopManager.getByUserId(SessionClient.getLoginUserId(request)));
		}
		request.setAttribute("ItemStatus", ItemStatus.Error);
	}

}
