package com.intkr.saas.manager.opa.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.opa.ApiExtDAO;
import com.intkr.saas.domain.bo.opa.ApiBO;
import com.intkr.saas.domain.bo.opa.ApiExtBO;
import com.intkr.saas.domain.dbo.opa.ApiExtDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.opa.ApiExtManager;

/**
 * 
 * 
 * @table api_ext_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
@Repository("opa.ApiExtManager")
public class ApiExtManagerImpl extends BaseManagerImpl<ApiExtBO, ApiExtDO> implements ApiExtManager {

	@Resource
	private ApiExtDAO apiExtDAO;

	public BaseDAO<ApiExtDO> getBaseDAO() {
		return apiExtDAO;
	}

	public ApiBO fill(ApiBO api) {
		if (api == null) {
			return null;
		}
		ApiExtBO query = new ApiExtBO();
		query.setApiId(api.getId());
		api.setApiExt(selectOne(query));
		return api;
	}

	public ApiExtBO getByApiId(Long apiId) {
		if (apiId == null) {
			return null;
		}
		ApiExtBO query = new ApiExtBO();
		query.setApiId(apiId);
		return selectOne(query);
	}

}
