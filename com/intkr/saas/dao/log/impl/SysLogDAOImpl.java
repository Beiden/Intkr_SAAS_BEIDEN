package com.intkr.saas.dao.log.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.log.SysLogDAO;
import com.intkr.saas.domain.dbo.log.SysLogDO;
import com.intkr.saas.engine.db.MyBatisEngine;

/**
 * 
 * @author Beiden
 * @date 2011-5-24 上午10:17:25
 * @version 1.0
 */
@Repository("SysLogDAO")
public class SysLogDAOImpl extends BaseDAOImpl<SysLogDO> implements SysLogDAO {

	public String getNameSpace() {
		return "sysLog";
	}

	public Long count(Map<String, Object> map) {
		return countByMap(map);
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
