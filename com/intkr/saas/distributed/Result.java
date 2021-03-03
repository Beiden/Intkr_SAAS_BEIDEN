package com.intkr.saas.distributed;

import java.io.Serializable;

import com.intkr.saas.util.JsonUtil;

/**
 * 
 * @author Beiden
 * @date 2017-10-23
 * @version 1.0
 */
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 接口调用是否成功
	 */
	private Boolean result = true;

	/**
	 * 返回码
	 */
	private String code;

	/**
	 * 接口返回的信息
	 */
	private String msg;

	private T data;

	public Result() {
	}

	public Result(T t) {
		data = t;
	}

	public Boolean getResult() {
		return result;
	}

	public Boolean result() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String toString() {
		return JsonUtil.toJson(this);
	}

}
