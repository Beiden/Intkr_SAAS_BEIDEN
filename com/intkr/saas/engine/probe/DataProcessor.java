package com.intkr.saas.engine.probe;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2017-9-26 下午3:02:44
 * 
 */
public class DataProcessor {

	public static DataStater dataStater = new DataStater();

	static {
		// Timer timer = new Timer();
		// timer.schedule(new TimerTask() {
		// public void run() {
		// System.out.println("定时清数据");
		// }
		// }, 100, 1000 * 60);
	}

	public static void process(CallChain callChain) {
		if (!ProbeEngine.running) {
			return;
		}
		dataStater.process(callChain);
	}

}
