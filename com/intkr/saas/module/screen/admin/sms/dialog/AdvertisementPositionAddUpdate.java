package com.intkr.saas.module.screen.admin.sms.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.ad.AdvertisementPositionBO;
import com.intkr.saas.domain.type.ad.AdvertisementPositionType;
import com.intkr.saas.manager.ad.AdvertisementPositionManager;
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
public class AdvertisementPositionAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AdvertisementPositionManager manager = IOC.get(AdvertisementPositionManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String advertisementPositionId = RequestUtil.getParam(request, "advertisementPositionId");
		AdvertisementPositionBO advertisementPosition = manager.get(advertisementPositionId);
		request.setAttribute("advertisementPosition", advertisementPosition);
		request.setAttribute("addId", manager.getId());
		request.setAttribute("AdvertisementPositionTypeList", AdvertisementPositionType.values());
	}

}
