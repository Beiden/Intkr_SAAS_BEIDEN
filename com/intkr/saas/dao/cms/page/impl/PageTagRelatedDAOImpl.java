package com.intkr.saas.dao.cms.page.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.page.PageTagRelatedDAO;
import com.intkr.saas.domain.dbo.page.PageTagRelatedDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 17:15:34
 * @version 1.0
 */
@Repository("PageTagRelatedDAO")
public class PageTagRelatedDAOImpl extends BaseDAOImpl<PageTagRelatedDO> implements PageTagRelatedDAO {

	public String getNameSpace() {
		return "pageTagRelated";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
