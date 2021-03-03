package com.intkr.saas.dao.shop.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.shop.ShopNoteDAO;
import com.intkr.saas.domain.dbo.shop.ShopNoteDO;

/**
 * 店铺公告
 * 
 * @table shop_note
 * 
 * @author Beiden
 * @date 2020-10-30 18:05:17
 * @version 1.0
 */
@Repository("ShopNoteDAO")
public class ShopNoteDAOImpl extends BaseDAOImpl<ShopNoteDO> implements ShopNoteDAO {

	public String getNameSpace() {
		return "shopNote";
	}
	
	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
