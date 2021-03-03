package com.intkr.saas.manager.db.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.db.TableDAO;
import com.intkr.saas.domain.bo.db.TableBO;
import com.intkr.saas.domain.dbo.db.TableDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.db.FieldManager;
import com.intkr.saas.manager.db.TableManager;

/**
 * 
 * 
 * @table table_tab
 * 
 * @author Beiden
 * @date 2020-04-02 20:59:46
 * @version 1.0
 */
@Repository("db.TableManager")
public class TableManagerImpl extends BaseManagerImpl<TableBO, TableDO> implements TableManager {

	@Resource
	private TableDAO tableDAO;

	@Resource
	private FieldManager fieldManager;

	public BaseDAO<TableDO> getBaseDAO() {
		return tableDAO;
	}

	public TableBO get(Long databaseId, String tableName) {
		if (databaseId == null || databaseId <= 0) {
			return null;
		}
		if (tableName == null || "".equals(tableName)) {
			return null;
		}
		TableBO query = new TableBO();
		query.setDatabaseId(databaseId);
		query.setDbName(tableName);
		return selectOne(query);
	}

	public TableBO getFull(Long id) {
		if (id == null || id <= 0) {
			return null;
		}
		TableBO table = get(id);
		if (table == null) {
			return null;
		}
		fieldManager.fill(table);
		return table;
	}

	public void updateIndexDesc(Long id, String indexDesc) {
		tableDAO.updateIndexDesc(id, indexDesc);
	}

}
