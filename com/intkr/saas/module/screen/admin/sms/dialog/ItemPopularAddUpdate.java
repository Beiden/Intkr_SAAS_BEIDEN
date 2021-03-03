package com.intkr.saas.module.screen.admin.sms.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemPopularBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.sms.ItemPopularManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 人气商品
 * 
 * @table item_popular
 * 
 * @author Beiden
 * @date 2020-11-11 23:10:57
 * @version 1.0
 */
public class ItemPopularAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemPopularManager manager = IOC.get(ItemPopularManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String itemPopularId = RequestUtil.getParam(request, "itemPopularId");
		ItemPopularBO itemPopular = manager.get(itemPopularId);
		request.setAttribute("itemPopular", itemPopular);
		request.setAttribute("addId", manager.getId());
	}

}
