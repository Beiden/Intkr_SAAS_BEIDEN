package com.intkr.saas.dao.cms.page.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.page.PageDAO;
import com.intkr.saas.domain.dbo.page.PageDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:00:20
 * @version 1.0
 */
@Repository("PageDAO")
public class PageDAOImpl extends BaseDAOImpl<PageDO> implements PageDAO {

	public String getNameSpace() {
		return "page";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
