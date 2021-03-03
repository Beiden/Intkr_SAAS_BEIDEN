package com.intkr.saas.dao.cms.menu.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.menu.FrontMenuDAO;
import com.intkr.saas.domain.dbo.menu.FrontMenuDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2015-9-23 下午11:59:08
 * @version 1.0
 */
@Repository("FrontMenuDAO")
public class FrontMenuDAOImpl extends BaseDAOImpl<FrontMenuDO> implements FrontMenuDAO {

	public String getNameSpace() {
		return "frontMenu";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
