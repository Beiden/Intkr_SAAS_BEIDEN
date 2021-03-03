package com.intkr.saas.dao.log.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.log.LogDAO;
import com.intkr.saas.domain.dbo.log.LogDO;
import com.intkr.saas.engine.db.MyBatisEngine;

/**
 * 
 * @author Beiden
 * @date 2015-5-22 下午9:08:47
 * @version 1.0
 */
@Repository("LogDAO")
public class LogDAOImpl extends BaseDAOImpl<LogDO> implements LogDAO {

	public String getNameSpace() {
		return "Log";
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
