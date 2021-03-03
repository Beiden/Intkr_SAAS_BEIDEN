package com.intkr.saas.engine.probe;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2017-9-26 下午3:15:38
 * 
 */
public class CallChain {

	private String method;

	private Object[] args;

	private Long startTime;

	private Long endTime;

	private CallChain prev;

	private CallChain next;

	public CallChain getPrev() {
		return prev;
	}

	public void setPrev(CallChain prev) {
		this.prev = prev;
	}

	public CallChain getNext() {
		return next;
	}

	public void setNext(CallChain next) {
		this.next = next;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

}
