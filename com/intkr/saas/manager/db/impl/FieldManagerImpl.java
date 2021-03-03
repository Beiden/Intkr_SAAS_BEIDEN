package com.intkr.saas.manager.db.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.db.FieldDAO;
import com.intkr.saas.domain.bo.db.FieldBO;
import com.intkr.saas.domain.bo.db.TableBO;
import com.intkr.saas.domain.dbo.db.FieldDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.db.FieldManager;

/**
 * 
 * 
 * @table field_tab
 * 
 * @author Beiden
 * @date 2020-04-02 21:01:47
 * @version 1.0
 */
@Repository("db.FieldManager")
public class FieldManagerImpl extends BaseManagerImpl<FieldBO, FieldDO> implements FieldManager {

	@Resource
	private FieldDAO fieldDAO;

	public BaseDAO<FieldDO> getBaseDAO() {
		return fieldDAO;
	}

	public List<FieldBO> selectByTableId(Long tableId) {
		if (tableId == null || tableId <= 0) {
			return new ArrayList<FieldBO>();
		}
		FieldBO query = new FieldBO();
		query.setTableId(tableId);
		query.set_pageSize(10000);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "asc");
		return select(query);
	}

	public TableBO fill(TableBO table) {
		if (table == null) {
			return table;
		}
		table.setFields(selectByTableId(table.getId()));
		return table;
	}

}
