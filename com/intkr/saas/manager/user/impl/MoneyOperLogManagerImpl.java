package com.intkr.saas.manager.user.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.user.MoneyOperLogDAO;
import com.intkr.saas.dao.user.impl.MoneyOperLogDAOImpl;
import com.intkr.saas.domain.bo.user.MoneyOperLogBO;
import com.intkr.saas.domain.dbo.user.MoneyOperLogDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.user.MoneyOperLogManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-15 下午3:26:59
 * @version 1.0
 */
@Repository("MoneyOperLogManager")
public class MoneyOperLogManagerImpl extends BaseManagerImpl<MoneyOperLogBO, MoneyOperLogDO> implements MoneyOperLogManager {

	private MoneyOperLogDAO moneyOperLogDAO = IOC.get(MoneyOperLogDAOImpl.class);

	public BaseDAO<MoneyOperLogDO> getBaseDAO() {
		return moneyOperLogDAO;
	}

}
