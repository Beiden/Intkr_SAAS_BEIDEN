package com.intkr.saas.module.screen.admin.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.item.ItemTagAction;
import com.intkr.saas.domain.bo.item.ItemTagBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.item.ItemTagManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class ItemTagMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemTagManager manager = IOC.get(ItemTagManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ItemTagBO query = ItemTagAction.getParameter(request);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
