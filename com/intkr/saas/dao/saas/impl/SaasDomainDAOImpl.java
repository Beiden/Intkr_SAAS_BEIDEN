package com.intkr.saas.dao.saas.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.saas.SaasDomainDAO;
import com.intkr.saas.domain.dbo.saas.SaasDomainDO;

/**
 * 
 * @author Beiden
 * @date 2017-07-20 14:55:37
 * @version 1.0
 */
@Repository("AppDomainDAO")
public class SaasDomainDAOImpl extends BaseDAOImpl<SaasDomainDO> implements SaasDomainDAO {

	public String getNameSpace() {
		return "saasDomain";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
