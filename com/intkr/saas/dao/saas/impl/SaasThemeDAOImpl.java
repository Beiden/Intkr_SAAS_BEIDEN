package com.intkr.saas.dao.saas.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.saas.SaasThemeDAO;
import com.intkr.saas.domain.dbo.saas.SaasThemeDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-19 10:45:08
 * @version 1.0
 */
@Repository("SaasThemeDAO")
public class SaasThemeDAOImpl extends BaseDAOImpl<SaasThemeDO> implements SaasThemeDAO {

	public String getNameSpace() {
		return "saasTheme";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
