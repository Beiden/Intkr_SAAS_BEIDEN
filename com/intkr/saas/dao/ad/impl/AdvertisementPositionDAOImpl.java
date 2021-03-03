package com.intkr.saas.dao.ad.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.ad.AdvertisementPositionDAO;
import com.intkr.saas.domain.dbo.ad.AdvertisementPositionDO;

/**
 * 广告位
 * 
 * @table advertisement_position
 * 
 * @author Beiden
 * @date 2020-11-04 09:37:22
 * @version 1.0
 */
@Repository("AdvertisementPositionDAO")
public class AdvertisementPositionDAOImpl extends BaseDAOImpl<AdvertisementPositionDO> implements AdvertisementPositionDAO {

	public String getNameSpace() {
		return "advertisementPosition";
	}
	
	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
