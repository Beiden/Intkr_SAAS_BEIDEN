package com.intkr.saas.dao.cms.page.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.page.PageCategoryRelatedDAO;
import com.intkr.saas.domain.dbo.page.PageCategoryRelatedDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 17:15:22
 * @version 1.0
 */
@Repository("PageCategoryRelatedDAO")
public class PageCategoryRelatedDAOImpl extends BaseDAOImpl<PageCategoryRelatedDO> implements PageCategoryRelatedDAO {

	public String getNameSpace() {
		return "pageCategoryRelated";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
