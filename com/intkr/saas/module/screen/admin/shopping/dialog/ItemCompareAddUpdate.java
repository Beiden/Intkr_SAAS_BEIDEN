package com.intkr.saas.module.screen.admin.shopping.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.shopping.ItemCompareBO;
import com.intkr.saas.manager.shopping.ItemCompareManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ItemCompareAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemCompareManager manager = IOC.get("ShopCompareManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String shopCompareId = RequestUtil.getParam(request, "shopCompareId");
		ItemCompareBO shopCompare = manager.get(shopCompareId);
		request.setAttribute("shopCompare", shopCompare);
		request.setAttribute("addId", manager.getId());
	}

}
