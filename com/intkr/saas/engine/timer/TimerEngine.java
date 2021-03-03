package com.intkr.saas.engine.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.timer.TimerBO;
import com.intkr.saas.domain.type.timer.TimerStatus;
import com.intkr.saas.manager.timer.TimerManager;
import com.intkr.saas.timer.callback.Processor;
import com.intkr.saas.util.claz.IOC;

/**
 * 定时器
 * 
 * @author Beiden
 * @date 2016-6-2 下午9:08:05
 * @version 1.0
 */
public class TimerEngine {

	protected static Logger logger = LoggerFactory.getLogger(TimerEngine.class);

	private final static AtomicBoolean isRun = new AtomicBoolean(false);

	private static TimerManager timerManager = IOC.get(TimerManager.class);

	private static Timer timer = new Timer();

	static {
		run();
	}

	public static void run() {
		if (isRun.compareAndSet(false, true)) {
			Date time = getMinuteLastTime();
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					try {
						processTimer();
					} catch (Exception e) {
						logger.error("", e);
					}
				}
			}, time, 1000 * 60 * 1);// 每分钟执行一次
		}
	}

	private static Date getMinuteLastTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.MINUTE, 1); // 一分钟后执行
		Date time = calendar.getTime();
		return time;
	}

	private static void processTimer() {
		List<TimerBO> timerList = timerManager.getLastMinute();
		for (final TimerBO timerData : timerList) {
			try {
				scheduleNow(timerData);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}

	public static void schedule(Date time, String action, String code) {
		if (time.before(new Date())) {
			throw new RuntimeException("error schedule time:newDate=" + new Date() + ";time=" + time);
		}
		TimerBO timer = new TimerBO();
		timer.setCode(code);
		timer.setAction(action);
		TimerBO oldTimer = timerManager.selectOne(timer);
		if (oldTimer == null) {
			timer.setId(timerManager.getId());
			timer.setStatus(TimerStatus.WaitRun.getCode());
			timer.setTime(time);
			timerManager.insert(timer);
			scheduleNow(timer);
		} else if (TimerStatus.WaitRun.getCode().equals(oldTimer.getStatus())) {
			oldTimer.setTime(time);
			timerManager.update(oldTimer);
			scheduleNow(oldTimer);
		} else if (TimerStatus.Finished.getCode().equals(oldTimer.getStatus())) {
			oldTimer.setTime(time);
			oldTimer.setStatus(TimerStatus.WaitRun.getCode());
			timerManager.update(oldTimer);
			scheduleNow(oldTimer);
		} else if (TimerStatus.Running.getCode().equals(oldTimer.getStatus())) {
			oldTimer.setId(timerManager.getId());
			oldTimer.setTime(time);
			oldTimer.setStatus(TimerStatus.WaitRun.getCode());
			timerManager.insert(oldTimer);
			scheduleNow(oldTimer);
		}
	}

	private static void scheduleNow(final TimerBO timerData) {
		Date minuteLaterTime = getMinuteLastTime();
		if (timerData.getTime().after(minuteLaterTime)) {
			return;
		}
		Date newTime = new Date();
		Long time = timerData.getTime().getTime() - newTime.getTime();
		if (time < 0) {
			time = 1000l;
		}
		timer.schedule(new TimerTask() {
			public void run() {
				try {
					TimerBO timerDataNew = timerManager.get(timerData.getId());
					Processor processor = IOC.get(timerDataNew.getAction());
					processor.superProcess(timerDataNew);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}, time);
	}

}
