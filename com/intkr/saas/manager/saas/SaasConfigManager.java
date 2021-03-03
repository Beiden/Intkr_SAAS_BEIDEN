package com.intkr.saas.manager.saas;

import java.util.List;

import com.intkr.saas.domain.bo.saas.SaasClientBO;
import com.intkr.saas.domain.bo.saas.SaasConfigBO;
import com.intkr.saas.domain.dbo.saas.SaasConfigDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-24 19:17:55
 * @version 1.0
 */
public interface SaasConfigManager extends BaseManager<SaasConfigBO, SaasConfigDO> {

	public <T> List<T> fill(List<T> list);

	public SaasClientBO fill(SaasClientBO saas);

	public SaasConfigBO getBySaasId(Long saasId);

	public SaasConfigBO getBySaasIdWithoutCache(Long saasId);

}
