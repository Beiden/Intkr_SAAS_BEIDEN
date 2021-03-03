package com.intkr.saas.dao.log;

import java.util.Date;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.domain.dbo.log.LogDO;

/**
 * 
 * @author Beiden
 * @date 2014-7-4 下午10:08:30
 * @version 1.0
 */
public interface LogDAO extends BaseDAO<LogDO> {

	public void deleteReal(Date maxGmtCreated);

}
