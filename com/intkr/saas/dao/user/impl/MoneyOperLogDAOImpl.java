package com.intkr.saas.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.user.MoneyOperLogDAO;
import com.intkr.saas.domain.dbo.user.MoneyOperLogDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-15 下午3:25:42
 * @version 1.0
 */
@Repository("MoneyOperLogDAO")
public class MoneyOperLogDAOImpl extends BaseDAOImpl<MoneyOperLogDO> implements MoneyOperLogDAO {

	public String getNameSpace() {
		return "moneyOperLog";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
