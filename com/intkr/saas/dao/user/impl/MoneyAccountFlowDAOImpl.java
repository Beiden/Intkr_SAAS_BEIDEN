package com.intkr.saas.dao.user.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.user.MoneyAccountFlowDAO;
import com.intkr.saas.domain.dbo.user.MoneyAccountFlowDO;

/**
 * 
 * @author Beiden
 * @date 2016-3-16 下午7:10:32
 * @version 1.0
 */
@Repository("ShopMoneyAccountFlowDAO")
public class MoneyAccountFlowDAOImpl extends BaseDAOImpl<MoneyAccountFlowDO> implements MoneyAccountFlowDAO {

	public String getNameSpace() {
		return "moneyAccountFlow";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
