package com.intkr.saas.module.toolbox.ad;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.ad.AdvertisementPositionBO;
import com.intkr.saas.manager.ad.AdvertisementManager;
import com.intkr.saas.manager.ad.AdvertisementPositionManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class AdDS extends BaseToolBox {

	public static Cache<String, AdvertisementPositionBO> adsCache = CacheBuilder.newBuilder()//
			.expireAfterAccess(30, TimeUnit.MINUTES).//
			maximumSize(10000).//
			build();

	public AdvertisementPositionManager advertisementPositionManager = IOC.get("AdvertisementPositionManager");

	public AdvertisementManager advertisementManager = IOC.get("AdvertisementManager");

	public AdvertisementPositionBO get(HttpServletRequest request, final String name) {
		final Long saasId = SessionClient.getSaasId(request);
		final String key = saasId + "|" + name;
		AdvertisementPositionBO ads;
		try {
			ads = adsCache.get(key, new Callable<AdvertisementPositionBO>() {
				public AdvertisementPositionBO call() throws Exception {
					AdvertisementPositionBO query = new AdvertisementPositionBO();
					query.setSaasId(saasId);
					query.setName(name);
					AdvertisementPositionBO ad = advertisementPositionManager.selectOne(query);
					if (ad == null) {
						ad = new AdvertisementPositionBO();
						return ad;
					}
					advertisementManager.fill(ad);
					return ad;
				}
			});
			if (ads.getId() == null) {
				ads = null;
			}
		} catch (ExecutionException e) {
			ads = null;
		}
		return ads;
	}
}
