package com.intkr.saas.module.screen.admin.shopping.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.shopping.ItemCollectBO;
import com.intkr.saas.manager.shopping.ItemCollectManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ItemCollectAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemCollectManager manager = IOC.get("ItemCollectManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String shopItemCollectId = RequestUtil.getParam(request, "shopItemCollectId");
		ItemCollectBO shopItemCollect = manager.get(shopItemCollectId);
		request.setAttribute("shopItemCollect", shopItemCollect);
		request.setAttribute("addId", manager.getId());
	}

}
