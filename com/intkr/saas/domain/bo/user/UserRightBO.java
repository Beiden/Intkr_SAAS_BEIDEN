package com.intkr.saas.domain.bo.user;

import com.intkr.saas.domain.BaseBO;

/**
 * 授权-帐号权限
 * 
 * @table ik_auth_account_right
 * 
 * @author Beiden
 * @date 2018-12-06 19:23:59
 * @version 1.0
 */
public class UserRightBO extends BaseBO {

	private static final long serialVersionUID = 1L;

	// 帐号
	private Long userId;

	// 权限
	private Long rightId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRightId() {
		return rightId;
	}

	public void setRightId(Long rightId) {
		this.rightId = rightId;
	}

}
