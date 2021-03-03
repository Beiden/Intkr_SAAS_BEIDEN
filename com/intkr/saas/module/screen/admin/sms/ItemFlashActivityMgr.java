package com.intkr.saas.module.screen.admin.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.sms.ItemFlashActivityAction;
import com.intkr.saas.domain.bo.sms.ItemFlashActivityBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.sms.ItemFlashActivityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.util.claz.IOC;

/**
 * 秒杀活动
 * 
 * @table item_flash_activity
 * 
 * @author Beiden
 * @date 2020-11-11 20:30:31
 * @version 1.0
 */
public class ItemFlashActivityMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFlashActivityManager manager = IOC.get(ItemFlashActivityManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ItemFlashActivityBO query = ItemFlashActivityAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
