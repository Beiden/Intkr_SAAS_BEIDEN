package com.intkr.saas.engine.probe;

import com.intkr.saas.domain.Pager;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2017-9-26 下午4:18:37
 * 
 */
public class MethodStat extends Pager {

	private String startTime;

	private String endTime;

	// 类型：近1分钟，近5分钟，近30分钟
	private int second;

	// 方法
	private String method;

	private Object[] args;

	// 调用次数
	private Integer count = 0;

	// 平均耗时
	private double avgTime = 0;

	// 最大耗时
	private long maxTime = 0;

	// 最小耗时
	private long minTime = Long.MAX_VALUE;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public double getAvgTime() {
		return avgTime;
	}

	public void setAvgTime(double avgTime) {
		this.avgTime = avgTime;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}

	public long getMinTime() {
		return minTime;
	}

	public void setMinTime(long minTime) {
		this.minTime = minTime;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

}
