package com.intkr.saas.module.screen.admin.item.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.item.ItemTagBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.item.ItemTagManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 商品标签
 * 
 * @table item_tag
 * 
 * @author Beiden
 * @date 2020-11-02 09:54:32
 * @version 1.0
 */
public class ItemTagAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemTagManager manager = IOC.get(ItemTagManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String itemTagId = RequestUtil.getParam(request, "itemTagId");
		ItemTagBO itemTag = manager.get(itemTagId);
		request.setAttribute("itemTag", itemTag);
		request.setAttribute("addId", manager.getId());
	}

}
