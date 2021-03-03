package com.intkr.saas.manager.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.sys.StatisticsDAO;
import com.intkr.saas.domain.bo.sys.StatisticsBO;
import com.intkr.saas.domain.dbo.sys.StatisticsDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.sys.StatisticsManager;

/**
 * 
 * @author Administrator
 * 
 */
@Repository("StatisticsManager")
public class StatisticsManagerImpl extends BaseManagerImpl<StatisticsBO, StatisticsDO> implements StatisticsManager {

	@Resource
	private StatisticsDAO statisticsDAO;

	public BaseDAO<StatisticsDO> getBaseDAO() {
		return statisticsDAO;
	}

	public boolean isExist(String time, String type) {
		if (time == null || "".equals(time)) {
			throw new RuntimeException("param error!");
		}
		if (type == null || "".equals(type)) {
			throw new RuntimeException("param error!");
		}
		StatisticsBO query = new StatisticsBO();
		query.setTime(time);
		query.setType(type);
		Long count = count(query);
		return count > 0;
	}

	public StatisticsBO get(String time, String type) {
		if (time == null || "".equals(time)) {
			throw new RuntimeException("param error!");
		}
		if (type == null || "".equals(type)) {
			throw new RuntimeException("param error!");
		}
		StatisticsBO query = new StatisticsBO();
		query.setTime(time);
		query.setType(type);
		return selectOne(query);
	}

}
