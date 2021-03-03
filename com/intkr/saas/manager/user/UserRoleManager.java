package com.intkr.saas.manager.user;

import java.util.List;

import com.intkr.saas.domain.bo.user.UserRoleBO;
import com.intkr.saas.domain.dbo.user.UserRoleDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2018-12-06 19:27:30
 * @version 1.0
 */
public interface UserRoleManager extends BaseManager<UserRoleBO, UserRoleDO> {

	public List<UserRoleBO> selectByUserId(Long userId);

	public void deleteRole(Long userId, Long roleId);

	public UserRoleBO addRole(Long userId, Long roleId);

}
