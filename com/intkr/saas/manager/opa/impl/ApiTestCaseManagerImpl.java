package com.intkr.saas.manager.opa.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.opa.ApiTestCaseDAO;
import com.intkr.saas.domain.bo.opa.ApiTestCaseBO;
import com.intkr.saas.domain.dbo.opa.ApiTestCaseDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.opa.ApiTestCaseManager;

/**
 * 
 * 
 * @table api_test_case_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
@Repository("opa.ApiTestCaseManager")
public class ApiTestCaseManagerImpl extends BaseManagerImpl<ApiTestCaseBO, ApiTestCaseDO> implements ApiTestCaseManager {

	@Resource
	private ApiTestCaseDAO apiTestCaseDAO;

	public BaseDAO<ApiTestCaseDO> getBaseDAO() {
		return apiTestCaseDAO;
	}

}
