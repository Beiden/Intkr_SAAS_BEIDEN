package com.intkr.saas.engine.probe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 统计器
 * 
 * @author beidenhuang
 * @datetime 2017-9-27 下午7:51:20
 * 
 */
public class DataStater {

	private Map<String, MethodStat> methodStatMap = new ConcurrentHashMap<String, MethodStat>();

	public void process(CallChain callChain) {
		Run run = new Run(this, callChain);
		ProbeEngine.executorService.execute(run);
	}

	void processReal(CallChain callChain) {
		if (!methodStatMap.containsKey(callChain.getMethod())) {
			MethodStat methodStat = new MethodStat();
			methodStat.setMethod(callChain.getMethod());
			if (!methodStatMap.containsKey(callChain.getMethod())) {
				methodStatMap.put(callChain.getMethod(), methodStat);
			}
		}
		MethodStat stat = methodStatMap.get(callChain.getMethod());
		synchronized (stat) {
			Long time = callChain.getEndTime() - callChain.getStartTime();
			stat.setCount(stat.getCount() + 1);
			if (stat.getMaxTime() < time) {
				stat.setMaxTime(time);
				stat.setArgs(callChain.getArgs());
			}
			if (stat.getMinTime() > time) {
				stat.setMinTime(time);
			}
			stat.setAvgTime((stat.getAvgTime() * (stat.getCount() - 1) + time) / stat.getCount());
		}
	}

	public Map<String, MethodStat> get() {
		Map<String, MethodStat> methodStat = this.methodStatMap;
		return methodStat;
	}

	public Map<String, MethodStat> getAndReset() {
		Map<String, MethodStat> methodStat = this.methodStatMap;
		this.methodStatMap = new ConcurrentHashMap<String, MethodStat>();
		return methodStat;
	}

}

class Run implements Runnable {

	DataStater dataStater;

	CallChain callChain;

	public Run(DataStater dataStater, CallChain callChain) {
		this.dataStater = dataStater;
		this.callChain = callChain;
	}

	public void run() {
		dataStater.processReal(callChain);
	}

}
