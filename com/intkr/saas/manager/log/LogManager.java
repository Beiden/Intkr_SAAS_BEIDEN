package com.intkr.saas.manager.log;

import java.util.Date;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.domain.bo.log.LogBO;
import com.intkr.saas.domain.dbo.log.LogDO;

/**
 * 
 * @author Beiden
 * @date 2014-7-4 下午10:09:09
 * @version 1.0
 */
public interface LogManager extends BaseManager<LogBO, LogDO> {

	public void deleteReal(Date maxGmtCreated);

}
