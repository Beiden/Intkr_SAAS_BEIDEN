package com.intkr.saas.module.action.sys;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sns.SysImgBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.sns.SysImgManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @author Beiden
 * @date 2019-10-12 上午10:18:22
 * @version 1.0.0
 */
public class SysImgAction extends BaseAction<SysImgBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SysImgManager sysImgManager = IOC.get(SysImgManager.class);

	public SysImgBO getBO(HttpServletRequest request) {
		SysImgBO sysImgBO = getParameter(request);
		return sysImgBO;
	}

	public static SysImgBO getParameter(HttpServletRequest request) {
		SysImgBO sysImgBO = new SysImgBO();
		sysImgBO.setId(RequestUtil.getParam(request, "id", Long.class));
		sysImgBO.setType(RequestUtil.getParam(request, "type"));
		sysImgBO.setUri(RequestUtil.getParam(request, "uri"));
		PagerUtil.initPage(request, sysImgBO);
		return sysImgBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return sysImgManager;
	}

}
