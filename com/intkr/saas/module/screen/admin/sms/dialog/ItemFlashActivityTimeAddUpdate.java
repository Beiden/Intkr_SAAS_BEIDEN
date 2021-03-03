package com.intkr.saas.module.screen.admin.sms.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemFlashActivityTimeBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.sms.ItemFlashActivityTimeManager;
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
public class ItemFlashActivityTimeAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFlashActivityTimeManager manager = IOC.get(ItemFlashActivityTimeManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String itemFlashActivityTimeId = RequestUtil.getParam(request, "itemFlashActivityTimeId");
		ItemFlashActivityTimeBO itemFlashActivityTime = manager.get(itemFlashActivityTimeId);
		request.setAttribute("itemFlashActivityTime", itemFlashActivityTime);
		request.setAttribute("addId", manager.getId());
	}

}
