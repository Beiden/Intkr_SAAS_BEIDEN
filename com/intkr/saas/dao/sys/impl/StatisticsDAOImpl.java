package com.intkr.saas.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sys.StatisticsDAO;
import com.intkr.saas.domain.dbo.sys.StatisticsDO;

/**
 * 
 * @author Administrator
 * 
 */
@Repository("StatisticsDAO")
public class StatisticsDAOImpl extends BaseDAOImpl<StatisticsDO> implements StatisticsDAO {

	public String getNameSpace() {
		return "statistics";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
