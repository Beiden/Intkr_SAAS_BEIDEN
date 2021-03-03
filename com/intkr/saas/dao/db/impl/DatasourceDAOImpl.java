package com.intkr.saas.dao.db.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.db.DatasourceDAO;
import com.intkr.saas.domain.dbo.db.DatasourceDO;

/**
 * 
 * 
 * @table datasource_tab
 * 
 * @author Beiden
 * @date 2020-04-02 18:59:02
 * @version 1.0
 */
@Repository("db.DatasourceDAO")
public class DatasourceDAOImpl extends BaseDAOImpl<DatasourceDO> implements DatasourceDAO {

	public String getNameSpace() {
		return "db_datasource";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
