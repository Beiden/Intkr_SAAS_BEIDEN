package com.intkr.saas.manager.opa;

import java.util.List;

import com.intkr.saas.domain.bo.opa.ApiBO;
import com.intkr.saas.domain.bo.opa.ApiParamBO;
import com.intkr.saas.domain.dbo.opa.ApiParamDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * 
 * @table api_param_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public interface ApiParamManager extends BaseManager<ApiParamBO, ApiParamDO> {

	public ApiBO fill(ApiBO api);

	public List<ApiParamBO> selectByApiId(Long apiId);

}
