package com.intkr.saas.dao.cms.page.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.page.PageCategoryDAO;
import com.intkr.saas.domain.dbo.page.PageCategoryDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 17:15:12
 * @version 1.0
 */
@Repository("PageCategoryDAO")
public class PageCategoryDAOImpl extends BaseDAOImpl<PageCategoryDO> implements PageCategoryDAO {

	public String getNameSpace() {
		return "pageCategory";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
