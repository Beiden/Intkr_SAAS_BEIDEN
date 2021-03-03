package com.intkr.saas.client.log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.manager.log.SysLogManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2017-8-16 上午11:32:40
 * @version 1.0
 */
public class SysLogClient {

	private static SysLogManager sysLogManager = IOC.get(SysLogManager.class);

	public static ExecutorService threadPool = Executors.newSingleThreadExecutor();

	public static boolean isIgnore(HttpServletRequest request) {
		if (request.getParameter("action") == null) {
			return false;
		}
		if (request.getParameter("action").endsWith("/SysLogAction")) {
			return true;
		}
		return false;
	}

	public static Long getId() {
		return sysLogManager.getId();
	}

}
