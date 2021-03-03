package com.intkr.saas.manager.ad.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.ad.AdvertisementPositionDAO;
import com.intkr.saas.domain.bo.ad.AdvertisementPositionBO;
import com.intkr.saas.domain.dbo.ad.AdvertisementPositionDO;
import com.intkr.saas.manager.ad.AdvertisementPositionManager;

/**
 * 广告位
 * 
 * @table advertisement_position
 * 
 * @author Beiden
 * @date 2020-11-04 09:37:22
 * @version 1.0
 */
@Repository("AdvertisementPositionManager")
public class AdvertisementPositionManagerImpl extends BaseManagerImpl<AdvertisementPositionBO, AdvertisementPositionDO> implements AdvertisementPositionManager {

	@Resource
	private AdvertisementPositionDAO advertisementPositionDAO;

	public BaseDAO<AdvertisementPositionDO> getBaseDAO() {
		return advertisementPositionDAO;
	}

}
