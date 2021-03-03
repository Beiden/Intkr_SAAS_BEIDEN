package com.intkr.saas.domain.type;

import java.util.HashMap;
import java.util.Map;

public enum BaseEnum {

	Error("error", "异常"); //

	private BaseEnum(String code, String name) {
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

	public BaseEnum getByCode(String code) {
		BaseEnum[] values = values();
		for (BaseEnum value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		BaseEnum[] values = values();
		for (BaseEnum value : values) {
			map.put(value.code, value.name);
		}
		return map;
	}

}
