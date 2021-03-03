package com.intkr.saas.manager.saas;

import java.util.List;

import com.intkr.saas.domain.bo.saas.SaasClientBO;
import com.intkr.saas.domain.bo.saas.SaasDomainBO;
import com.intkr.saas.domain.dbo.saas.SaasDomainDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-05-25 22:55:26
 * @version 1.0
 */
public interface SaasDomainManager extends BaseManager<SaasDomainBO, SaasDomainDO> {

	public SaasDomainBO getByDomain(String domain);

	public List<SaasDomainBO> getBySaasId(Long saasId);

	public <T> List<T> fill(List<T> list);

	public SaasClientBO fill(SaasClientBO saas);

	public void invalidateCache(String domain);

}
