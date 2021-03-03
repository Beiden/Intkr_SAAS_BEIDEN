package com.intkr.saas.util.poi;

public class ColumnBO {

	private Object value;

	public Object getValue() {
		return value;
	}

	public String getString() {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ColumnBO(Object value) {
		this.value = value;
	}

	public ColumnBO() {
	}

}
