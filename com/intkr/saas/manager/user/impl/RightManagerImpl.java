package com.intkr.saas.manager.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.user.RightDAO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.bo.user.UserRightBO;
import com.intkr.saas.domain.bo.user.RoleRightBO;
import com.intkr.saas.domain.bo.user.AuthorityBO;
import com.intkr.saas.domain.bo.user.RightBO;
import com.intkr.saas.domain.bo.user.RoleBO;
import com.intkr.saas.domain.dbo.user.RightDO;
import com.intkr.saas.domain.type.user.RightType;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.user.UserRightManager;
import com.intkr.saas.manager.user.RoleRightManager;
import com.intkr.saas.manager.user.RightManager;

/**
 * 
 * @author Beiden
 * @date 2011-11-16 下午4:28:37
 * @version 1.0
 */
@Repository("RightManager")
public class RightManagerImpl extends BaseManagerImpl<RightBO, RightDO> implements RightManager {

	@Resource
	private RightDAO rightDAO;

	@Resource
	private RoleRightManager roleRightManager;

	@Resource
	private UserRightManager userRightManager;

	public BaseDAO<RightDO> getBaseDAO() {
		return rightDAO;
	}

	public <T> List<T> fill(List<T> list) {
		if (list == null) {
			return new ArrayList<T>();
		}
		for (T bo : list) {
			if (bo instanceof UserBO) {
				fill((UserBO) bo);
			} else if (bo instanceof RoleBO) {
				fill((RoleBO) bo);
			}
		}
		return list;
	}

	public UserBO fill(UserBO user) {
		if (user == null) {
			return user;
		}
		List<UserRightBO> authRightList = userRightManager.selectByUserId(user.getId());
		List<RightBO> rights = new ArrayList<RightBO>();
		for (UserRightBO auth : authRightList) {
			RightBO right = get(auth.getRightId());
			if (right != null) {
				rights.add(right);
			}
		}
		if (user.getAuthority() == null) {
			user.setAuthority(new AuthorityBO());
		}
		user.getAuthority().setRightList(rights);
		return user;
	}

	public RoleBO fill(RoleBO role) {
		if (role == null) {
			return null;
		}
		List<RoleRightBO> authRightList = roleRightManager.selectByRoleId(role.getId());
		List<RightBO> rights = new ArrayList<RightBO>();
		for (RoleRightBO auth : authRightList) {
			RightBO right = get(auth.getRightId());
			if (right != null) {
				rights.add(right);
			}
		}
		role.setRights(rights);
		return role;
	}

	public List<RightBO> getActionRight() {
		RightBO query = new RightBO();
		query.set_pageSize(100000);
		query.setType(RightType.Action.getCode());
		query.setQuery("orderBy", "code");
		return select(query);
	}

	public List<RightBO> getScreenRight() {
		RightBO query = new RightBO();
		query.set_pageSize(100000);
		query.setType(RightType.Page.getCode());
		query.setQuery("orderBy", "code");
		return select(query);
	}

	public List<RightBO> getCustomRight() {
		RightBO query = new RightBO();
		query.set_pageSize(100000);
		query.setType(RightType.Custom.getCode());
		query.setQuery("orderBy", "code");
		return select(query);
	}

	public RightBO getByCode(String code) {
		if (code == null || "".equals(code)) {
			return null;
		}
		RightBO query = new RightBO();
		query.setCode(code);
		query.set_pageSize(1);
		return selectOne(query);
	}
	
	public Long countByCode(String code) {
		if (code == null || "".equals(code)) {
			return null;
		}
		RightBO query = new RightBO();
		query.setCode(code);
		query.set_pageSize(1);
		return count(query);
	}

	public List<RightBO> getAllFull(String sys) {
		if ("saas".equalsIgnoreCase(sys)) {
			sys = null;
		}
		RightBO query = new RightBO();
		query.set_pageSize(100000);
		query.setSys(sys);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "asc");
		List<RightBO> categorys = select(query);
		List<RightBO> firstCategorys = new ArrayList<RightBO>();
		Map<Long, RightBO> map = new HashMap<Long, RightBO>();
		for (RightBO bo : categorys) {
			map.put(bo.getId(), bo);
		}
		for (RightBO child : categorys) {
			if (child.getPid() != null && map.containsKey(child.getPid())) {
				RightBO parent = map.get(child.getPid());
				parent.addChild(child);
				child.setParent(parent);
			}
		}
		for (RightBO bo : categorys) {
			if (bo.getParent() == null) {
				firstCategorys.add(bo);
			}
		}
		return firstCategorys;
	}
}
