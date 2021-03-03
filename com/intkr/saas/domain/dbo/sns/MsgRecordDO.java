package com.intkr.saas.domain.dbo.sns;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2016-3-22 上午11:24:36
 * @version 1.0
 */
public class MsgRecordDO extends BaseDO {

	private String content;

	private String toPhone;

	private Long relatedId;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getToPhone() {
		return toPhone;
	}

	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}

	public Long getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}

}
