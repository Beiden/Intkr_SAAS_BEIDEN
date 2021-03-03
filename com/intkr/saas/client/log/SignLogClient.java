package com.intkr.saas.client.log;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.log.SignLogBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.facade.ip.IpClient;
import com.intkr.saas.manager.log.SignLogManager;
import com.intkr.saas.util.IpUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2017-8-16 上午11:32:40
 * @version 1.0
 */
public class SignLogClient {

	protected static final Logger logger = LoggerFactory.getLogger(SignLogClient.class);

	public static void log(HttpServletRequest request, UserBO user, String type) {
		try {
			SignLogManager signLogManager = IOC.get(SignLogManager.class);
			String ip = IpUtil.getIp(request);
			SignLogBO log = new SignLogBO();
			log.setId(signLogManager.getId());
			log.setType(type);
			log.setSaasId(user.getSaasId());
			log.setUserId(user.getId());
			log.setIp(ip);
			Runnable run = new Runner(log);
			SysLogClient.threadPool.execute(run);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	static class Runner implements Runnable {
		SignLogBO log;

		public Runner(SignLogBO log) {
			this.log = log;
		}

		public void run() {
			try {
				SignLogManager signLogManager = IOC.get("SignLogManager");
				IpClient ipClient = IOC.get("IpClient");
				IpClient.Response result = ipClient.getInfo(log.getIp());
				log.setCountry(result.country);
				log.setProvince(result.region);
				log.setCity(result.city);
				log.setIsp(result.isp);
				signLogManager.insert(log);
			} catch (Exception e) {
				logger.error("", e);
			}
		}

	}

}
