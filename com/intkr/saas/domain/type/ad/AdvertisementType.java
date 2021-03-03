package com.intkr.saas.domain.type.ad;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:00
 * @version 1.0
 */
public enum AdvertisementType {

	Picture("picture", "图片"), //
	Text("text", "广告语"), //
	Error("error", "异常"); //

	private AdvertisementType(String code, String name) {
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
	
	public static AdvertisementType getByCode(String code) {
		AdvertisementType[] values = values();
		for (AdvertisementType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
