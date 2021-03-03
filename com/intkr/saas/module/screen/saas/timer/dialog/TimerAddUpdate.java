package com.intkr.saas.module.screen.saas.timer.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.timer.TimerBO;
import com.intkr.saas.manager.timer.TimerManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class TimerAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private TimerManager manager = IOC.get(TimerManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String saasProductId = RequestUtil.getParam(request, "saasProductId");
		TimerBO saasProduct = manager.get(saasProductId);
		request.setAttribute("saasProduct", saasProduct);
		request.setAttribute("addId", manager.getId());
	}

}
