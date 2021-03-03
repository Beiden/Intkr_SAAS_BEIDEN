package com.intkr.saas.manager.log;

import java.util.Date;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.domain.bo.log.SignLogBO;
import com.intkr.saas.domain.dbo.log.SignLogDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-24 上午11:43:18
 * @version 1.0
 */
public interface SignLogManager extends BaseManager<SignLogBO, SignLogDO> {

	public void deleteReal(Date maxGmtCreated);

}
