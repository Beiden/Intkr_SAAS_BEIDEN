package com.intkr.saas.util;

/**
 * 业务层报的业务错误，会直接返回用用户。不记录error记录，只记录info日志
 * 
 * @author Beiden
 */
public class ViewException extends RuntimeException {

	private Boolean result;

	private String msg;

	private Object data;

	public ViewException(Boolean result, String msg) {
		this.result = result;
		this.msg = msg;
	}

	public ViewException(Boolean result, String msg, Object data) {
		this.result = result;
		this.msg = msg;
		this.data = data;
	}

}
