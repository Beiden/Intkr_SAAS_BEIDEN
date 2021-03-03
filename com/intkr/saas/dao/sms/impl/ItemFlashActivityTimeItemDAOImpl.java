package com.intkr.saas.dao.sms.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemFlashActivityTimeItemDAO;
import com.intkr.saas.domain.dbo.sms.ItemFlashActivityTimeItemDO;

/**
 * 秒杀活动
 * 
 * @table item_flash_activity_time_item
 * 
 * @author Beiden
 * @date 2020-11-11 20:30:46
 * @version 1.0
 */
@Repository("ItemFlashActivityTimeItemDAO")
public class ItemFlashActivityTimeItemDAOImpl extends BaseDAOImpl<ItemFlashActivityTimeItemDO> implements ItemFlashActivityTimeItemDAO {

	public String getNameSpace() {
		return "itemFlashActivityTimeItem";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
