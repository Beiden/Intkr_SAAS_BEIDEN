package com.intkr.saas.module.action.sns;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2015-7-7 上午11:37:16
 * @version 1.0
 */
public class MyMessageAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgManager messageManager = IOC.get(MsgManager.class);

	public void doAdd(HttpServletRequest request, HttpServletResponse response) {
		MsgBO messageBO = MsgAction.getParameter(request);
		messageManager.insert(messageBO);
		request.setAttribute("result", true);
		request.setAttribute("msg", "插入成功");
	}

	public void doDeleteOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idString = RequestUtil.getParam(request, "id");
		MsgBO messageBO = messageManager.get(idString);
		if (messageBO == null) {
			request.setAttribute("result", true);
			request.setAttribute("msg", "记录不存在");
			return;
		}
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MsgBO messageBO = MsgAction.getParameter(request);
		if (messageBO.getContent() == null || "".equals(messageBO.getContent())) {
			messageBO.setContent("-1");
		}
		messageBO.setId(RequestUtil.getParam(request, "id", Long.class));
		MsgBO oldMessageBO = messageManager.get(RequestUtil.getParam(request, "id"));
		if (oldMessageBO == null) {
			request.setAttribute("result", false);
			request.setAttribute("ms", "商品不存在");
			return;
		}
	}

}
