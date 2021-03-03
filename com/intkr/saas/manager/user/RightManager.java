package com.intkr.saas.manager.user;

import java.util.List;

import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.bo.user.RightBO;
import com.intkr.saas.domain.bo.user.RoleBO;
import com.intkr.saas.domain.dbo.user.RightDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2011-11-16 下午4:28:04
 * @version 1.0
 */
public interface RightManager extends BaseManager<RightBO, RightDO> {

	public <T> List<T> fill(List<T> list);

	public UserBO fill(UserBO user);

	public RoleBO fill(RoleBO role);

	public List<RightBO> getActionRight();

	public List<RightBO> getScreenRight();

	public List<RightBO> getCustomRight();

	public RightBO getByCode(String code);
	
	public Long countByCode(String code);

	public List<RightBO> getAllFull(String sys);

}
