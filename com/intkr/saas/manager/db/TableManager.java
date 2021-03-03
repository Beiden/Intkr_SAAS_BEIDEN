package com.intkr.saas.manager.db;

import com.intkr.saas.domain.bo.db.TableBO;
import com.intkr.saas.domain.dbo.db.TableDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * 
 * @table table_tab
 * 
 * @author Beiden
 * @date 2020-04-02 20:59:46
 * @version 1.0
 */
public interface TableManager extends BaseManager<TableBO, TableDO> {

	public TableBO get(Long databaseId, String tableName);

	public TableBO getFull(Long id);

	public void updateIndexDesc(Long id, String indexDesc);

}
