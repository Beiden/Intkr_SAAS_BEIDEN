package com.intkr.saas.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.user.RightDAO;
import com.intkr.saas.domain.dbo.user.RightDO;

/**
 * 
 * @author Beiden
 * @date 2011-11-16 下午4:27:08
 * @version 1.0
 */
@Repository("RightDAO")
public class RightDAOImpl extends BaseDAOImpl<RightDO> implements RightDAO {

	public String getNameSpace() {
		return "right";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
