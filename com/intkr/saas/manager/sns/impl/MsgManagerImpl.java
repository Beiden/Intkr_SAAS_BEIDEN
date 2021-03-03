package com.intkr.saas.manager.sns.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.sns.MsgDAO;
import com.intkr.saas.domain.Transfer;
import com.intkr.saas.domain.bo.sns.ContactBO;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.dbo.sns.ContactDO;
import com.intkr.saas.domain.dbo.sns.MsgDO;
import com.intkr.saas.domain.type.sns.MsgChannel;
import com.intkr.saas.domain.type.sns.MsgStatus;
import com.intkr.saas.domain.type.sns.MsgType;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.manager.user.UserManager;

/**
 * 
 * @author Beiden
 * @date 2011-7-7 上午9:05:38
 * @version 1.0
 */
@Repository("MsgManager")
public class MsgManagerImpl extends BaseManagerImpl<MsgBO, MsgDO> implements MsgManager {

	@Resource
	private MsgDAO messageDAO;

	@Resource
	private UserManager accountManager;

	public BaseDAO<MsgDO> getBaseDAO() {
		return messageDAO;
	}

	public ContactBO fillLastMessage(ContactBO contact) {
		if (contact == null || contact.getUserId() == null || contact.getContactId() == null) {
			return contact;
		}
		MsgBO query = new MsgBO();
		query.setQuery("userId", contact.getUserId());
		query.setQuery("contactId", contact.getContactId());
		query.set_pageSize(1);
		List<MsgBO> messageList = select(query);
		if (!messageList.isEmpty()) {
			MsgBO message = messageList.get(0);
			contact.addMessage(message);
		}
		return contact;
	}

	public List<Object> fillLastMessage(List<Object> contactList) {
		if (contactList == null) {
			return new ArrayList<Object>();
		}
		for (Object bo : contactList) {
			if (bo instanceof ContactBO) {
				fillLastMessage((ContactBO) bo);
			}
		}
		return contactList;
	}

	public int readMessages(ContactBO contact) {
		if (contact == null) {
			return 0;
		}
		ContactDO dto = Transfer.toDOByField(contact);
		return messageDAO.readMessages(dto);
	}

	public void sendSysMsg(Long toUserId, String title, String content) {
		MsgBO msg = new MsgBO();
		msg.setId(getId());
		UserBO account = accountManager.get(toUserId);
		msg.setSaasId(account.getSaasId());
		msg.setType(MsgType.System.getCode());
		msg.setChannel(MsgChannel.System.getCode());
		msg.setToUserId(toUserId);
		msg.setIsToUserRead(2);
		msg.setTitle(title);
		msg.setContent(content);
		msg.setStatus(MsgStatus.Sended.getCode());
		insert(msg);
	}

	public void sendSaasMsg(Long saasId, String title, String content) {
		MsgBO msg = new MsgBO();
		msg.setId(getId());
		msg.setSaasId(saasId);
		msg.setType(MsgType.System.getCode());
		msg.setChannel(MsgChannel.Saas.getCode());
		msg.setIsToUserRead(2);
		msg.setTitle(title);
		msg.setContent(content);
		msg.setStatus(MsgStatus.Sended.getCode());
		insert(msg);
	}

}
