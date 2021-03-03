package com.intkr.saas.dao.opa.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.opa.OaClientDAO;
import com.intkr.saas.domain.dbo.opa.OaClientDO;

/**
 * 客户端
 * 
 * @table oa_client
 * 
 * @author Beiden
 * @date 2020-11-04 20:18:06
 * @version 1.0
 */
@Repository("OaClientDAO")
public class OaClientDAOImpl extends BaseDAOImpl<OaClientDO> implements OaClientDAO {

	public String getNameSpace() {
		return "oaClient";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
