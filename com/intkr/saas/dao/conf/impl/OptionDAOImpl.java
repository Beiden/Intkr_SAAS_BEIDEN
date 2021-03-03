package com.intkr.saas.dao.conf.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.conf.OptionDAO;
import com.intkr.saas.domain.dbo.conf.OptionDO;

/**
 * 
 * @author Beiden
 * @date 2011-5-7 下午12:45:36
 * @version 1.0
 */
@Repository("OptionDAO")
public class OptionDAOImpl extends BaseDAOImpl<OptionDO> implements OptionDAO {

	public String getNameSpace() {
		return "option";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
