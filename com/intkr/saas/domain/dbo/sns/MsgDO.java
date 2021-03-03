package com.intkr.saas.domain.dbo.sns;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-24 下午3:31:10
 * @version 1.0
 */
public class MsgDO extends BaseDO {

	private Long saasId;

	private String channel;

	private String type;

	private Long fromUserId;

	private Long toUserId;

	private String toPhone;

	private String toEmail;

	private String toWeixin;

	private Integer isToUserRead;
	
	private String status;

	private String title;

	private String content;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public Integer getIsToUserRead() {
		return isToUserRead;
	}

	public void setIsToUserRead(Integer isToUserRead) {
		this.isToUserRead = isToUserRead;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getToPhone() {
		return toPhone;
	}

	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getToWeixin() {
		return toWeixin;
	}

	public void setToWeixin(String toWeixin) {
		this.toWeixin = toWeixin;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
