package com.intkr.saas.manager.ad;

import com.intkr.saas.domain.bo.ad.AdvertisementBO;
import com.intkr.saas.domain.bo.ad.AdvertisementPositionBO;
import com.intkr.saas.domain.dbo.ad.AdvertisementDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 广告
 * 
 * @table advertisement
 * 
 * @author Beiden
 * @date 2020-11-04 09:37:14
 * @version 1.0
 */
public interface AdvertisementManager extends BaseManager<AdvertisementBO, AdvertisementDO> {

	public AdvertisementPositionBO fill(AdvertisementPositionBO ad);

}
