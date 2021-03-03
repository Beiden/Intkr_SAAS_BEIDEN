package com.intkr.saas.client.log;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.conf.OptionBO;
import com.intkr.saas.domain.bo.log.UserLogBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.log.UserLogManager;
import com.intkr.saas.util.IpUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-7-5 下午10:14:20
 * @version 1.0
 */
public class UserLogClient {

	protected static final Logger logger = LoggerFactory.getLogger(UserLogClient.class);

	private static UserLogManager userLogManager = IOC.get(UserLogManager.class);

	public static void log(HttpServletRequest request, String type, String obj) {
		OptionManager optionManager = IOC.get(OptionManager.class);
		OptionBO option = optionManager.getByName(SessionClient.getSaasId(request), "user_log");
		if (option == null || !"1".equals(option.getValue())) {
			return;
		}
		try {
			UserBO account = SessionClient.getLoginUser(request);
			UserLogBO log = new UserLogBO();
			log.setId(userLogManager.getId());
			log.setTime(new Date());
			if (account != null) {
				log.setUserId(account.getId());
				log.setStaffId(account.getId());
			}
			log.setSaasId(SessionClient.getSaasId(request));
			log.setStaffName(SessionClient.getLoginUser(request).getNickName());
			log.setType(type);
			log.setObj(obj);
			log.setNote(log.getStaffName() + log.getType() + log.getObj());
			log.setIp(IpUtil.getIp(request));
			userLogManager.insertAsyn(log);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public static void log(HttpServletRequest request, String type, String obj, Long id) {
		OptionManager optionManager = IOC.get(OptionManager.class);
		OptionBO option = optionManager.getByName(SessionClient.getSaasId(request), "user_log");
		if (option == null || !"1".equals(option.getValue())) {
			return;
		}
		try {
			Long userId = SessionClient.getLoginUserId(request);
			UserLogBO log = new UserLogBO();
			log.setSaasId(SessionClient.getSaasId(request));
			log.setId(userLogManager.getId());
			log.setTime(new Date());
			log.setUserId(userId);
			log.setStaffId(userId);
			log.setStaffName(SessionClient.getLoginUser(request).getNickName());
			log.setType(type);
			log.setObj(obj);
			log.setNote(log.getStaffName() + log.getType() + log.getObj());
			log.setIp(IpUtil.getIp(request));
			log.setFeature("relatedId", id);
			userLogManager.insertAsyn(log);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
