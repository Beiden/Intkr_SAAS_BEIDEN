package com.intkr.saas.dao.user.impl;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.user.UserRightDAO;
import com.intkr.saas.domain.dbo.user.UserRightDO;

import org.springframework.stereotype.Repository;


/**
 * 
 * @author Beiden
 * @date 2018-12-06 19:23:59
 * @version 1.0
 */
@Repository("UserRightDAO")
public class UserRightDAOImpl extends BaseDAOImpl<UserRightDO> implements UserRightDAO {

	public String getNameSpace() {
		return "userRight";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
