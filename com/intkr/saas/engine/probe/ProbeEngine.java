package com.intkr.saas.engine.probe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * probe
 * 
 * @author beidenhuang
 * @datetime 2017-9-26 下午3:02:53
 * 
 */
public class ProbeEngine {

	public static boolean running = true;

	static ExecutorService executorService = Executors.newFixedThreadPool(4);

	// 当前调用的方法
	static ThreadLocal<CallChain> callChainLocalCache = new ThreadLocal<CallChain>();

	public static void start() {
		running = true;
	}

	public static void stop() {
		running = false;
	}

	public static void clear() {
		DataProcessor.dataStater.getAndReset();
	}

	public static List<MethodStat> getStat(MethodStat query) {
		List<MethodStat> list = getStat((String) query.getQuery("order"), (String) query.getQuery("orderBy"));
		query.setDatas(list);
		query.set_count(list.size());
		return list;
	}

	public static List<MethodStat> getStat(String order, String orderBy) {
		Map<String, MethodStat> map = DataProcessor.dataStater.get();
		Collection<MethodStat> list = map.values();
		Object[] array = list.toArray();
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				MethodStat stat = (MethodStat) array[i];
				MethodStat statEnd = (MethodStat) array[j];
				if ("minTime".equalsIgnoreCase(order) && "asc".equalsIgnoreCase(orderBy)) {
					if (stat.getMinTime() > statEnd.getMinTime()) {
						array[j] = stat;
						array[i] = statEnd;
					}
				} else if ("minTime".equalsIgnoreCase(order)) {
					if (stat.getMinTime() < statEnd.getMinTime()) {
						array[j] = stat;
						array[i] = statEnd;
					}
				} else if ("count".equalsIgnoreCase(order) && "asc".equalsIgnoreCase(orderBy)) {
					if (stat.getCount() > statEnd.getCount()) {
						array[j] = stat;
						array[i] = statEnd;
					}
				} else if ("count".equalsIgnoreCase(order)) {
					if (stat.getCount() < statEnd.getCount()) {
						array[j] = stat;
						array[i] = statEnd;
					}
				} else if ("avgTime".equalsIgnoreCase(order) && "asc".equalsIgnoreCase(orderBy)) {
					if (stat.getAvgTime() > statEnd.getAvgTime()) {
						array[j] = stat;
						array[i] = statEnd;
					}
				} else if ("avgTime".equalsIgnoreCase(order)) {
					if (stat.getAvgTime() < statEnd.getAvgTime()) {
						array[j] = stat;
						array[i] = statEnd;
					}
				} else if ("maxTime".equalsIgnoreCase(order) && "asc".equalsIgnoreCase(orderBy)) {
					if (stat.getMaxTime() > statEnd.getMaxTime()) {
						array[j] = stat;
						array[i] = statEnd;
					}
				} else {
					if (stat.getMaxTime() < statEnd.getMaxTime()) {
						array[j] = stat;
						array[i] = statEnd;
					}
				}
			}
		}
		List<MethodStat> returnList = new ArrayList<MethodStat>();
		for (Object obj : array) {
			returnList.add((MethodStat) obj);
		}
		return returnList;
	}

	public static void start(CallChain callChain) {
		AopBefore.process(callChain);
	}

	public static void end() {
		AopAfter.process();
	}

}

class AopAfter {

	protected static Logger logger = LoggerFactory.getLogger(AopAfter.class);

	public static void process() {
		try {
			processReal();
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	private static void processReal() {
		if (ProbeEngine.callChainLocalCache.get() == null) {
			return;
		}
		CallChain callChain = ProbeEngine.callChainLocalCache.get();
		if (callChain.getPrev() != null) {
			ProbeEngine.callChainLocalCache.set(callChain.getPrev());
		}
		callChain.setEndTime(new Date().getTime());
		DataProcessor.process(callChain);
	}

}

class AopBefore {

	protected static Logger logger = LoggerFactory.getLogger(AopBefore.class);

	public static void process(CallChain callChain) {
		try {
			processReal(callChain);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	private static void processReal(CallChain callChain) {
		if (ProbeEngine.callChainLocalCache.get() == null) {
			ProbeEngine.callChainLocalCache.set(callChain);
		} else {
			callChain.setPrev(ProbeEngine.callChainLocalCache.get());
			ProbeEngine.callChainLocalCache.get().setNext(callChain);
			ProbeEngine.callChainLocalCache.set(callChain);
		}
	}

}
