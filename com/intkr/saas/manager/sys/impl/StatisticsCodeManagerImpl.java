package com.intkr.saas.manager.sys.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.sys.StatisticsCodeDAO;
import com.intkr.saas.domain.bo.sys.StatisticsCodeBO;
import com.intkr.saas.domain.dbo.sys.StatisticsCodeDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.sys.StatisticsCodeManager;

/**
 * 
 * @author Beiden
 * @date 2016-5-21 下午11:43:18
 * @version 1.0
 */
@Repository("StatisticsCodeManager")
public class StatisticsCodeManagerImpl extends BaseManagerImpl<StatisticsCodeBO, StatisticsCodeDO> implements StatisticsCodeManager {

	@Resource
	private StatisticsCodeDAO statisticsCodeDAO;

	public BaseDAO<StatisticsCodeDO> getBaseDAO() {
		return statisticsCodeDAO;
	}

}
