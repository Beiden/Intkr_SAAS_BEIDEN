package com.intkr.saas.domain.bo.timer;

import java.util.Date;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2016-6-2 下午8:50:13
 * @version 1.0
 */
public class TimerBO extends BaseBO {

	// 应用名称
	private String appName;

	// 执行class类
	private String action;

	// 执行状态
	private String status;

	// 计划执行时间
	private Date time;

	// code
	private String code;

	// 实际执行时间
	private Date actionTime;

	// 接收帐号ID
	private Long userId;

	// 备注
	private String note;

	//
	private String feature;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

}
