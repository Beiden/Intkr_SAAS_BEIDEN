package com.intkr.saas.domain.type.sys;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:45
 * @version 1.0
 */
public enum TimerStatus {

	WaitRun("waitRun", "待执行"), //
	Running("running", "执行中"), //
	Finished("finished", "已完成 "), //
	Error("error", "异常"); //

	private TimerStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	private String code;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static TimerStatus getByCode(String code) {
		TimerStatus[] values = values();
		for (TimerStatus value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
