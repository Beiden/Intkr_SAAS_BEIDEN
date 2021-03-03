package com.intkr.saas.dao.opa.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.opa.ApiTestCaseStepDAO;
import com.intkr.saas.domain.dbo.opa.ApiTestCaseStepDO;

/**
 * 
 * 
 * @table api_test_case_step_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
@Repository("opa.ApiTestCaseStepDAO")
public class ApiTestCaseStepDAOImpl extends BaseDAOImpl<ApiTestCaseStepDO> implements ApiTestCaseStepDAO {

	public String getNameSpace() {
		return "opa_apiTestCaseStep";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
