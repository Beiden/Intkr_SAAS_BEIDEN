package com.intkr.saas.dao.opa.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.opa.ApiMockDAO;
import com.intkr.saas.domain.dbo.opa.ApiMockDO;

/**
 * 
 * 
 * @table api_mock_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
@Repository("opa.ApiMockDAO")
public class ApiMockDAOImpl extends BaseDAOImpl<ApiMockDO> implements ApiMockDAO {

	public String getNameSpace() {
		return "opa_apiMock";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
