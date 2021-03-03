package com.intkr.saas.manager.saas;

import java.util.List;

import com.intkr.saas.domain.bo.saas.SaasClientBO;
import com.intkr.saas.domain.dbo.saas.SaasClientDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-19 22:00:49
 * @version 1.0
 */
public interface SaasClientManager extends BaseManager<SaasClientBO, SaasClientDO> {

	public SaasClientBO getSaas(Long saasId);

	public String getSaasDomain(Long saasId);

	public SaasClientBO getFullByDomain(String domain);

	public SaasClientBO getFull(Long saasId);

	public SaasClientBO getDefaultFull();

	public SaasClientBO getDefault();

	public SaasClientBO full(SaasClientBO saas);

	public List<SaasClientBO> selectAndCountFull(SaasClientBO query);

}
