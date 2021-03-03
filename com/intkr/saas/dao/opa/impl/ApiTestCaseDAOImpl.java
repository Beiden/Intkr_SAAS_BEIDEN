package com.intkr.saas.dao.opa.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.opa.ApiTestCaseDAO;
import com.intkr.saas.domain.dbo.opa.ApiTestCaseDO;

/**
 * 
 * 
 * @table api_test_case_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
@Repository("opa.ApiTestCaseDAO")
public class ApiTestCaseDAOImpl extends BaseDAOImpl<ApiTestCaseDO> implements ApiTestCaseDAO {

	public String getNameSpace() {
		return "opa_apiTestCase";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
