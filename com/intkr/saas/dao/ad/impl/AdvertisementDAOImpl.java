package com.intkr.saas.dao.ad.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.ad.AdvertisementDAO;
import com.intkr.saas.domain.dbo.ad.AdvertisementDO;

/**
 * 广告
 * 
 * @table advertisement
 * 
 * @author Beiden
 * @date 2020-11-04 09:37:14
 * @version 1.0
 */
@Repository("AdvertisementDAO")
public class AdvertisementDAOImpl extends BaseDAOImpl<AdvertisementDO> implements AdvertisementDAO {

	public String getNameSpace() {
		return "advertisement";
	}
	
	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
