package com.intkr.saas.dao.opa.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.opa.ApiParamDAO;
import com.intkr.saas.domain.dbo.opa.ApiParamDO;

/**
 * 
 * 
 * @table api_param_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
@Repository("opa.ApiParamDAO")
public class ApiParamDAOImpl extends BaseDAOImpl<ApiParamDO> implements ApiParamDAO {

	public String getNameSpace() {
		return "opa_apiParam";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
