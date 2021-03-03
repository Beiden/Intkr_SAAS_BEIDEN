package com.intkr.saas.module.screen.admin.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.sms.ItemRecommendAction;
import com.intkr.saas.domain.bo.sms.ItemRecommendBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.sms.ItemRecommendManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class ItemRecommendMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemRecommendManager manager = IOC.get(ItemRecommendManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ItemRecommendBO query = ItemRecommendAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
