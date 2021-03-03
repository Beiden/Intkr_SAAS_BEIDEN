package com.intkr.saas.dao.item.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.item.ItemTagRelatedDAO;
import com.intkr.saas.domain.dbo.item.ItemTagRelatedDO;

/**
 * 商品标签
 * 
 * @table item_tag_related
 * 
 * @author Beiden
 * @date 2020-11-02 09:54:14
 * @version 1.0
 */
@Repository("ItemTagRelatedDAO")
public class ItemTagRelatedDAOImpl extends BaseDAOImpl<ItemTagRelatedDO> implements ItemTagRelatedDAO {

	public String getNameSpace() {
		return "itemTagRelated";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
