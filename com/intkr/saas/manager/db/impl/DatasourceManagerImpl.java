package com.intkr.saas.manager.db.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.db.DatasourceDAO;
import com.intkr.saas.domain.bo.db.DatasourceBO;
import com.intkr.saas.domain.bo.db.DatasourceDatabaseBO;
import com.intkr.saas.domain.dbo.db.DatasourceDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.db.DatasourceManager;

/**
 * 
 * 
 * @table datasource_tab
 * 
 * @author Beiden
 * @date 2020-04-02 18:59:02
 * @version 1.0
 */
@Repository("db.DatasourceManager")
public class DatasourceManagerImpl extends BaseManagerImpl<DatasourceBO, DatasourceDO> implements DatasourceManager {

	@Resource
	private DatasourceDAO datasourceDAO;

	public BaseDAO<DatasourceDO> getBaseDAO() {
		return datasourceDAO;
	}

	public DatasourceDatabaseBO fill(DatasourceDatabaseBO datasourceDatabase) {
		if (datasourceDatabase == null) {
			return datasourceDatabase;
		}
		datasourceDatabase.setDatasource(get(datasourceDatabase.getDatasourceId()));
		return datasourceDatabase;
	}

}
