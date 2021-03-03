package com.intkr.saas.dao.item.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.item.ItemTagDAO;
import com.intkr.saas.domain.dbo.item.ItemTagDO;

/**
 * 商品标签
 * 
 * @table item_tag
 * 
 * @author Beiden
 * @date 2020-11-02 09:54:32
 * @version 1.0
 */
@Repository("ItemTagDAO")
public class ItemTagDAOImpl extends BaseDAOImpl<ItemTagDO> implements ItemTagDAO {

	public String getNameSpace() {
		return "itemTag";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
