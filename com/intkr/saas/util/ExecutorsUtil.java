package com.intkr.saas.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author beidenhuang
 */
public class ExecutorsUtil {

	public static volatile ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

	public static volatile ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 2);

	public static void execute(Runnable command) {
		threadPool.execute(command);
	}

	public static void schedule(Runnable command, long delay, TimeUnit unit) {
		scheduledExecutor.schedule(command, delay, unit);
	}

}
