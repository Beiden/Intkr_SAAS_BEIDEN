package com.intkr.saas.module.screen.admin.sms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.ad.AdvertisementBO;
import com.intkr.saas.domain.bo.ad.AdvertisementPositionBO;
import com.intkr.saas.domain.type.ad.AdvertisementPositionType;
import com.intkr.saas.domain.type.ad.AdvertisementType;
import com.intkr.saas.manager.ad.AdvertisementManager;
import com.intkr.saas.manager.ad.AdvertisementPositionManager;
import com.intkr.saas.module.action.sms.AdvertisementAction;
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
public class AdvertisementMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AdvertisementPositionManager advertisementPositionManager = IOC.get(AdvertisementPositionManager.class);

	private AdvertisementManager manager = IOC.get(AdvertisementManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Long positionId = RequestUtil.getParam(request, "positionId", Long.class);
		AdvertisementPositionBO adPosition = advertisementPositionManager.get(positionId);
		AdvertisementBO query = AdvertisementAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("adPosition", adPosition);
		request.setAttribute("AdvertisementType", AdvertisementType.Error);
		request.setAttribute("AdvertisementPositionType", AdvertisementPositionType.Error);
	}
}
