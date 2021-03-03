package com.intkr.saas.module.screen.admin.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.sms.ItemBrandRecommendAction;
import com.intkr.saas.domain.bo.sms.ItemBrandRecommendBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.sms.ItemBrandRecommendManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class ItemBrandRecommendMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemBrandRecommendManager manager = IOC.get(ItemBrandRecommendManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ItemBrandRecommendBO query = ItemBrandRecommendAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
