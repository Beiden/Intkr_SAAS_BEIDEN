package com.intkr.saas.client;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.intkr.saas.domain.bo.timer.TimerBO;
import com.intkr.saas.engine.timer.TimerEngine;
import com.intkr.saas.manager.timer.TimerManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2018-9-20
 * @version 1.0
 */
@Repository("TimerClient")
public class TimerClient {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Boolean schedule(Date time, String action, String code) {
		TimerEngine.schedule(time, action, code);
		return true;
	}

	public Boolean schedule(TimerBO timerBO) {
		TimerEngine.schedule(timerBO.getTime(), timerBO.getAction(), timerBO.getCode());
		return true;
	}

	public void update(TimerBO timer) {
		TimerManager timerManager = IOC.get(TimerManager.class);
		timerManager.update(timer);
	}

}
