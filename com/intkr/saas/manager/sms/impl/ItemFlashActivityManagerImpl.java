package com.intkr.saas.manager.sms.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemFlashActivityDAO;
import com.intkr.saas.domain.bo.sms.ItemFlashActivityBO;
import com.intkr.saas.domain.dbo.sms.ItemFlashActivityDO;
import com.intkr.saas.manager.sms.ItemFlashActivityManager;

/**
 * 秒杀活动
 * 
 * @table item_flash_activity
 * 
 * @author Beiden
 * @date 2020-11-11 20:30:31
 * @version 1.0
 */
@Repository("ItemFlashActivityManager")
public class ItemFlashActivityManagerImpl extends BaseManagerImpl<ItemFlashActivityBO, ItemFlashActivityDO> implements ItemFlashActivityManager {

	@Resource
	private ItemFlashActivityDAO itemFlashActivityDAO;

	public BaseDAO<ItemFlashActivityDO> getBaseDAO() {
		return itemFlashActivityDAO;
	}

}
