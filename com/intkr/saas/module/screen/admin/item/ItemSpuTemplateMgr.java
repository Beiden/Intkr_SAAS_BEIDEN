package com.intkr.saas.module.screen.admin.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateBO;
import com.intkr.saas.manager.item.ItemSpuTemplateManager;
import com.intkr.saas.module.action.item.SpuTemplateAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class ItemSpuTemplateMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSpuTemplateManager itemSpuTemplateManager = IOC.get("ItemSpuTemplateManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ItemSpuTemplateBO query = SpuTemplateAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query = itemSpuTemplateManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
