package com.intkr.saas.manager.user;

import java.util.List;

import com.intkr.saas.domain.bo.user.UserRightBO;
import com.intkr.saas.domain.dbo.user.UserRightDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2018-12-06 19:23:59
 * @version 1.0
 */
public interface UserRightManager extends BaseManager<UserRightBO, UserRightDO> {

	public List<UserRightBO> selectByUserId(Long userId);

}
