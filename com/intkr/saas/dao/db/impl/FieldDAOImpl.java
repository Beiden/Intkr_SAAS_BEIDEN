package com.intkr.saas.dao.db.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.db.FieldDAO;
import com.intkr.saas.domain.dbo.db.FieldDO;

/**
 * 
 * 
 * @table field_tab
 * 
 * @author Beiden
 * @date 2020-04-02 21:01:47
 * @version 1.0
 */
@Repository("db.FieldDAO")
public class FieldDAOImpl extends BaseDAOImpl<FieldDO> implements FieldDAO {

	public String getNameSpace() {
		return "db_field";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
