package com.intkr.saas.module.screen.admin.sms.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemRecommendBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.sms.ItemRecommendManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 商品推荐
 * 
 * @table item_recommend
 * 
 * @author Beiden
 * @date 2020-11-11 23:10:14
 * @version 1.0
 */
public class ItemRecommendAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemRecommendManager manager = IOC.get(ItemRecommendManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String itemRecommendId = RequestUtil.getParam(request, "itemRecommendId");
		ItemRecommendBO itemRecommend = manager.get(itemRecommendId);
		request.setAttribute("itemRecommend", itemRecommend);
		request.setAttribute("addId", manager.getId());
	}

}
