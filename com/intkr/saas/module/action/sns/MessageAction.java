package com.intkr.saas.module.action.sns;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.sns.ContactBO;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.sns.ContactManager;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2015-7-9 上午10:20:48
 * @version 1.0
 */
public class MessageAction extends BaseAction<MsgBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ContactManager contactManager = IOC.get(ContactManager.class);

	private MsgManager messageManager = IOC.get(MsgManager.class);

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long saasId = SessionClient.getSaasId(request);
		MsgBO messageBO = MsgAction.getParameter(request);
		messageBO.setSaasId(SessionClient.getSaasId(request));
		messageBO.setId(messageManager.getId());
		UserBO userBO = SessionClient.getLoginUser(request);
		messageBO.setFromUserId(userBO.getId());
		messageBO.setIsToUserRead(2);
		messageManager.insert(messageBO);
		ContactBO contact = contactManager.get(userBO.getId(), messageBO.getToUserId());
		updateContact(saasId, userBO.getId(), messageBO.getToUserId(), contact);
		contact = contactManager.get(messageBO.getToUserId(), userBO.getId());
		updateContact(saasId, messageBO.getToUserId(), userBO.getId(), contact);
		request.setAttribute("msg", "发送成功！");
		request.setAttribute("result", true);
	}

	private void updateContact(Long saasId, Long userId, Long contantId, ContactBO contact) {
		if (contact == null) {
			contact = new ContactBO();
			contact.setId(contactManager.getId());
			contact.setSaasId(saasId);
			contact.setUserId(userId);
			contact.setContactId(contantId);
			contact.setLastContactTime(new Date());
			contactManager.insert(contact);
		} else {
			contact.setLastContactTime(new Date());
			contactManager.update(contact);
		}
	}

	public void doDeleteOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idString = RequestUtil.getParam(request, "id");
		deleteOne(request, response, idString);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response, String id) {
		MsgBO messageBO = messageManager.get(id);
		if (messageBO == null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "记录不存在");
			return;
		}
		UserBO userBO = SessionClient.getLoginUser(request);
		if (userBO.getId().equals(messageBO.getFromUserId())) {
			messageManager.delete(id);
			request.setAttribute("result", true);
			request.setAttribute("msg", "删除成功");
			return;
		}
		request.setAttribute("result", false);
		request.setAttribute("msg", "记录不存在");
	}

	public void deleteAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idsString = RequestUtil.getParam(request, "ids");
		String[] idsStrings = idsString.split(",");
		for (String idString : idsStrings) {
			deleteOne(request, response, idString);
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "删除成功");
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MsgBO messageBO = MsgAction.getParameter(request);
		messageBO.setId(RequestUtil.getParam(request, "id", Long.class));
		MsgBO oldMessageBO = messageManager.get(messageBO.getId());
		if (oldMessageBO == null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "记录不存在");
			return;
		}
		UserBO userBO = SessionClient.getLoginUser(request);
		if (userBO.getId().equals(messageBO.getFromUserId())) {
			messageManager.update(messageBO);
			request.setAttribute("result", true);
			request.setAttribute("msg", "更新成功");
			return;
		}
		request.setAttribute("result", false);
		request.setAttribute("msg", "记录不存在");
	}

	public MsgBO getBO(HttpServletRequest request) {
		MsgBO messageBO = MsgAction.getParameter(request);
		return messageBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return messageManager;
	}

}
