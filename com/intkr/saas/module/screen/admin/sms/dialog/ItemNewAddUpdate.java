package com.intkr.saas.module.screen.admin.sms.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemNewBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.sms.ItemNewManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 新品推荐
 * 
 * @table item_new
 * 
 * @author Beiden
 * @date 2020-11-11 23:11:23
 * @version 1.0
 */
public class ItemNewAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemNewManager manager = IOC.get(ItemNewManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String itemNewId = RequestUtil.getParam(request, "itemNewId");
		ItemNewBO itemNew = manager.get(itemNewId);
		request.setAttribute("itemNew", itemNew);
		request.setAttribute("addId", manager.getId());
	}

}
