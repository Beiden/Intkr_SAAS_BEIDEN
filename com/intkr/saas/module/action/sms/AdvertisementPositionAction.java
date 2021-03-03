package com.intkr.saas.module.action.sms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.ad.AdvertisementPositionBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.ad.AdvertisementPositionManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 广告位
 * 
 * @table advertisement_position
 * 
 * @author Beiden
 * @date 2020-11-04 09:37:22
 * @version 1.0
 */
public class AdvertisementPositionAction extends BaseAction<AdvertisementPositionBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AdvertisementPositionManager advertisementPositionManager = IOC.get(AdvertisementPositionManager.class);

	public AdvertisementPositionBO getBO(HttpServletRequest request) {
		AdvertisementPositionBO advertisementPositionBO = getParameter(request);
		return advertisementPositionBO;
	}

	public static AdvertisementPositionBO getParameter(HttpServletRequest request) {
		AdvertisementPositionBO advertisementPositionBO = new AdvertisementPositionBO();
		advertisementPositionBO.setId(RequestUtil.getParam(request, "id", Long.class));
		advertisementPositionBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		advertisementPositionBO.setType(RequestUtil.getParam(request, "type", String.class));
		advertisementPositionBO.setName(RequestUtil.getParam(request, "name", String.class));
		advertisementPositionBO.setNote(RequestUtil.getParam(request, "note", String.class));
		advertisementPositionBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		if (RequestUtil.existParam(request, "nameList")) {
			List<String> nameList = RequestUtil.getParams(request, "nameList", ";");
			advertisementPositionBO.setQuery("nameList", nameList);
		}
		PagerUtil.initPage(request, advertisementPositionBO);
		return advertisementPositionBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return advertisementPositionManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doAdd(request, response);
	}

}
