package com.intkr.saas.domain.dbo.user;

import com.intkr.saas.domain.BaseDO;

/**
 * 授权-帐号角色
 * 
 * @table ik_auth_account_role
 * 
 * @author Beiden
 * @date 2018-12-06 19:27:29
 * @version 1.0
 */
public class UserRoleDO extends BaseDO {

	private static final long serialVersionUID = 1L;

	// 帐号
	private Long userId;

	// 角色
	private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
