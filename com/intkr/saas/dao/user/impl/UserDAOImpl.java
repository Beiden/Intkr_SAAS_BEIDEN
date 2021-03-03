package com.intkr.saas.dao.user.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.user.UserDAO;
import com.intkr.saas.domain.dbo.user.UserDO;
import com.intkr.saas.engine.db.MyBatisEngine;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-24 下午03:34:12
 * @version 1.0
 */
@Repository("UserDAO")
public class UserDAOImpl extends BaseDAOImpl<UserDO> implements UserDAO {

	public String getNameSpace() {
		return "user";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void updateMoney(UserDO dto) {
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			session.insert(getNameSpace() + ".updateMoney", dto);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void updateMoney(Long userId, Long money) {
		UserDO dto = new UserDO();
		dto.setId(userId);
		dto.setMoney(money);
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			session.insert(getNameSpace() + ".updateMoney", dto);
			session.commit();
		} finally {
			session.close();
		}
	}

}
