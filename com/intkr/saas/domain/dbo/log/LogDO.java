package com.intkr.saas.domain.dbo.log;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2014-7-4 下午10:07:58
 * @version 1.0
 */
public class LogDO extends BaseDO {
	
	private Long saasId;

	// 日志分组
	private String groupName;

	// 日志内容
	private String content;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
