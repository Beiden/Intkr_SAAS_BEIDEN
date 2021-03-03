package com.intkr.saas.manager.db;

import com.intkr.saas.domain.bo.db.DatasourceDatabaseBO;
import com.intkr.saas.domain.dbo.db.DatasourceDatabaseDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * 
 * @table datasource_database_tab
 * 
 * @author Beiden
 * @date 2020-04-02 20:51:57
 * @version 1.0
 */
public interface DatasourceDatabaseManager extends BaseManager<DatasourceDatabaseBO, DatasourceDatabaseDO> {

	public DatasourceDatabaseBO getDefault(Long databaseId);

}
