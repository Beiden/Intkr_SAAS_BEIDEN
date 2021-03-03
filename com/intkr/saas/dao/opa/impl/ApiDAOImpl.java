package com.intkr.saas.dao.opa.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.opa.ApiDAO;
import com.intkr.saas.domain.dbo.opa.ApiDO;

/**
 * 
 * 
 * @table api_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
@Repository("opa.ApiDAO")
public class ApiDAOImpl extends BaseDAOImpl<ApiDO> implements ApiDAO {

	public String getNameSpace() {
		return "opa_api";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
