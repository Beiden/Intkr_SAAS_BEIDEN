package com.intkr.saas.dao.user.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.user.MoneyAccountDAO;
import com.intkr.saas.domain.dbo.user.MoneyAccountDO;

/**
 * 
 * @author Beiden
 * @date 2017-10-02 17:59:27
 * @version 1.0
 */
@Repository("ShopMoneyAccountDAO")
public class MoneyAccountDAOImpl extends BaseDAOImpl<MoneyAccountDO> implements MoneyAccountDAO {

	public String getNameSpace() {
		return "moneyAccount";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
