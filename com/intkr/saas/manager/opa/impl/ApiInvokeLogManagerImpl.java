package com.intkr.saas.manager.opa.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.opa.ApiInvokeLogDAO;
import com.intkr.saas.domain.bo.opa.ApiInvokeLogBO;
import com.intkr.saas.domain.dbo.opa.ApiInvokeLogDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.opa.ApiInvokeLogManager;

/**
 * 
 * 
 * @table api_invoke_log_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
@Repository("opa.ApiInvokeLogManager")
public class ApiInvokeLogManagerImpl extends BaseManagerImpl<ApiInvokeLogBO, ApiInvokeLogDO> implements ApiInvokeLogManager {

	@Resource
	private ApiInvokeLogDAO apiInvokeLogDAO;

	public BaseDAO<ApiInvokeLogDO> getBaseDAO() {
		return apiInvokeLogDAO;
	}

}
