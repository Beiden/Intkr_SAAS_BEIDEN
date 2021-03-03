package com.intkr.saas.dao.cms.menu.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.menu.FrontMenuDetailDAO;
import com.intkr.saas.domain.dbo.menu.FrontMenuDetailDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2015-9-30 下午8:13:15
 * @version 1.0
 */
@Repository("FrontMenuDetailDAO")
public class FrontMenuDetailDAOImpl extends BaseDAOImpl<FrontMenuDetailDO> implements FrontMenuDetailDAO {

	public String getNameSpace() {
		return "frontMenuDetail";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
