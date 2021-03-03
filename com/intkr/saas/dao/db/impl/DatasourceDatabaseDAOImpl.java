package com.intkr.saas.dao.db.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.db.DatasourceDatabaseDAO;
import com.intkr.saas.domain.dbo.db.DatasourceDatabaseDO;

/**
 * 
 * 
 * @table datasource_database_tab
 * 
 * @author Beiden
 * @date 2020-04-02 20:51:57
 * @version 1.0
 */
@Repository("db.DatasourceDatabaseDAO")
public class DatasourceDatabaseDAOImpl extends BaseDAOImpl<DatasourceDatabaseDO> implements DatasourceDatabaseDAO {

	public String getNameSpace() {
		return "db_datasourceDatabase";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
