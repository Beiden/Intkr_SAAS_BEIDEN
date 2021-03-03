package com.intkr.saas.manager.user;


import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.domain.bo.user.RoleApplyBO;
import com.intkr.saas.domain.dbo.user.RoleApplyDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 下午2:11:53
 * @version 1.0
 */
public interface RoleApplyManager extends BaseManager<RoleApplyBO, RoleApplyDO> {

	public RoleApplyBO get(Long userId, Long roleId);

}
