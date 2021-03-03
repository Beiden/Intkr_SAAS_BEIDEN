package com.intkr.saas.manager.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.user.RoleApplyDAO;
import com.intkr.saas.domain.bo.user.RoleApplyBO;
import com.intkr.saas.domain.dbo.user.RoleApplyDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.user.RoleApplyManager;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 下午2:15:16
 * @version 1.0
 */
@Repository("RoleApplyManager")
public class RoleApplyManagerImpl extends BaseManagerImpl<RoleApplyBO, RoleApplyDO> implements RoleApplyManager {

	@Resource
	private RoleApplyDAO roleApplyDAO;

	public BaseDAO<RoleApplyDO> getBaseDAO() {
		return roleApplyDAO;
	}

	public RoleApplyBO get(Long userId, Long role) {
		RoleApplyBO query = new RoleApplyBO();
		query.setUserId(userId);
		query.setRoleId(role);
		List<RoleApplyBO> list = select(query);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
