package com.intkr.saas.module.action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.shop.ItemSellRecordManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.RoleApplyManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-4-27 下午3:09:44
 * @version 1.0
 */
public class OrderTimeLineLogAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get("UserManager");

	private OrderManager orderManager = IOC.get("OrderManager");

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	private ItemManager itemManager = IOC.get("ItemManager");

	private ShopManager shopManager = IOC.get("ShopManager");

	private ItemSellRecordManager itemSellRecordManager = IOC.get("ShopItemSellRecordManager");

	private OptionManager optionManager = IOC.get("OptionManager");

	private RoleApplyManager roleApplyManager = IOC.get("RoleApplyManager");


	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {

	}

}
