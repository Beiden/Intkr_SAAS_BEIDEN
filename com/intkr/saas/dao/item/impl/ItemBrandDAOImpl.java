package com.intkr.saas.dao.item.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.item.ItemBrandDAO;
import com.intkr.saas.domain.dbo.item.ItemBrandDO;

/**
 * 品牌
 * 
 * @table item_brand
 * 
 * @author Beiden
 * @date 2020-11-11 22:33:08
 * @version 1.0
 */
@Repository("ItemBrandDAO")
public class ItemBrandDAOImpl extends BaseDAOImpl<ItemBrandDO> implements ItemBrandDAO {

	public String getNameSpace() {
		return "itemBrand";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
