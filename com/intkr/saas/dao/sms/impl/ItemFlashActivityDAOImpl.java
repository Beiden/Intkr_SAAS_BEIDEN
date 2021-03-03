package com.intkr.saas.dao.sms.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemFlashActivityDAO;
import com.intkr.saas.domain.dbo.sms.ItemFlashActivityDO;

/**
 * 秒杀活动
 * 
 * @table item_flash_activity
 * 
 * @author Beiden
 * @date 2020-11-11 20:30:31
 * @version 1.0
 */
@Repository("ItemFlashActivityDAO")
public class ItemFlashActivityDAOImpl extends BaseDAOImpl<ItemFlashActivityDO> implements ItemFlashActivityDAO {

	public String getNameSpace() {
		return "itemFlashActivity";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
