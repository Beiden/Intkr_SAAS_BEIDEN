package com.intkr.saas.manager.user.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.user.MoneyOperDAO;
import com.intkr.saas.dao.user.impl.MoneyOperDAOImpl;
import com.intkr.saas.domain.bo.user.MoneyOperBO;
import com.intkr.saas.domain.dbo.user.MoneyOperDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.user.MoneyOperManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-3-16 下午7:11:33
 * @version 1.0
 */
@Repository("MoneyOperManager")
public class MoneyOperManagerImpl extends BaseManagerImpl<MoneyOperBO, MoneyOperDO> implements MoneyOperManager {

	private MoneyOperDAO moneyOperDAO = IOC.get(MoneyOperDAOImpl.class);

	public BaseDAO<MoneyOperDO> getBaseDAO() {
		return moneyOperDAO;
	}

}
