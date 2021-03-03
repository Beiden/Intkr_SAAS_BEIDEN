package com.intkr.saas.manager.sms.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemFlashActivityTimeDAO;
import com.intkr.saas.domain.bo.sms.ItemFlashActivityTimeBO;
import com.intkr.saas.domain.dbo.sms.ItemFlashActivityTimeDO;
import com.intkr.saas.manager.sms.ItemFlashActivityTimeManager;

/**
 * 秒杀活动
 * 
 * @table item_flash_activity_time
 * 
 * @author Beiden
 * @date 2020-11-11 20:30:39
 * @version 1.0
 */
@Repository("ItemFlashActivityTimeManager")
public class ItemFlashActivityTimeManagerImpl extends BaseManagerImpl<ItemFlashActivityTimeBO, ItemFlashActivityTimeDO> implements ItemFlashActivityTimeManager {

	@Resource
	private ItemFlashActivityTimeDAO itemFlashActivityTimeDAO;

	public BaseDAO<ItemFlashActivityTimeDO> getBaseDAO() {
		return itemFlashActivityTimeDAO;
	}

}
