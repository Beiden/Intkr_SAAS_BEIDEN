package com.intkr.saas.dao.log.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.log.SignLogDAO;
import com.intkr.saas.domain.dbo.log.SignLogDO;
import com.intkr.saas.engine.db.MyBatisEngine;

/**
 * 
 * @author Beiden
 * @date 2016-4-24 上午11:42:54
 * @version 1.0
 */
@Repository("SignLogDAO")
public class SignLogDAOImpl extends BaseDAOImpl<SignLogDO> implements SignLogDAO {

	public String getNameSpace() {
		return "signLog";
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
