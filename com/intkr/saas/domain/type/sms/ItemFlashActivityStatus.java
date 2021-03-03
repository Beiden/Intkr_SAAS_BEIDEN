package com.intkr.saas.domain.type.sms;

/**
 * 秒杀活动状态
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:45
 * @version 1.0
 */
public enum ItemFlashActivityStatus {

	Wait("wait", "未开始"), //
	Ongoing("ongoing", "进行中"), //
	Ended("ended", "已结束"), //
	Error("error", "异常"); //

	private ItemFlashActivityStatus(String code, String name) {
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

	public static ItemFlashActivityStatus getByCode(String code) {
		ItemFlashActivityStatus[] values = values();
		for (ItemFlashActivityStatus value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
