package com.intkr.saas.facade.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-12 下午9:03:01
 * @version 1.0
 */
public class EmailMsgEngine {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final OptionManager optionManager = IOC.get("OptionManager");

	public static void send(final Long saasId, final String email, final String subject, final String htmlContent) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				SendObj sendObj = new SendObj();
//				sendObj.setUsername(optionManager.getAdminEmail(saasId, appId));
//				sendObj.setPassword(optionManager.getAdminEmailPassword(saasId, appId));
				sendObj.setTo(email);
				sendObj.setSubject(subject);
				sendObj.setContent(htmlContent);
				new Mail(sendObj).send();
			}
		});
		t.start();
	}

}
