package com.intkr.saas.manager.sys;

import com.intkr.saas.domain.bo.sys.StatisticsBO;
import com.intkr.saas.domain.dbo.sys.StatisticsDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Administrator
 * 
 */
public interface StatisticsManager extends BaseManager<StatisticsBO, StatisticsDO> {

	public boolean isExist(String time, String type);

	public StatisticsBO get(String time, String type);

}
