package com.intkr.saas.manager.timer.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.timer.TimerDAO;
import com.intkr.saas.domain.bo.timer.TimerBO;
import com.intkr.saas.domain.dbo.timer.TimerDO;
import com.intkr.saas.domain.type.timer.TimerStatus;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.timer.TimerManager;

/**
 * 
 * @author Beiden
 * @date 2016-6-2 下午8:52:04
 * @version 1.0
 */
@Repository("TimerManager")
public class TimerManagerImpl extends BaseManagerImpl<TimerBO, TimerDO> implements TimerManager {

	@Resource
	private TimerDAO timerDAO;

	public BaseDAO<TimerDO> getBaseDAO() {
		return timerDAO;
	}

	public List<TimerBO> getLastMinute() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.MINUTE, 1);
		Date endTime = calendar.getTime();
		TimerBO query = new TimerBO();
		query.setStatus(TimerStatus.WaitRun.getCode());
		query.setQuery("endTime", endTime);
		query.setQuery("orderBy", "time");
		query.setQuery("order", "asc");
		query.set_pageSize(10000);
		List<TimerBO> timerList = select(query);
		return timerList;
	}

	public TimerBO getByCode(String code) {
		TimerBO query = new TimerBO();
		query.setCode(code);
		return selectOne(query);
	}

}
