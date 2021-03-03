package com.intkr.saas.module.action.sns;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.sns.MsgChannel;
import com.intkr.saas.domain.type.sns.MsgType;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-24 上午9:53:19
 * @version 1.0
 */
public class SysMsgAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static MsgManager messageManager = IOC.get(MsgManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	public void doSend(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String title = RequestUtil.getParam(request, "title");
		String content = RequestUtil.getParam(request, "content");
		UserBO query = new UserBO();
		userManager.selectAndCount(query);
		while (query.getCurrentPage() <= query.getAllPage()) {
			for (Object obj : query.getDatas()) {
				UserBO user = (UserBO) obj;
				send(user.getId(), title, content);
			}
			query.set_page(query.getCurrentPage() + 1);
			userManager.selectAndCount(query);
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "发送成功");
	}

	public static void send(Long userId, String title, String content) {
		MsgBO messageBO = new MsgBO();
		messageBO.setId(messageManager.getId());
		messageBO.setType(MsgType.System.getCode());
		messageBO.setChannel(MsgChannel.System.getCode());
		messageBO.setToUserId(userId);
		messageBO.setIsToUserRead(2);
		messageBO.setTitle(title);
		messageBO.setContent(content);
		messageManager.insert(messageBO);
	}

}
