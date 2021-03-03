package com.intkr.saas.dao.saas.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.saas.SaasClientDAO;
import com.intkr.saas.domain.dbo.saas.SaasClientDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-19 22:00:49
 * @version 1.0
 */
@Repository("SaasClientDAO")
public class SaasClientDAOImpl extends BaseDAOImpl<SaasClientDO> implements SaasClientDAO {

	public String getNameSpace() {
		return "saasClient";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
