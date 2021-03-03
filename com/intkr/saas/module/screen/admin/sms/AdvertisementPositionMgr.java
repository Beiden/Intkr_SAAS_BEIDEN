package com.intkr.saas.module.screen.admin.sms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.ad.AdvertisementPositionBO;
import com.intkr.saas.domain.type.ad.AdvertisementPositionType;
import com.intkr.saas.manager.ad.AdvertisementPositionManager;
import com.intkr.saas.module.action.sms.AdvertisementPositionAction;
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
public class AdvertisementPositionMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AdvertisementPositionManager manager = IOC.get(AdvertisementPositionManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AdvertisementPositionBO query = AdvertisementPositionAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("AdvertisementPositionType", AdvertisementPositionType.Error);
	}

}
