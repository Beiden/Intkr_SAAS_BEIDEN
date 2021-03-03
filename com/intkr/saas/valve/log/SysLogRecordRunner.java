package com.intkr.saas.valve.log;

import com.intkr.saas.domain.bo.conf.OptionBO;
import com.intkr.saas.domain.bo.log.SysLogBO;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.log.SysLogManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 */
public class SysLogRecordRunner implements Runnable {

	private SysLogBO viewRecord;

	public SysLogRecordRunner(SysLogBO viewRecord) {
		this.viewRecord = viewRecord;
	}

	public void run() {
		OptionManager optionManager = IOC.get(OptionManager.class);
		OptionBO option = optionManager.getByName(viewRecord.getSaasId(), "system_log");
		if (option != null && "1".equals(option.getValue())) {
			SysLogManager manager = IOC.get(SysLogManager.class);
			manager.insert(viewRecord);
		}
	}

}
