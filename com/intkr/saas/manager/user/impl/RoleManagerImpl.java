package com.intkr.saas.manager.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.user.RoleDAO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.bo.user.UserRoleBO;
import com.intkr.saas.domain.bo.user.AuthorityBO;
import com.intkr.saas.domain.bo.user.RoleApplyBO;
import com.intkr.saas.domain.bo.user.RoleBO;
import com.intkr.saas.domain.dbo.user.RoleDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.user.UserRoleManager;
import com.intkr.saas.manager.user.RoleManager;

/**
 * 
 * @author Beiden
 * @date 2011-11-16 下午4:28:37
 * @version 1.0
 */
@Repository("RoleManager")
public class RoleManagerImpl extends BaseManagerImpl<RoleBO, RoleDO> implements RoleManager {

	@Resource
	private RoleDAO roleDAO;

	@Resource
	private UserRoleManager userRoleManager;

	public BaseDAO<RoleDO> getBaseDAO() {
		return roleDAO;
	}

	public <T> List<T> fill(List<T> list) {
		if (list == null) {
			return new ArrayList<T>();
		}
		for (T bo : list) {
			if (bo instanceof UserBO) {
				fill((UserBO) bo);
			} else if (bo instanceof RoleApplyBO) {
				fill((RoleApplyBO) bo);
			}
		}
		return list;
	}

	public UserBO fill(UserBO user) {
		if (user == null) {
			return user;
		}
		List<UserRoleBO> roleList = userRoleManager.selectByUserId(user.getId());
		List<RoleBO> roles = new ArrayList<RoleBO>();
		for (UserRoleBO auth : roleList) {
			RoleBO role = get(auth.getRoleId());
			if (role != null) {
				roles.add(role);
			}
		}
		if (user.getAuthority() == null) {
			user.setAuthority(new AuthorityBO());
		}
		user.getAuthority().setRoleList(roles);
		return user;
	}

	public List<RoleBO> select() {
		RoleBO query = new RoleBO();
		query.set_pageSize(10000);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "desc");
		return select(query);
	}

	public List<RoleBO> getByUserId(Long userId) {
		if (!IdEngine.isValidate(userId)) {
			return new ArrayList<RoleBO>();
		}
		List<UserRoleBO> roleList = userRoleManager.selectByUserId(userId);
		List<RoleBO> roles = new ArrayList<RoleBO>();
		for (UserRoleBO auth : roleList) {
			RoleBO role = get(auth.getRoleId());
			roles.add(role);
		}
		return roles;
	}

	public RoleApplyBO fill(RoleApplyBO roleApply) {
		if (roleApply == null) {
			return null;
		}
		roleApply.setRole(get(roleApply.getRoleId()));
		return roleApply;
	}

	public RoleBO getByCode(String code) {
		if (code == null || "".equals(code)) {
			return null;
		}
		RoleBO query = new RoleBO();
		query.setCode(code);
		return selectOne(query);
	}

	public List<RoleBO> getAllFull(Long saasId) {
		if (saasId == null || saasId <= 0L) {
			return new ArrayList<RoleBO>();
		}
		RoleBO query = new RoleBO();
		query.set_pageSize(10000);
		query.setSaasId(saasId);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "desc");
		List<RoleBO> categorys = select(query);
		List<RoleBO> firstCategorys = new ArrayList<RoleBO>();
		Map<Long, RoleBO> map = new HashMap<Long, RoleBO>();
		for (RoleBO bo : categorys) {
			map.put(bo.getId(), bo);
		}
		for (RoleBO bo : categorys) {
			if (bo.getPid() != null && map.containsKey(bo.getPid())) {
				RoleBO parent = map.get(bo.getPid());
				parent.addChild(bo);
				bo.setParent(parent);
			}
		}
		for (RoleBO bo : categorys) {
			if (bo.getParent() == null) {
				firstCategorys.add(bo);
			}
		}
		return firstCategorys;
	}

}
