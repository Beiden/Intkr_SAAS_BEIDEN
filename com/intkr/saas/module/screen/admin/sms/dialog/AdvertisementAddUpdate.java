package com.intkr.saas.module.screen.admin.sms.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.ad.AdvertisementBO;
import com.intkr.saas.domain.type.ad.AdvertisementType;
import com.intkr.saas.manager.ad.AdvertisementManager;
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
public class AdvertisementAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AdvertisementManager manager = IOC.get(AdvertisementManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String advertisementId = RequestUtil.getParam(request, "advertisementId");
		AdvertisementBO advertisement = manager.get(advertisementId);
		request.setAttribute("advertisement", advertisement);
		request.setAttribute("addId", manager.getId());
		request.setAttribute("AdvertisementTypeList", AdvertisementType.values());
	}
}
