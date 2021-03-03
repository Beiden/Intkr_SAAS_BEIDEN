package com.intkr.saas.engine.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * 
 * @author Beiden
 * @date 2017-11-23
 * @version 1.0
 */
public class ThreadPoolEngine {

	public final static Map<String, ThreadPool> threadPoolMap = new HashMap<String, ThreadPool>();

	public static ExecutorService getExecutor(String threadPollName) {
		return threadPoolMap.get(threadPollName).getExecutorService();
	}

	public static ExecutorService getExecutor() {
		if (!threadPoolMap.containsKey("default")) {
			new ThreadPool();
		}
		return getExecutor("default");
	}

}
