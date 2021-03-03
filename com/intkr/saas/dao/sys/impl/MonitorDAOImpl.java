package com.intkr.saas.dao.sys.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sys.MonitorDAO;
import com.intkr.saas.domain.dbo.sys.MonitorDO;
import com.intkr.saas.engine.db.MyBatisEngine;

/**
 * 
 * @author Administrator
 * 
 */
@Repository("MonitorDAO")
public class MonitorDAOImpl extends BaseDAOImpl<MonitorDO> implements MonitorDAO {

	public String getNameSpace() {
		return "monitor";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void deleteReal(Date maxGmtCreated) {
		int result;
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("maxGmtCreated", maxGmtCreated);
			result = session.delete(getNameSpace() + ".deleteReal", map);
			session.commit();
		} finally {
			session.close();
		}
	}

}
