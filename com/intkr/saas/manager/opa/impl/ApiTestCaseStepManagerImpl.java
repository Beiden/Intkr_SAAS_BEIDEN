package com.intkr.saas.manager.opa.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.opa.ApiTestCaseStepDAO;
import com.intkr.saas.domain.bo.opa.ApiTestCaseStepBO;
import com.intkr.saas.domain.dbo.opa.ApiTestCaseStepDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.opa.ApiTestCaseStepManager;

/**
 * 
 * 
 * @table api_test_case_step_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
@Repository("opa.ApiTestCaseStepManager")
public class ApiTestCaseStepManagerImpl extends BaseManagerImpl<ApiTestCaseStepBO, ApiTestCaseStepDO> implements ApiTestCaseStepManager {

	@Resource
	private ApiTestCaseStepDAO apiTestCaseStepDAO;

	public BaseDAO<ApiTestCaseStepDO> getBaseDAO() {
		return apiTestCaseStepDAO;
	}

}
