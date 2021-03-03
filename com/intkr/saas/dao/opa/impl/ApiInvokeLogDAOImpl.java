package com.intkr.saas.dao.opa.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.opa.ApiInvokeLogDAO;
import com.intkr.saas.domain.dbo.opa.ApiInvokeLogDO;

/**
 * 
 * 
 * @table api_invoke_log_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
@Repository("opa.ApiInvokeLogDAO")
public class ApiInvokeLogDAOImpl extends BaseDAOImpl<ApiInvokeLogDO> implements ApiInvokeLogDAO {

	public String getNameSpace() {
		return "opa_apiInvokeLog";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
