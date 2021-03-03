package com.intkr.saas.domain.bo.sns;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.user.UserBO;

/**
 * 
 * @author Beiden
 * @date 2016-4-24 下午3:31:19
 * @version 1.0
 */
public class MsgBO extends BaseBO {

	private Long saasId;

	// 消息渠道：MsgChannel
	private String channel;

	// 消息类型：MsgType
	private String type;

	// 发送人
	private Long fromUserId;

	// 接收人
	private Long toUserId;

	// 接收手机
	private String toPhone;

	// 接受邮箱
	private String toEmail;

	// 接收微信
	private String toWeixin;

	// 是否已读
	private Integer isToUserRead;

	// 状态：MsgStatus
	private String status;

	// 标题
	private String title;

	// 内容
	private String content;

	private UserBO fromUser;

	private UserBO toUser;

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

	public UserBO getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserBO fromUser) {
		this.fromUser = fromUser;
	}

	public UserBO getToUser() {
		return toUser;
	}

	public void setToUser(UserBO toUser) {
		this.toUser = toUser;
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
