package com.intkr.saas.dao.sys.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sys.StatisticsCodeDAO;
import com.intkr.saas.domain.dbo.sys.StatisticsCodeDO;

/**
 * 
 * @author Beiden
 * @date 2016-5-21 下午11:22:34
 * @version 1.0
 */
@Repository("StatisticsCodeDAO")
public class StatisticsCodeDAOImpl extends BaseDAOImpl<StatisticsCodeDO> implements StatisticsCodeDAO {

	public String getNameSpace() {
		return "statisticsCode";
	}
	
	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
