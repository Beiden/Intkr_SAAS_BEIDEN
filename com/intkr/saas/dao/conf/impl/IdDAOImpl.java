package com.intkr.saas.dao.conf.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.conf.IdDAO;
import com.intkr.saas.domain.dbo.conf.IkIdDO;

/**
 * 
 * @author Beiden
 * @date 2016-07-31 18:04:42
 * @version 1.0
 */
@Repository("IkIdDAO")
public class IdDAOImpl extends BaseDAOImpl<IkIdDO> implements IdDAO {

	public String getNameSpace() {
		return "sysId";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
