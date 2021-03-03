package com.intkr.saas.dao.user.impl;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.user.UserRoleDAO;
import com.intkr.saas.domain.dbo.user.UserRoleDO;

import org.springframework.stereotype.Repository;


/**
 * 
 * @author Beiden
 * @date 2018-12-06 19:27:30
 * @version 1.0
 */
@Repository("UserRoleDAO")
public class UserRoleDAOImpl extends BaseDAOImpl<UserRoleDO> implements UserRoleDAO {

	public String getNameSpace() {
		return "userRole";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
