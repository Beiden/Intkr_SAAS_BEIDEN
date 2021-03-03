package com.intkr.saas.module.toolbox.sns;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.sns.ContactBO;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.manager.sns.ContactManager;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.sns.ContactAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class ContactDS extends BaseToolBox {

	private MsgManager messageManager = IOC.get(MsgManager.class);

	private ContactManager contactManager = IOC.get(ContactManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	public ContactBO select(HttpServletRequest request) {
		try {
			Long userId = SessionClient.getLoginUserId(request);
			ContactBO query = ContactAction.getParameter(request);
			query.setQuery("orderBy", "last_contact_time");
			query.setQuery("order", "desc");
			query.setUserId(userId);
			query = contactManager.selectAndCount(query);
			query.setDatas(userManager.fill(query.getDatas()));
			query.setDatas(messageManager.fillLastMessage(query.getDatas()));
			return query;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	public MsgBO getByToId(Object userIdOBj, Object toUserIdObj) {
		Long toUserId = null;
		if (toUserIdObj instanceof String) {
			toUserId = Long.valueOf((String) toUserIdObj);
		} else if (toUserIdObj instanceof Long) {
			toUserId = (Long) toUserIdObj;
		}
		Long userId = null;
		if (userIdOBj instanceof String) {
			userId = Long.valueOf((String) userIdOBj);
		} else if (userIdOBj instanceof Long) {
			userId = (Long) userIdOBj;
		}
		UserBO toUser = userManager.get(toUserId);
		ContactBO contact = contactManager.get(userId, toUserId);
		messageManager.readMessages(contact);
		MsgBO query = new MsgBO();
		query.setQuery("userId", userId);
		query.setQuery("contactId", toUserId);
		query = messageManager.selectAndCount(query);
		query.setDatas(userManager.fill(query.getDatas()));
		Collections.reverse(query.getDatas());
		query.setProperty("toUser", toUser);
		return query;
	}

}
