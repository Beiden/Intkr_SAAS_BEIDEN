package com.intkr.saas.module.action.sns;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.type.sns.MsgType;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 上午9:12:41
 * @version 1.0
 */
public class MsgAction extends BaseAction<MsgBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgManager messageManager = IOC.get(MsgManager.class);

	public MsgBO getBO(HttpServletRequest request) {
		MsgBO messageBO = getParameter(request);
		return messageBO;
	}

	public static MsgBO getParameter(HttpServletRequest request) {
		MsgBO messageBO = new MsgBO();
		messageBO.setId(RequestUtil.getParam(request, "id", Long.class));
		messageBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		messageBO.setChannel(RequestUtil.getParam(request, "channel"));
		messageBO.setType(RequestUtil.getParam(request, "type"));
		messageBO.setFromUserId(RequestUtil.getParam(request, "fromUserId", Long.class));
		messageBO.setToUserId(RequestUtil.getParam(request, "toUserId", Long.class));
		messageBO.setToPhone(RequestUtil.getParam(request, "toPhone"));
		messageBO.setToEmail(RequestUtil.getParam(request, "toEmail"));
		messageBO.setToWeixin(RequestUtil.getParam(request, "toWeixin"));
		messageBO.setIsToUserRead(RequestUtil.getParam(request, "isToUserRead", Integer.class));
		messageBO.setStatus(RequestUtil.getParam(request, "status"));
		messageBO.setTitle(RequestUtil.getParam(request, "title"));
		messageBO.setContent(RequestUtil.getParam(request, "content"));
		PagerUtil.initPage(request, messageBO);
		return messageBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return messageManager;
	}

	public void doGetUnReadMsgCount(HttpServletRequest request, HttpServletResponse response) {
		getUnReadMsgCount(request, response);
	}

	public void getUnReadMsgCount(HttpServletRequest request, HttpServletResponse response) {
		Long unReadChatMsgCount = 0l;
		Long unReadMsgCount = 0l;
		Long unReadSysMsgCount = 0l;
		if (SessionClient.isLogin(request)) {
			MsgBO query = new MsgBO();
			query.setToUserId(SessionClient.getLoginUserId(request));
			query.setIsToUserRead(2);
			query.setType(MsgType.System.getCode());
			unReadSysMsgCount = messageManager.count(query);
			query.setType(MsgType.Chat.getCode());
			unReadChatMsgCount = messageManager.count(query);
			unReadMsgCount = unReadChatMsgCount + unReadSysMsgCount;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("unReadSysMsgCount", unReadSysMsgCount + "");
		map.put("unReadChatMsgCount", unReadChatMsgCount + "");
		map.put("unReadMsgCount", unReadMsgCount + "");
		request.setAttribute("data", map);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功！");
	}

	public void doRead(HttpServletRequest request, HttpServletResponse response) {
		Long msgId = RequestUtil.getParam(request, "msgId", Long.class);
		MsgBO msg = messageManager.get(msgId);
		msg.setIsToUserRead(1);
		messageManager.update(msg);
		request.setAttribute("result", true);
		request.setAttribute("msg", "阅读成功！");
	}

}
