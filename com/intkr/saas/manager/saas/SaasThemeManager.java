package com.intkr.saas.manager.saas;

import com.intkr.saas.domain.bo.saas.SaasThemeBO;
import com.intkr.saas.domain.dbo.saas.SaasThemeDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-19 10:45:08
 * @version 1.0
 */
public interface SaasThemeManager extends BaseManager<SaasThemeBO, SaasThemeDO> {

	public SaasThemeBO getByName(String name);

	public SaasThemeBO get(Long primary);

}
