package com.intkr.saas.dao.saas.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.saas.SaasConfigDAO;
import com.intkr.saas.domain.dbo.saas.SaasConfigDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-24 19:17:55
 * @version 1.0
 */
@Repository("AppConfigDAO")
public class SaasConfigDAOImpl extends BaseDAOImpl<SaasConfigDO> implements SaasConfigDAO {

	public String getNameSpace() {
		return "saasConfig";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
