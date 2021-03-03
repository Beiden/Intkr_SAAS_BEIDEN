package com.intkr.saas.dao.user.impl;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.user.RoleRightDAO;
import com.intkr.saas.domain.dbo.user.RoleRightDO;

import org.springframework.stereotype.Repository;


/**
 * 
 * @author Beiden
 * @date 2018-12-06 19:28:05
 * @version 1.0
 */
@Repository("RoleRightDAO")
public class RoleRightDAOImpl extends BaseDAOImpl<RoleRightDO> implements RoleRightDAO {

	public String getNameSpace() {
		return "roleRight";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
