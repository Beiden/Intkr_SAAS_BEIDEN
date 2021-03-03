package com.intkr.saas.module.screen.admin.sms.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemBrandRecommendBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.sms.ItemBrandRecommendManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 品牌推荐
 * 
 * @table item_brand_recommend
 * 
 * @author Beiden
 * @date 2020-11-11 22:44:12
 * @version 1.0
 */
public class ItemBrandRecommendAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemBrandRecommendManager manager = IOC.get(ItemBrandRecommendManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String itemBrandRecommendId = RequestUtil.getParam(request, "itemBrandRecommendId");
		ItemBrandRecommendBO itemBrandRecommend = manager.get(itemBrandRecommendId);
		request.setAttribute("itemBrandRecommend", itemBrandRecommend);
		request.setAttribute("addId", manager.getId());
	}

}
