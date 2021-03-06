package com.intkr.saas.module.screen.admin.item.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.item.ItemPropertyBO;
import com.intkr.saas.manager.item.ItemPropertyManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ItemPropertyAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemPropertyManager itemPropertyManager = IOC.get("ItemPropertyManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = RequestUtil.getParam(request, "itemPropertyId");
		ItemPropertyBO itemProperty = itemPropertyManager.get(id);
		request.setAttribute("itemProperty", itemProperty);
		request.setAttribute("addId", itemPropertyManager.getId());
	}

}
