package com.intkr.saas.dao.log;

import java.util.Date;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.domain.dbo.log.UserLogDO;

/**
 * 
 * @author Beiden
 * @date 2016-06-15 20:30:06
 * @version 1.0
 */
public interface UserLogDAO extends BaseDAO<UserLogDO> {

	public void deleteReal(Date maxGmtCreated);

}
