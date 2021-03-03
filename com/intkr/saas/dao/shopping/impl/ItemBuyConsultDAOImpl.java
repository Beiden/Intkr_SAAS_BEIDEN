package com.intkr.saas.dao.shopping.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.shopping.ItemBuyConsultDAO;
import com.intkr.saas.domain.dbo.shopping.ItemBuyConsultDO;

/**
 * 
 * @author Beiden
 * @date 2016-5-20 下午9:26:27
 * @version 1.0
 */
@Repository("ItemBuyConsultDAO")
public class ItemBuyConsultDAOImpl extends BaseDAOImpl<ItemBuyConsultDO> implements ItemBuyConsultDAO {

	public String getNameSpace() {
		return "itemBuyConsult";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
