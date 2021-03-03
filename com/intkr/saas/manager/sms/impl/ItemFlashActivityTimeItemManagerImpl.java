package com.intkr.saas.manager.sms.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemFlashActivityTimeItemDAO;
import com.intkr.saas.domain.bo.sms.ItemFlashActivityTimeItemBO;
import com.intkr.saas.domain.dbo.sms.ItemFlashActivityTimeItemDO;
import com.intkr.saas.manager.sms.ItemFlashActivityTimeItemManager;

/**
 * 秒杀活动
 * 
 * @table item_flash_activity_time_item
 * 
 * @author Beiden
 * @date 2020-11-11 20:30:46
 * @version 1.0
 */
@Repository("ItemFlashActivityTimeItemManager")
public class ItemFlashActivityTimeItemManagerImpl extends BaseManagerImpl<ItemFlashActivityTimeItemBO, ItemFlashActivityTimeItemDO> implements ItemFlashActivityTimeItemManager {

	@Resource
	private ItemFlashActivityTimeItemDAO itemFlashActivityTimeItemDAO;

	public BaseDAO<ItemFlashActivityTimeItemDO> getBaseDAO() {
		return itemFlashActivityTimeItemDAO;
	}

}
