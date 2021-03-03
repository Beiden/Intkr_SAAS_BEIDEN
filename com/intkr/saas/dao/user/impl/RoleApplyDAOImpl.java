package com.intkr.saas.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.user.RoleApplyDAO;
import com.intkr.saas.domain.dbo.user.RoleApplyDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 下午2:11:26
 * @version 1.0
 */
@Repository("RoleApplyDAO")
public class RoleApplyDAOImpl extends BaseDAOImpl<RoleApplyDO> implements RoleApplyDAO {

	public String getNameSpace() {
		return "roleApply";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
