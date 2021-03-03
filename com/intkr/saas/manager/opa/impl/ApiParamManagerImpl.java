package com.intkr.saas.manager.opa.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.opa.ApiParamDAO;
import com.intkr.saas.domain.bo.opa.ApiBO;
import com.intkr.saas.domain.bo.opa.ApiParamBO;
import com.intkr.saas.domain.dbo.opa.ApiParamDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.opa.ApiParamManager;

/**
 * 
 * 
 * @table api_param_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
@Repository("opa.ApiParamManager")
public class ApiParamManagerImpl extends BaseManagerImpl<ApiParamBO, ApiParamDO> implements ApiParamManager {

	@Resource
	private ApiParamDAO apiParamDAO;

	public BaseDAO<ApiParamDO> getBaseDAO() {
		return apiParamDAO;
	}

	public ApiBO fill(ApiBO api) {
		if (api == null) {
			return null;
		}
		ApiParamBO query = new ApiParamBO();
		query.setApiId(api.getId());
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "asc");
		query.set_pageSize(1000);
		api.setParams(select(query));
		return api;
	}

	public List<ApiParamBO> selectByApiId(Long apiId) {
		if (apiId == null) {
			return new ArrayList<ApiParamBO>();
		}
		ApiParamBO query = new ApiParamBO();
		query.setApiId(apiId);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "asc");
		query.set_pageSize(1000);
		return select(query);
	}

}
