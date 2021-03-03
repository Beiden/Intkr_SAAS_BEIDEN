package com.intkr.saas.module.action.conf;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.conf.IdManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.domain.bo.conf.IkIdBO;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 上午9:10:56
 * @version 1.0
 */
public class IdAction extends BaseAction<IkIdBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private IdManager ikIdManager = IOC.get(IdManager.class);

	public IkIdBO getBO(HttpServletRequest request) {
		IkIdBO ikIdBO = getParameter(request);
		return ikIdBO;
	}

	public static IkIdBO getParameter(HttpServletRequest request) {
		IkIdBO ikIdBO = new IkIdBO();
		ikIdBO.setId(RequestUtil.getParam(request, "id", Long.class));
		ikIdBO.setCode(RequestUtil.getParam(request, "code", Long.class));
		PagerUtil.initPage(request, ikIdBO);
		return ikIdBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return ikIdManager;
	}

}
