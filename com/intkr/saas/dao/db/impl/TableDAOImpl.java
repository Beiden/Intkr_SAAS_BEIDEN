package com.intkr.saas.dao.db.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.db.TableDAO;
import com.intkr.saas.domain.dbo.db.TableDO;
import com.intkr.saas.engine.db.MyBatisEngine;

/**
 * 
 * 
 * @table table_tab
 * 
 * @author Beiden
 * @date 2020-04-02 20:59:46
 * @version 1.0
 */
@Repository("db.TableDAO")
public class TableDAOImpl extends BaseDAOImpl<TableDO> implements TableDAO {

	public String getNameSpace() {
		return "db_table";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

	public int updateIndexDesc(Long id, String indexDesc) {
		if (id == null || indexDesc == null) {
			return 0;
		}
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("id", id);
		map.put("indexDesc", indexDesc);
		int result;
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			result = session.update(getNameSpace() + ".updateIndexDesc", map);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

}
