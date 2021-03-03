package com.intkr.saas.module.screen.admin.sms.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemFlashActivityTimeItemBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.sms.ItemFlashActivityTimeItemManager;
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
public class ItemFlashActivityTimeItemAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFlashActivityTimeItemManager manager = IOC.get(ItemFlashActivityTimeItemManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String itemFlashActivityTimeItemId = RequestUtil.getParam(request, "itemFlashActivityTimeItemId");
		ItemFlashActivityTimeItemBO itemFlashActivityTimeItem = manager.get(itemFlashActivityTimeItemId);
		request.setAttribute("itemFlashActivityTimeItem", itemFlashActivityTimeItem);
		request.setAttribute("addId", manager.getId());
	}

}
