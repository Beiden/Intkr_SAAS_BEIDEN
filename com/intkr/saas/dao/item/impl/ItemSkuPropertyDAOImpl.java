package com.intkr.saas.dao.item.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.ItemSkuPropertyDAO;
import com.intkr.saas.domain.dbo.item.ItemSkuPropertyDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:44:35
 * @version 1.0
 */
@Repository("ItemSkuPropertyDAO")
public class ItemSkuPropertyDAOImpl extends BaseDAOImpl<ItemSkuPropertyDO> implements ItemSkuPropertyDAO {

	public String getNameSpace() {
		return "itemSkuProperty";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
