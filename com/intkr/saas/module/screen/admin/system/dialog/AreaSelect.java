package com.intkr.saas.module.screen.admin.system.dialog;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.conf.AreaManager;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.domain.bo.conf.AreaBO;

/**
 * 
 * @author Beiden
 * @date 2016-5-16 下午3:26:41
 * @version 1.0
 */
public class AreaSelect {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AreaManager areaDataManager = IOC.get(AreaManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AreaBO data = areaDataManager.getFull();
		if (data != null) {
			request.setAttribute("areaList", data.getChilds());
		}
	}

}