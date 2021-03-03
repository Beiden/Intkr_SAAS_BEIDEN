package com.intkr.saas.manager.opa.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.opa.ApiMockDAO;
import com.intkr.saas.domain.bo.opa.ApiMockBO;
import com.intkr.saas.domain.dbo.opa.ApiMockDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.opa.ApiMockManager;

/**
 * 
 * 
 * @table api_mock_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
@Repository("opa.ApiMockManager")
public class ApiMockManagerImpl extends BaseManagerImpl<ApiMockBO, ApiMockDO> implements ApiMockManager {

	@Resource
	private ApiMockDAO apiMockDAO;

	public BaseDAO<ApiMockDO> getBaseDAO() {
		return apiMockDAO;
	}

}
