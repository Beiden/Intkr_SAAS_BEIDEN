package com.intkr.saas.manager.user;

import java.util.List;

import com.intkr.saas.domain.bo.user.RoleRightBO;
import com.intkr.saas.domain.dbo.user.RoleRightDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2018-12-06 19:28:05
 * @version 1.0
 */
public interface RoleRightManager extends BaseManager<RoleRightBO, RoleRightDO> {

	public List<RoleRightBO> selectByRoleId(Long roleId);

}
