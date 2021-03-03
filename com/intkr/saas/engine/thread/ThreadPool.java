package com.intkr.saas.engine.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author Beiden
 * @date 2017-11-23
 * @version 1.0
 */
public class ThreadPool {

	private String name;

	private Integer size;

	private ExecutorService executorService;

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public ThreadPool() {
		new ThreadPool("default", Runtime.getRuntime().availableProcessors() + 1);
	}

	public ThreadPool(String name, Integer poolSize) {
		if (ThreadPoolEngine.threadPoolMap.containsKey(name)) {
			throw new RuntimeException("ThreadPool : [" + name + "] aready exist !");
		}
		this.name = name;
		this.size = poolSize;
		executorService = Executors.newFixedThreadPool(poolSize);
		ThreadPoolEngine.threadPoolMap.put(name, this);
	}

}
