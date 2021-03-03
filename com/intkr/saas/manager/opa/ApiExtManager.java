package com.intkr.saas.manager.opa;

import com.intkr.saas.domain.bo.opa.ApiBO;
import com.intkr.saas.domain.bo.opa.ApiExtBO;
import com.intkr.saas.domain.dbo.opa.ApiExtDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * 
 * @table api_ext_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public interface ApiExtManager extends BaseManager<ApiExtBO, ApiExtDO> {

	public ApiBO fill(ApiBO api);

	public ApiExtBO getByApiId(Long apiId);

}
