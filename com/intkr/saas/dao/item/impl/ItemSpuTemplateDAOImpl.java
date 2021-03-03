package com.intkr.saas.dao.item.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.ItemSpuTemplateDAO;
import com.intkr.saas.domain.dbo.item.ItemSpuTemplateDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:44:51
 * @version 1.0
 */
@Repository("ItemSpuTemplateDAO")
public class ItemSpuTemplateDAOImpl extends BaseDAOImpl<ItemSpuTemplateDO> implements ItemSpuTemplateDAO {

	public String getNameSpace() {
		return "itemSpuTemplate";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
