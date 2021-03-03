package com.intkr.saas.dao.opa.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.opa.ApiExtDAO;
import com.intkr.saas.domain.dbo.opa.ApiExtDO;

/**
 * 
 * 
 * @table api_ext_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
@Repository("opa.ApiExtDAO")
public class ApiExtDAOImpl extends BaseDAOImpl<ApiExtDO> implements ApiExtDAO {

	public String getNameSpace() {
		return "opa_apiExt";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
