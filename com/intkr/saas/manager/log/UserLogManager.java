package com.intkr.saas.manager.log;

import java.util.Date;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.domain.bo.log.UserLogBO;
import com.intkr.saas.domain.dbo.log.UserLogDO;

/**
 * 
 * @author Beiden
 * @date 2016-06-15 20:30:06
 * @version 1.0
 */
public interface UserLogManager extends BaseManager<UserLogBO, UserLogDO> {

	public long insertAsyn(UserLogBO object);

	public void deleteReal(Date maxGmtCreated);

}
