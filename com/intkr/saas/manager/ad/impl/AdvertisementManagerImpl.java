package com.intkr.saas.manager.ad.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.ad.AdvertisementDAO;
import com.intkr.saas.domain.bo.ad.AdvertisementBO;
import com.intkr.saas.domain.bo.ad.AdvertisementPositionBO;
import com.intkr.saas.domain.dbo.ad.AdvertisementDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.ad.AdvertisementManager;

/**
 * 广告
 * 
 * @table advertisement
 * 
 * @author Beiden
 * @date 2020-11-04 09:37:14
 * @version 1.0
 */
@Repository("AdvertisementManager")
public class AdvertisementManagerImpl extends BaseManagerImpl<AdvertisementBO, AdvertisementDO> implements AdvertisementManager {

	@Resource
	private AdvertisementDAO advertisementDAO;

	public BaseDAO<AdvertisementDO> getBaseDAO() {
		return advertisementDAO;
	}

	public AdvertisementPositionBO fill(AdvertisementPositionBO ad) {
		AdvertisementBO query = new AdvertisementBO();
		query.set_pageSize(1000);
		query.setPositionId(ad.getId());
		ad.setAds(select(query));
		return ad;
	}
}
