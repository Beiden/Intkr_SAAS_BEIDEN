package com.intkr.saas.dao.sns.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sns.MsgDAO;
import com.intkr.saas.domain.dbo.sns.ContactDO;
import com.intkr.saas.domain.dbo.sns.MsgDO;
import com.intkr.saas.engine.db.MyBatisEngine;

/**
 * 
 * @author Beiden
 * @date 2011-7-7 上午9:04:53
 * @version 1.0
 */
@Repository("sns.MsgDAO")
public class MsgDAOImpl extends BaseDAOImpl<MsgDO> implements MsgDAO {

	public String getNameSpace() {
		return "msg";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

	public int readMessages(ContactDO contact) {
		int result;
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			result = session.update(getNameSpace() + ".readMsgs", contact);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

}
