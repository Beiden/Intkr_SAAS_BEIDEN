package com.intkr.saas.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.user.MoneyOperDAO;
import com.intkr.saas.domain.dbo.user.MoneyOperDO;

/**
 * 
 * @author Beiden
 * @date 2016-3-16 下午7:10:32
 * @version 1.0
 */
@Repository("MoneyOperDAO")
public class MoneyOperDAOImpl extends BaseDAOImpl<MoneyOperDO> implements MoneyOperDAO {

	public String getNameSpace() {
		return "moneyOper";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
