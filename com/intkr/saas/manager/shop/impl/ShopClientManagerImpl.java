package com.intkr.saas.manager.shop.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.shop.ShopClientDAO;
import com.intkr.saas.domain.bo.shop.ShopClientBO;
import com.intkr.saas.domain.dbo.shop.ShopClientDO;
import com.intkr.saas.manager.shop.ShopClientManager;

/**
 * 店铺客户
 * 
 * @table shop_client
 * 
 * @author Beiden
 * @date 2020-11-02 10:01:02
 * @version 1.0
 */
@Repository("ShopClientManager")
public class ShopClientManagerImpl extends BaseManagerImpl<ShopClientBO, ShopClientDO> implements ShopClientManager {

	@Resource
	private ShopClientDAO shopClientDAO;

	public BaseDAO<ShopClientDO> getBaseDAO() {
		return shopClientDAO;
	}

}
