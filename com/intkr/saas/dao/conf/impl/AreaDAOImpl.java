package com.intkr.saas.dao.conf.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.conf.AreaDAO;
import com.intkr.saas.domain.dbo.conf.AreaDO;

/**
 * 
 * @author Beiden
 * @date 2016-5-15 上午9:39:13
 * @version 1.0
 */
@Repository("AreaDAO")
public class AreaDAOImpl extends BaseDAOImpl<AreaDO> implements AreaDAO {

	public String getNameSpace() {
		return "area";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
