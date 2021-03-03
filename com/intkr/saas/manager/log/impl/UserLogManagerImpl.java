package com.intkr.saas.manager.log.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.log.UserLogDAO;
import com.intkr.saas.domain.bo.log.UserLogBO;
import com.intkr.saas.domain.dbo.log.UserLogDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.log.UserLogManager;
import com.intkr.saas.util.ExecutorsUtil;

/**
 * 
 * @author Beiden
 * @date 2016-06-15 20:30:06
 * @version 1.0
 */
@Repository("UserLogManager")
public class UserLogManagerImpl extends BaseManagerImpl<UserLogBO, UserLogDO> implements UserLogManager {

	@Resource
	private UserLogDAO userLogDAO;

	public BaseDAO<UserLogDO> getBaseDAO() {
		return userLogDAO;
	}

	public long insertAsyn(final UserLogBO object) {
		ExecutorsUtil.execute(new Runnable() {
			public void run() {
				insert(object);
			}
		});
		return 1;
	}

	public void deleteReal(Date maxGmtCreated) {
		userLogDAO.deleteReal(maxGmtCreated);
	}

}
