package com.intkr.saas.dao.item.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.ItemSpuTemplateValueDAO;
import com.intkr.saas.domain.dbo.item.ItemSpuTemplateValueDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:44:58
 * @version 1.0
 */
@Repository("ItemSpuTemplateValueDAO")
public class ItemSpuTemplateValueDAOImpl extends BaseDAOImpl<ItemSpuTemplateValueDO> implements ItemSpuTemplateValueDAO {

	public String getNameSpace() {
		return "itemSpuTemplateValue";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
