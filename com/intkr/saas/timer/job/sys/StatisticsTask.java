package com.intkr.saas.timer.job.sys;

import java.util.Calendar;
import java.util.Date;

import com.intkr.saas.domain.bo.log.SysLogBO;
import com.intkr.saas.domain.bo.sys.StatisticsBO;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.log.SysLogManager;
import com.intkr.saas.manager.sys.StatisticsManager;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2013-7-25 下午11:53:26
 * @version 1.0
 */
public class StatisticsTask {

	// 增加或减少天数
	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

	private static Date getLastDayStart(String time) {
		Date date = DateUtil.parse(time, "yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date dayStart = c.getTime();
		return dayStart;
	}

	private static Date getLastDayEnd(String time) {
		Date date = DateUtil.parse(time, "yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 24);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date dayStart = c.getTime();
		return dayStart;
	}

	public void run() {
		OptionManager optionManager = IOC.get(OptionManager.class);
		if (!optionManager.taskOpen()) {
			return;
		}
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		Date date = c.getTime();
		String time = DateUtil.formatDate(date);
		statistics(time);
	}

	public void statistics(String time) {
		dailyPV(time);
	}

	public void statisticsInsert(String time, String type, Long count) {
		StatisticsManager statisticsManager = IOC.get(StatisticsManager.class);
		StatisticsBO update = statisticsManager.get(time, type);
		if (update == null) {
			StatisticsBO statistics = new StatisticsBO();
			statistics.setId(statisticsManager.getId());
			statistics.setTime(time);
			statistics.setType(type);
			statistics.setNum(count);
			statisticsManager.insert(statistics);
		} else {
			update.setNum(count);
			statisticsManager.update(update);
		}
	}

	public void dailyPV(String time) {
		SysLogManager sysLogManager = IOC.get(SysLogManager.class);
		Date dayStart = getLastDayStart(time);
		Date dayEnd = getLastDayEnd(time);
		SysLogBO query = new SysLogBO();
		query.setQuery("dayStart", dayStart);
		query.setQuery("dayEnd", dayEnd);
		Long count = sysLogManager.count(query);
		statisticsInsert(time, "PV", count);
	}

}
