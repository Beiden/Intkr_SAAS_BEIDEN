package com.intkr.saas.manager.db;

import java.util.List;

import com.intkr.saas.domain.bo.db.DatabaseBO;
import com.intkr.saas.domain.bo.db.DatasourceBO;
import com.intkr.saas.domain.bo.db.DatasourceDatabaseBO;
import com.intkr.saas.domain.bo.db.SqlResultBO;
import com.intkr.saas.domain.bo.db.TableBO;
import com.intkr.saas.domain.dbo.db.DatabaseDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * 
 * @table database_tab
 * 
 * @author Beiden
 * @date 2020-04-02 19:03:12
 * @version 1.0
 */
public interface DatabaseManager extends BaseManager<DatabaseBO, DatabaseDO> {

	public void syncTables(DatabaseBO database);

	public void syncTable(DatabaseBO database, String tableName);

	public void syncTable(Long datasourceId, Long databaseId, String tableName);

	public List<TableBO> getMenu(Long databaseId);

	public List<TableBO> getMenu(Long databaseId, String dbNameLike);

	public List<String> getTableNamesRt(Long databaseId);

	public SqlResultBO executeSql(DatasourceBO datasource, DatabaseBO database, String sql);

	public DatasourceBO getDefaultDatasource(Long databaseId);

	public List<DatasourceBO> selectDatasource(Long databaseId);

	public DatasourceDatabaseBO fill(DatasourceDatabaseBO datasourceDatabase);

}
