package com.intkr.saas.module.screen.admin.sms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemFlashActivityBO;
import com.intkr.saas.domain.bo.sms.ItemFlashActivityTimeBO;
import com.intkr.saas.manager.sms.ItemFlashActivityManager;
import com.intkr.saas.manager.sms.ItemFlashActivityTimeManager;
import com.intkr.saas.module.action.sms.ItemFlashActivityTimeAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 秒杀活动
 * 
 * @table item_flash_activity_time
 * 
 * @author Beiden
 * @date 2020-11-11 20:30:39
 * @version 1.0
 */
public class ItemFlashActivityTimeMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFlashActivityManager itemFlashActivityManager = IOC.get(ItemFlashActivityManager.class);

	private ItemFlashActivityTimeManager manager = IOC.get(ItemFlashActivityTimeManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Long activityId = RequestUtil.getParam(request, "activityId", Long.class);
		ItemFlashActivityBO activity = itemFlashActivityManager.get(activityId);
		request.setAttribute("activity", activity);
		ItemFlashActivityTimeBO query = ItemFlashActivityTimeAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
