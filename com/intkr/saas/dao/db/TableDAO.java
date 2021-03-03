package com.intkr.saas.dao.db;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.domain.dbo.db.TableDO;

/**
 * 
 * 
 * @table table_tab
 * 
 * @author Beiden
 * @date 2020-04-02 20:59:46
 * @version 1.0
 */
public interface TableDAO extends BaseDAO<TableDO> {

	public int updateIndexDesc(Long id, String indexDesc);

}
