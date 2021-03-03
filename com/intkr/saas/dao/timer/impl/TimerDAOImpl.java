package com.intkr.saas.dao.timer.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.timer.TimerDAO;
import com.intkr.saas.domain.dbo.timer.TimerDO;

/**
 * 
 * @author Beiden
 * @date 2016-6-2 下午8:51:16
 * @version 1.0
 */
@Repository("TimerDAO")
public class TimerDAOImpl extends BaseDAOImpl<TimerDO> implements TimerDAO {

	public String getNameSpace() {
		return "timer";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
