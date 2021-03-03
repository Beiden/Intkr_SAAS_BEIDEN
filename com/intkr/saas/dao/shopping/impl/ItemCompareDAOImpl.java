package com.intkr.saas.dao.shopping.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.shopping.ItemCompareDAO;
import com.intkr.saas.domain.dbo.shopping.ItemCompareDO;

/**
 * 
 * @author Beiden
 * @date 2017-09-02 15:36:08
 * @version 1.0
 */
@Repository("ShopCompareDAO")
public class ItemCompareDAOImpl extends BaseDAOImpl<ItemCompareDO> implements ItemCompareDAO {

	public String getNameSpace() {
		return "itemCompare";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
