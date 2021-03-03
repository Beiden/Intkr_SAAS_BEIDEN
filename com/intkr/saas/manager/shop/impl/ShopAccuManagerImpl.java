package com.intkr.saas.manager.shop.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.shop.ShopAccuDAO;
import com.intkr.saas.domain.bo.shop.ShopAccuBO;
import com.intkr.saas.domain.dbo.shop.ShopAccuDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.shop.ShopAccuManager;

/**
 * 
 * @author Beiden
 * @date 2017-09-07 18:09:23
 * @version 1.0
 */
@Repository("ShopAccuManager")
public class ShopAccuManagerImpl extends BaseManagerImpl<ShopAccuBO, ShopAccuDO> implements ShopAccuManager {

	@Resource
	private ShopAccuDAO shopAccuDAO;

	public BaseDAO<ShopAccuDO> getBaseDAO() {
		return shopAccuDAO;
	}

}
