package com.intkr.saas.dao.sms.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemFlashActivityTimeDAO;
import com.intkr.saas.domain.dbo.sms.ItemFlashActivityTimeDO;

/**
 * 秒杀活动
 * 
 * @table item_flash_activity_time
 * 
 * @author Beiden
 * @date 2020-11-11 20:30:39
 * @version 1.0
 */
@Repository("ItemFlashActivityTimeDAO")
public class ItemFlashActivityTimeDAOImpl extends BaseDAOImpl<ItemFlashActivityTimeDO> implements ItemFlashActivityTimeDAO {

	public String getNameSpace() {
		return "itemFlashActivityTime";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
