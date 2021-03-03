package com.intkr.saas.manager.db.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.db.DatasourceDatabaseDAO;
import com.intkr.saas.domain.bo.db.DatasourceDatabaseBO;
import com.intkr.saas.domain.dbo.db.DatasourceDatabaseDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.db.DatasourceDatabaseManager;

/**
 * 
 * 
 * @table datasource_database_tab
 * 
 * @author Beiden
 * @date 2020-04-02 20:51:57
 * @version 1.0
 */
@Repository("db.DatasourceDatabaseManager")
public class DatasourceDatabaseManagerImpl extends BaseManagerImpl<DatasourceDatabaseBO, DatasourceDatabaseDO> implements DatasourceDatabaseManager {

	@Resource
	private DatasourceDatabaseDAO datasourceDatabaseDAO;

	public BaseDAO<DatasourceDatabaseDO> getBaseDAO() {
		return datasourceDatabaseDAO;
	}

	public DatasourceDatabaseBO getDefault(Long databaseId) {
		if (databaseId == null || databaseId <= 0) {
			return null;
		}
		DatasourceDatabaseBO query = new DatasourceDatabaseBO();
		query.setDatabaseId(databaseId);
		return selectOne(query);
	}

}
