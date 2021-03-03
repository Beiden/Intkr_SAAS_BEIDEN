package com.intkr.saas.domain.bo.sns;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.user.UserBO;

/**
 * 
 * @author Beiden
 * @date 2011-7-8 上午10:19:19
 * @version 1.0
 */
public class ContactBO extends BaseBO {

	private Long saasId;

	private Long userId;

	private UserBO user;

	private Long contactId;

	private UserBO contact;

	private Date lastContactTime;

	private List<MsgBO> messageList;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public Date getLastContactTime() {
		return lastContactTime;
	}

	public void setLastContactTime(Date lastContactTime) {
		this.lastContactTime = lastContactTime;
	}

	public UserBO getContact() {
		return contact;
	}

	public void setContact(UserBO contact) {
		this.contact = contact;
	}

	public List<MsgBO> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<MsgBO> messageList) {
		this.messageList = messageList;
	}

	public void addMessage(MsgBO message) {
		if (this.messageList == null) {
			this.messageList = new ArrayList<MsgBO>();
		}
		this.messageList.add(message);
	}

	public UserBO getUser() {
		return user;
	}

	public void setUser(UserBO user) {
		this.user = user;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
