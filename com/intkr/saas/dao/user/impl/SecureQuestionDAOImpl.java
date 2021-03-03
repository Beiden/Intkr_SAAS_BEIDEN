package com.intkr.saas.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.user.SecureQuestionDAO;
import com.intkr.saas.domain.dbo.user.SecureQuestionDO;

/**
 * 
 * @author Beiden
 * @date 2016-5-28 下午10:52:25
 * @version 1.0
 */
@Repository("SecureQuestionDAO")
public class SecureQuestionDAOImpl extends BaseDAOImpl<SecureQuestionDO> implements SecureQuestionDAO {

	public String getNameSpace() {
		return "secureQuestion";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
