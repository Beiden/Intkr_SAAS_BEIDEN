package com.intkr.saas.module.screen.admin.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.item.ItemTagRelatedAction;
import com.intkr.saas.domain.bo.item.ItemTagRelatedBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.item.ItemTagRelatedManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.util.claz.IOC;

/**
 * 商品标签
 * 
 * @table item_tag_related
 * 
 * @author Beiden
 * @date 2020-11-02 09:54:15
 * @version 1.0
 */
public class ItemTagRelatedMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemTagRelatedManager manager = IOC.get(ItemTagRelatedManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ItemTagRelatedBO query = ItemTagRelatedAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
