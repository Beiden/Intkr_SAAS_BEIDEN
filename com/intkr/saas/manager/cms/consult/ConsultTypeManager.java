package com.intkr.saas.manager.cms.consult;

import com.intkr.saas.domain.bo.consult.ConsultTypeBO;
import com.intkr.saas.domain.dbo.consult.ConsultTypeDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-04-14 21:37:49
 * @version 1.0
 */
public interface ConsultTypeManager extends BaseManager<ConsultTypeBO, ConsultTypeDO> {

	public ConsultTypeBO getByName(String type);

}
