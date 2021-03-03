package com.intkr.saas.module.toolbox.log;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.module.toolbox.BaseToolBox;

/**
 * 
 * @author Beiden
 * @date 2016-3-29 上午10:44:06
 * @version 1.0
 */
public class UserLogDS extends BaseToolBox {

	public void log(HttpServletRequest request, String type, String obj) {
		if (request == null) {
			return;
		}
		if (type == null) {
			type = "";
		}
		if (obj == null) {
			obj = "";
		}
		UserLogClient.log(request, type, obj);
	}

}
