package com.intkr.saas.manager.opa;

import java.util.List;

import com.intkr.saas.domain.bo.opa.ApiBO;
import com.intkr.saas.domain.dbo.opa.ApiDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * 
 * @table api_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public interface ApiManager extends BaseManager<ApiBO, ApiDO> {

	public ApiBO getDetail(Long apiId);

	public List<ApiBO> getMenuTree(Long appId);

}
