package com.intkr.saas.module.toolbox.sns;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.module.action.sns.MsgAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class MsgDS extends BaseToolBox {

	private MsgManager msgManager = IOC.get(MsgManager.class);

	/**
	 * 查询列表
	 */
	public MsgBO select(HttpServletRequest request) {
		try {
			Long userId = SessionClient.getLoginUserId(request);
			MsgBO query = MsgAction.getParameter(request);
			query.setToUserId(userId);
			msgManager.selectAndCount(query);
			return query;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	public MsgBO getById(Object idObj) {
		Long id = null;
		if (idObj instanceof String) {
			id = Long.valueOf((String) idObj);
		} else if (idObj instanceof Long) {
			id = (Long) idObj;
		}
		MsgBO message = msgManager.get(id);
		return message;
	}

	public MsgBO read(Object idObj) {
		Long id = null;
		if (idObj instanceof String) {
			id = Long.valueOf((String) idObj);
		} else if (idObj instanceof Long) {
			id = (Long) idObj;
		}
		MsgBO message = msgManager.get(id);
		if (message.getIsToUserRead() != 1) {
			message.setIsToUserRead(1);
			msgManager.update(message);
		}
		return message;
	}

}
