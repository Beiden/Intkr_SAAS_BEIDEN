package com.intkr.saas.dao.user;


import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.domain.dbo.user.UserDO;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-24 下午03:33:54
 * @version 1.0
 */
public interface UserDAO extends BaseDAO<UserDO> {

	public void updateMoney(UserDO user);

	public void updateMoney(Long userId, Long money);

}
