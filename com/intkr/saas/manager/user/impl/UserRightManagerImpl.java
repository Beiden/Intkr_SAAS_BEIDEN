package com.intkr.saas.manager.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.user.UserRightDAO;
import com.intkr.saas.domain.bo.user.UserRightBO;
import com.intkr.saas.domain.dbo.user.UserRightDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.user.UserRightManager;

/**
 * 
 * @author Beiden
 * @date 2018-12-06 19:23:59
 * @version 1.0
 */
@Repository("UserRightManager")
public class UserRightManagerImpl extends BaseManagerImpl<UserRightBO, UserRightDO> implements UserRightManager {

	@Resource
	private UserRightDAO userRightDAO;

	public BaseDAO<UserRightDO> getBaseDAO() {
		return userRightDAO;
	}

	public List<UserRightBO> selectByUserId(Long userId) {
		if (userId == null || userId <= 0L) {
			return new ArrayList<UserRightBO>();
		}
		UserRightBO query = new UserRightBO();
		query.setUserId(userId);
		query.set_pageSize(10000);
		return select(query);
	}

}
