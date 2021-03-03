package com.intkr.saas.module.screen.admin.item.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.item.ItemSpuBO;
import com.intkr.saas.manager.item.ItemSpuManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ItemSpuAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSpuManager itemSpuManager = IOC.get("ItemSpuManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String shopSpuId = RequestUtil.getParam(request, "shopSpuId");
		ItemSpuBO shopSpu = itemSpuManager.get(shopSpuId);
		request.setAttribute("shopSpu", shopSpu);
		request.setAttribute("addId", itemSpuManager.getId());
	}

}
