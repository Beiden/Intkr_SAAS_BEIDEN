package com.intkr.saas.module.screen.admin.sms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemFlashActivityBO;
import com.intkr.saas.domain.bo.sms.ItemFlashActivityTimeBO;
import com.intkr.saas.domain.bo.sms.ItemFlashActivityTimeItemBO;
import com.intkr.saas.manager.sms.ItemFlashActivityManager;
import com.intkr.saas.manager.sms.ItemFlashActivityTimeItemManager;
import com.intkr.saas.manager.sms.ItemFlashActivityTimeManager;
import com.intkr.saas.module.action.sms.ItemFlashActivityTimeItemAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 秒杀活动
 * 
 * @table item_flash_activity_time_item
 * 
 * @author Beiden
 * @date 2020-11-11 20:30:46
 * @version 1.0
 */
public class ItemFlashActivityTimeItemMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFlashActivityManager itemFlashActivityManager = IOC.get(ItemFlashActivityManager.class);

	private ItemFlashActivityTimeManager itemFlashActivityTimeManager = IOC.get(ItemFlashActivityTimeManager.class);

	private ItemFlashActivityTimeItemManager manager = IOC.get(ItemFlashActivityTimeItemManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Long activityId = RequestUtil.getParam(request, "activityId", Long.class);
		ItemFlashActivityBO activity = itemFlashActivityManager.get(activityId);
		request.setAttribute("activity", activity);
		Long timeId = RequestUtil.getParam(request, "timeId", Long.class);
		ItemFlashActivityTimeBO activityTime = itemFlashActivityTimeManager.get(timeId);
		request.setAttribute("activityTime", activityTime);
		ItemFlashActivityTimeItemBO query = ItemFlashActivityTimeItemAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
