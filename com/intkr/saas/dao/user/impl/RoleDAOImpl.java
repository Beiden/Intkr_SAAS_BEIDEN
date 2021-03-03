package com.intkr.saas.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.user.RoleDAO;
import com.intkr.saas.domain.dbo.user.RoleDO;

/**
 * 
 * @author Beiden
 * @date 2011-11-16 下午4:27:08
 * @version 1.0
 */
@Repository("RoleDAO")
public class RoleDAOImpl extends BaseDAOImpl<RoleDO> implements RoleDAO {

	public String getNameSpace() {
		return "role";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
