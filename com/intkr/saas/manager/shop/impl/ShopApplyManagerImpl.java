package com.intkr.saas.manager.shop.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.shop.ShopApplyDAO;
import com.intkr.saas.domain.bo.shop.ShopApplyBO;
import com.intkr.saas.domain.dbo.shop.ShopApplyDO;
import com.intkr.saas.manager.shop.ShopApplyManager;

/**
 * 店铺申请
 * 
 * @table shop_apply
 * 
 * @author Beiden
 * @date 2020-11-10 22:09:23
 * @version 1.0
 */
@Repository("ShopApplyManager")
public class ShopApplyManagerImpl extends BaseManagerImpl<ShopApplyBO, ShopApplyDO> implements ShopApplyManager {

	@Resource
	private ShopApplyDAO shopApplyDAO;

	public BaseDAO<ShopApplyDO> getBaseDAO() {
		return shopApplyDAO;
	}

}
