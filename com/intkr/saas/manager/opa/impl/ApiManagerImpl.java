package com.intkr.saas.manager.opa.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.opa.ApiDAO;
import com.intkr.saas.domain.bo.opa.ApiBO;
import com.intkr.saas.domain.dbo.opa.ApiDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.opa.ApiExtManager;
import com.intkr.saas.manager.opa.ApiManager;
import com.intkr.saas.manager.opa.ApiParamManager;

/**
 * 
 * 
 * @table api_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
@Repository("opa.ApiManager")
public class ApiManagerImpl extends BaseManagerImpl<ApiBO, ApiDO> implements ApiManager {

	@Resource
	private ApiDAO apiDAO;

	@Resource
	private ApiParamManager apiParamManager;

	@Resource
	private ApiExtManager apiExtManager;

	public BaseDAO<ApiDO> getBaseDAO() {
		return apiDAO;
	}

	public ApiBO getDetail(Long apiId) {
		ApiBO apiBO = get(apiId);
		apiParamManager.fill(apiBO);
		apiExtManager.fill(apiBO);
		return apiBO;
	}

	public List<ApiBO> getMenuTree(Long appId) {
		ApiBO query = new ApiBO();
		query.setAppId(appId);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "asc");
		query.set_pageSize(10000);
		List<ApiBO> apiList = select(query);
		List<ApiBO> apiListReturn = new ArrayList<ApiBO>();
		for (ApiBO api : apiList) {
			for (ApiBO api2 : apiList) {
				if (api.getId().equals(api2.getPid())) {
					api.addChild(api2);
					api2.setParent(api);
				}
			}
		}
		for (ApiBO api : apiList) {
			if (api.getParent() == null) {
				apiListReturn.add(api);
			}
		}
		return apiListReturn;
	}

}
