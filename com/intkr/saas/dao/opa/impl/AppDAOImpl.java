package com.intkr.saas.dao.opa.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.opa.AppDAO;
import com.intkr.saas.domain.dbo.opa.AppDO;

/**
 * 
 * 
 * @table app_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
@Repository("opa.AppDAO")
public class AppDAOImpl extends BaseDAOImpl<AppDO> implements AppDAO {

	public String getNameSpace() {
		return "opa_app";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
