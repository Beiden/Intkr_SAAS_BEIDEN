package com.intkr.saas.domain.bo.user;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Beiden
 * @date 2016-4-22 上午9:22:26
 * @version 1.0
 */
public class AuthorityBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 所有权限
	 */
	private List<RightBO> rightList;

	/**
	 * 所有角色
	 */
	private List<RoleBO> roleList;

	public List<RightBO> getRightList() {
		return rightList;
	}

	public void setRightList(List<RightBO> rightList) {
		this.rightList = rightList;
	}

	public List<RoleBO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleBO> roleList) {
		this.roleList = roleList;
	}

	public boolean containRole(String roleCode) {
		if (roleCode == null || "".equals(roleCode)) {
			return false;
		}
		for (RoleBO role : roleList) {
			if (1 == role.getId()) {
				return true;
			}
			if (roleCode.equals(role.getCode())) {
				return true;
			}
		}
		return false;
	}

	public boolean hasRight(String code) {
		if (code == null || "".equals(code)) {
			return false;
		}
		for (RightBO right : rightList) {
			if (right.getCode().equals(code)) {
				return true;
			}
		}
		for (RoleBO role : roleList) {
			if (role.hasRight(code)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasRole(String role) {
		if (role == null || "".equals(role)) {
			return false;
		}
		for (RoleBO roleBO : roleList) {
			if (roleBO.getCode().equals(role)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 不判断拥有的角色是否有权限
	 * 
	 * @param code
	 * @return
	 */
	public boolean hasRightAdmin(String code) {
		if (rightList == null) {
			return false;
		}
		for (RightBO right : rightList) {
			if (right.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

}
