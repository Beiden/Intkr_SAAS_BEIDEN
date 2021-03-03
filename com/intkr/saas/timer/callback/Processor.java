package com.intkr.saas.timer.callback;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.TimerClient;
import com.intkr.saas.domain.bo.timer.TimerBO;
import com.intkr.saas.domain.type.timer.TimerStatus;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2018-9-21
 * @version 1.0
 */
public abstract class Processor {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private TimerClient timerClient = IOC.get(TimerClient.class);

	public void superProcess(TimerBO timer) {
		if (!TimerStatus.WaitRun.getCode().equals(timer.getStatus())) {
			return;
		}
		timer.setStatus(TimerStatus.Running.getCode());
		timerClient.update(timer);
		try {
			process(timer);
			timer.setActionTime(new Date());
			timer.setStatus(TimerStatus.Finished.getCode());
			timerClient.update(timer);
		} catch (Exception e) {
			logger.error("", e);
			timer.setActionTime(new Date());
			timer.setStatus(TimerStatus.Error.getCode());
			timerClient.update(timer);
		}
	}

	public abstract void process(TimerBO timer);

}
