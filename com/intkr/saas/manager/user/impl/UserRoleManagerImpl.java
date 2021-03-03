package com.intkr.saas.manager.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.user.UserRoleDAO;
import com.intkr.saas.domain.bo.user.UserRoleBO;
import com.intkr.saas.domain.dbo.user.UserRoleDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.user.UserRoleManager;

/**
 * 
 * @author Beiden
 * @date 2018-12-06 19:27:30
 * @version 1.0
 */
@Repository("UserRoleManager")
public class UserRoleManagerImpl extends BaseManagerImpl<UserRoleBO, UserRoleDO> implements UserRoleManager {

	@Resource
	private UserRoleDAO userRoleDAO;

	public BaseDAO<UserRoleDO> getBaseDAO() {
		return userRoleDAO;
	}

	public List<UserRoleBO> selectByUserId(Long userId) {
		if (userId == null || userId <= 0L) {
			return new ArrayList<UserRoleBO>();
		}
		UserRoleBO query = new UserRoleBO();
		query.setUserId(userId);
		query.set_pageSize(10000);
		return select(query);
	}

	public void deleteRole(Long userId, Long roleId) {
		if (userId == null || userId <= 0L) {
			return;
		}
		if (roleId == null || roleId <= 0L) {
			return;
		}
		UserRoleBO query = new UserRoleBO();
		query.setUserId(userId);
		query.setRoleId(roleId);
		query.set_pageSize(10000);
		List<UserRoleBO> list = select(query);
		for (UserRoleBO bo : list) {
			delete(bo.getId());
		}
	}

	public UserRoleBO addRole(Long userId, Long roleId) {
		if (userId == null || userId <= 0L) {
			return null;
		}
		if (roleId == null || roleId <= 0L) {
			return null;
		}
		UserRoleBO bo = new UserRoleBO();
		bo.setId(getId());
		bo.setUserId(userId);
		bo.setRoleId(roleId);
		insert(bo);
		return bo;
	}

}
