package com.intkr.saas.module.screen.admin.sms.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemFlashActivityBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.sms.ItemFlashActivityManager;
import com.intkr.saas.util.RequestUtil;
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
public class ItemFlashActivityAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFlashActivityManager manager = IOC.get(ItemFlashActivityManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String itemFlashActivityId = RequestUtil.getParam(request, "itemFlashActivityId");
		ItemFlashActivityBO itemFlashActivity = manager.get(itemFlashActivityId);
		request.setAttribute("itemFlashActivity", itemFlashActivity);
		request.setAttribute("addId", manager.getId());
	}

}
