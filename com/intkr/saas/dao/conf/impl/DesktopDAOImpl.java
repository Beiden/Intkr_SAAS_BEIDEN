package com.intkr.saas.dao.conf.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.conf.DesktopDAO;
import com.intkr.saas.domain.dbo.conf.DesktopDO;

/**
 * 
 * @author Beiden
 * @date 2016-07-05 17:44:34
 * @version 1.0
 */
@Repository("DesktopDAO")
public class DesktopDAOImpl extends BaseDAOImpl<DesktopDO> implements DesktopDAO {

	public String getNameSpace() {
		return "desktop";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
