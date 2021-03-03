package com.intkr.saas.engine.auth;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.intkr.saas.domain.bo.user.RightBO;
import com.intkr.saas.domain.type.user.RightType;
import com.intkr.saas.engine.UrlRewriteEngine;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.user.RightManager;
import com.intkr.saas.util.ExecutorsUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 权限引擎
 * 
 * @author Beiden
 * @date 2016-5-24 下午2:55:31
 * @version 1.0
 */
public class RightCollectEngine {

	public static void collectRight(final HttpServletRequest request) {
		OptionManager optionManager = IOC.get(OptionManager.class);
		if (!optionManager.isCollectRight()) {
			return;
		}
		TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
		final String uri = UrlRewriteEngine.getRealTarget(request);
		final String host = request.getHeader("host");
		if (uri.contains(".") && !uri.endsWith(".json")) {
			return;
		}
		ExecutorsUtil.execute(new Runnable() {
			public void run() {
				collectScreenRight(uri, host);
			}
		});

		final String action = runData.getAction();
		final String actionEvent = runData.getActionEvent();
		ExecutorsUtil.execute(new Runnable() {
			public void run() {
				collectActionRight(action, actionEvent, host);
			}
		});
	}

	private synchronized static void collectScreenRight(String uri, String host) {
		OptionManager optionManager = IOC.get(OptionManager.class);
		if (!optionManager.isCollectRight()) {
			return;
		}
		if (uri == null || "".equals(uri)) {
			return;
		}
		RightManager rightManager = IOC.get("RightManager");
		if (rightManager.countByCode(uri) == 0l) {
			RightBO right = new RightBO();
			right.setId(rightManager.getId());
			right.setCode(uri);
			right.setType(RightType.Page.getCode());
			right.setName(uri);
			right.setSort(right.getId().doubleValue());
			rightManager.insert(right);
		}
	}

	private synchronized static void collectActionRight(String action, String actionEvent, String host) {
		OptionManager optionManager = IOC.get(OptionManager.class);
		if (!optionManager.isCollectRight()) {
			return;
		}
		if (action == null || actionEvent == null) {
			return;
		}
		action = action + "." + actionEvent;
		RightManager rightManager = IOC.get("RightManager");
		if (rightManager.getByCode(action) == null) {
			RightBO right = new RightBO();
			right.setId(rightManager.getId());
			right.setSys("admin");
			right.setCode(action);
			right.setType(RightType.Action.getCode());
			right.setName(action);
			rightManager.insert(right);
		}
	}

}
