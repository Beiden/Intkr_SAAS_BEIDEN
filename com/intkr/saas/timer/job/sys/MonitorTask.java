package com.intkr.saas.timer.job.sys;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sys.MonitorBO;
import com.intkr.saas.engine.SmsEngine;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.saas.SaasClientManager;
import com.intkr.saas.manager.sys.MonitorManager;
import com.intkr.saas.util.HttpClientUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2013-7-25 下午11:53:23
 * @version 1.0
 */
public class MonitorTask {

	protected final Logger logger = LoggerFactory.getLogger(MonitorTask.class);

	public static final String monitorUrl = "/admin/sign/monitor.html";

	public void run() {
		OptionManager optionManager = IOC.get(OptionManager.class);
		if (!optionManager.taskOpen()) {
			return;
		}
		logger.warn("start");
		if (optionManager.domainMonitorOpen()) {
			monitorDomain();
		}
		if (optionManager.dbMonitorOpen()) {
			monitorDatabase();
		}
		// deleteData();
		logger.warn("end");
	}

	private void deleteData() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, -7);
		MonitorManager monitorManager = IOC.get(MonitorManager.class);
		monitorManager.deleteReal(c.getTime());
	}

	private void monitorDomain() {
		SaasClientManager saasClientManager = IOC.get(SaasClientManager.class);
		try {
			String url = "http://" + saasClientManager.getSaasDomain(1l) + monitorUrl;
			String text = HttpClientUtil.getText(url);
			if ("result:ok".equals(text)) {
				log("Domain-Monitor", "success");
			} else {
				processError("Domain-Monitor");
			}
		} catch (Exception e) {
			processError("Domain-Monitor");
		}
	}

	private void processError(String type) {
		log(type, "fail");
		if (needWarning(type)) {
			String error = type;
			send(error);
		}
	}

	// 超过5次有问题才告警
	private boolean needWarning(String type) {
		MonitorManager monitorManager = IOC.get(MonitorManager.class);
		MonitorBO query = new MonitorBO();
		query.setType(type);
		query.set_pageSize(5);
		List<MonitorBO> monitorList = monitorManager.select(query);
		boolean isAllFail = true;
		if (monitorList.size() == 5) {
			for (MonitorBO m : monitorList) {
				if ("success".equals(m.getResult())) {
					isAllFail = false;
					break;
				}
			}
		}
		return isAllFail;
	}

	private void log(String type, String result) {
		MonitorManager monitorManager = IOC.get(MonitorManager.class);
		MonitorBO monitor = new MonitorBO();
		monitor.setId(monitorManager.getId());
		monitor.setType(type);
		monitor.setResult(result);
		monitorManager.insert(monitor);
	}

	private void monitorDatabase() {
		MonitorManager monitorManager = IOC.get(MonitorManager.class);
		try {
			monitorManager.count(null);
			MonitorBO monitor = new MonitorBO();
			monitor.setId(monitorManager.getId());
			monitor.setType("Database monitor");
			monitor.setResult("success");
			monitorManager.insert(monitor);
		} catch (Exception e) {
			String error = "数据库查询出错:" + e.toString();
			send(error);
		}
	}

	public void send(final String error) {
		OptionManager optionManager = IOC.get(OptionManager.class);
		String email = optionManager.getAdminEmail(-1l);
		String phone = optionManager.getAdminPhone(-1l);
		String subject = "[" + optionManager.getWebsiteTitle(1L) + "]-网站监控";
		String content = "网站监控告警：" + error;
		// EmailMsgEngine.send(1L, email, subject, content);
		SmsEngine.send(-1L, phone, content);
	}

}