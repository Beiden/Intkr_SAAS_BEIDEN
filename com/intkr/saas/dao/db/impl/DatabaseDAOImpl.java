package com.intkr.saas.dao.db.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.db.DatabaseDAO;
import com.intkr.saas.domain.dbo.db.DatabaseDO;

/**
 * 
 * 
 * @table database_tab
 * 
 * @author Beiden
 * @date 2020-04-02 19:03:12
 * @version 1.0
 */
@Repository("db.DatabaseDAO")
public class DatabaseDAOImpl extends BaseDAOImpl<DatabaseDO> implements DatabaseDAO {

	public String getNameSpace() {
		return "db_database";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
