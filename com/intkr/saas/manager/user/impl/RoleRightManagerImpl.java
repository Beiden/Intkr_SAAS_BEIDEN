package com.intkr.saas.manager.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.user.RoleRightDAO;
import com.intkr.saas.domain.bo.user.RoleRightBO;
import com.intkr.saas.domain.dbo.user.RoleRightDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.user.RoleRightManager;

/**
 * 
 * @author Beiden
 * @date 2018-12-06 19:28:05
 * @version 1.0
 */
@Repository("RoleRightManager")
public class RoleRightManagerImpl extends BaseManagerImpl<RoleRightBO, RoleRightDO> implements RoleRightManager {

	@Resource
	private RoleRightDAO roleRightDAO;

	public BaseDAO<RoleRightDO> getBaseDAO() {
		return roleRightDAO;
	}

	public List<RoleRightBO> selectByRoleId(Long roleId) {
		if (roleId == null || roleId <= 0L) {
			return new ArrayList<RoleRightBO>();
		}
		RoleRightBO query = new RoleRightBO();
		query.setRoleId(roleId);
		query.set_pageSize(10000);
		return select(query);
	}

}
