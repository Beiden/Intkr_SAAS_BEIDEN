package com.intkr.saas.module.action.sms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.ad.AdvertisementBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.ad.AdvertisementManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 广告
 * 
 * @table advertisement
 * 
 * @author Beiden
 * @date 2020-11-04 09:37:14
 * @version 1.0
 */
public class AdvertisementAction extends BaseAction<AdvertisementBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AdvertisementManager advertisementManager = IOC.get(AdvertisementManager.class);

	public AdvertisementBO getBO(HttpServletRequest request) {
		AdvertisementBO advertisementBO = getParameter(request);
		return advertisementBO;
	}

	public static AdvertisementBO getParameter(HttpServletRequest request) {
		AdvertisementBO advertisementBO = new AdvertisementBO();
		advertisementBO.setId(RequestUtil.getParam(request, "id", Long.class));
		advertisementBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		advertisementBO.setPositionId(RequestUtil.getParam(request, "positionId", Long.class));
		advertisementBO.setUrl(RequestUtil.getParam(request, "url", String.class));
		advertisementBO.setType(RequestUtil.getParam(request, "type", String.class));
		advertisementBO.setCode(RequestUtil.getParam(request, "code", String.class));
		advertisementBO.setName(RequestUtil.getParam(request, "name", String.class));
		advertisementBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, advertisementBO);
		return advertisementBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return advertisementManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			AdvertisementBO bo = getBO(request);
			for (Object key : request.getParameterMap().keySet()) {
				String name = (String) key;
				if (name.startsWith("feature_")) {
					bo.setFeature(name.substring(8), RequestUtil.getParam(request, name));
				}
			}
			long id = advertisementManager.insert(bo);
			request.setAttribute("result", true);
			request.setAttribute("data", "{\"id\":" + id + "}");
			request.setAttribute("msg", "添加成功！");
		} catch (Exception e) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			AdvertisementBO bo = getBO(request);
			for (Object key : request.getParameterMap().keySet()) {
				String name = (String) key;
				if (name.startsWith("feature_")) {
					bo.setFeature(name.substring(8), RequestUtil.getParam(request, name));
				}
			}
			advertisementManager.update(bo);
			request.setAttribute("result", true);
			request.setAttribute("msg", "更新成功！");
		} catch (Exception e) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
