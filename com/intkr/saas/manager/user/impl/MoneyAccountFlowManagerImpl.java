package com.intkr.saas.manager.user.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.user.MoneyAccountFlowDAO;
import com.intkr.saas.domain.bo.user.MoneyAccountFlowBO;
import com.intkr.saas.domain.dbo.user.MoneyAccountFlowDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.user.MoneyAccountFlowManager;

/**
 * 
 * @author Beiden
 * @date 2016-3-16 下午7:11:33
 * @version 1.0
 */
@Repository("MoneyAccountFlowManager")
public class MoneyAccountFlowManagerImpl extends BaseManagerImpl<MoneyAccountFlowBO, MoneyAccountFlowDO> implements MoneyAccountFlowManager {

	@Resource
	private MoneyAccountFlowDAO moneyOperDAO;

	public BaseDAO<MoneyAccountFlowDO> getBaseDAO() {
		return moneyOperDAO;
	}

}
