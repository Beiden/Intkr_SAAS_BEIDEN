package com.intkr.saas.manager.user;

import java.util.List;

import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.bo.user.RoleApplyBO;
import com.intkr.saas.domain.bo.user.RoleBO;
import com.intkr.saas.domain.dbo.user.RoleDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2011-11-16 下午4:28:04
 * @version 1.0
 */
public interface RoleManager extends BaseManager<RoleBO, RoleDO> {

	public <T> List<T> fill(List<T> list);

	public UserBO fill(UserBO user);

	public RoleApplyBO fill(RoleApplyBO roleApply);

	public List<RoleBO> select();

	public List<RoleBO> getByUserId(Long userId);

	public RoleBO getByCode(String code);

	public List<RoleBO> getAllFull(Long saasId);

}
