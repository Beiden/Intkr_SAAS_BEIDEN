package com.intkr.saas.manager.db;

import com.intkr.saas.domain.bo.db.DatasourceBO;
import com.intkr.saas.domain.bo.db.DatasourceDatabaseBO;
import com.intkr.saas.domain.dbo.db.DatasourceDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * 
 * @table datasource_tab
 * 
 * @author Beiden
 * @date 2020-04-02 18:59:02
 * @version 1.0
 */
public interface DatasourceManager extends BaseManager<DatasourceBO, DatasourceDO> {

	public DatasourceDatabaseBO fill(DatasourceDatabaseBO datasourceDatabase);

}
