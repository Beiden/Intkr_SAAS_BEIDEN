package com.intkr.saas.manager.shopping.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.shopping.ShopCollectDAO;
import com.intkr.saas.domain.bo.shopping.ShopCollectBO;
import com.intkr.saas.domain.dbo.shopping.ShopCollectDO;
import com.intkr.saas.manager.shopping.ShopCollectManager;

/**
 * 店铺收藏
 * 
 * @table shop_collect
 * 
 * @author Beiden
 * @date 2020-11-14 17:59:25
 * @version 1.0
 */
@Repository("ShopCollectManager")
public class ShopCollectManagerImpl extends BaseManagerImpl<ShopCollectBO, ShopCollectDO> implements ShopCollectManager {

	@Resource
	private ShopCollectDAO shopCollectDAO;

	public BaseDAO<ShopCollectDO> getBaseDAO() {
		return shopCollectDAO;
	}

}
