package com.intkr.saas.domain.bo.user;

import com.intkr.saas.domain.BaseBO;

/**
 * 授权-角色权限
 * 
 * @table ik_auth_role_right
 * 
 * @author Beiden
 * @date 2018-12-06 19:28:04
 * @version 1.0
 */
public class RoleRightBO extends BaseBO {

	private static final long serialVersionUID = 1L;

	// 角色
	private Long roleId;

	// 权限
	private Long rightId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getRightId() {
		return rightId;
	}

	public void setRightId(Long rightId) {
		this.rightId = rightId;
	}

}
