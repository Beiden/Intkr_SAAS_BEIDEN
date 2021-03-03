package com.intkr.saas.dao.shop.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.shop.ShopApplyDAO;
import com.intkr.saas.domain.dbo.shop.ShopApplyDO;

/**
 * 店铺申请
 * 
 * @table shop_apply
 * 
 * @author Beiden
 * @date 2020-11-10 22:09:23
 * @version 1.0
 */
@Repository("ShopApplyDAO")
public class ShopApplyDAOImpl extends BaseDAOImpl<ShopApplyDO> implements ShopApplyDAO {

	public String getNameSpace() {
		return "shopApply";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
