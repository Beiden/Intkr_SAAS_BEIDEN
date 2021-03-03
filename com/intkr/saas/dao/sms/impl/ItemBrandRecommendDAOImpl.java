package com.intkr.saas.dao.sms.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemBrandRecommendDAO;
import com.intkr.saas.domain.dbo.sms.ItemBrandRecommendDO;

/**
 * 品牌推荐
 * 
 * @table item_brand_recommend
 * 
 * @author Beiden
 * @date 2020-11-11 22:44:12
 * @version 1.0
 */
@Repository("ItemBrandRecommendDAO")
public class ItemBrandRecommendDAOImpl extends BaseDAOImpl<ItemBrandRecommendDO> implements ItemBrandRecommendDAO {

	public String getNameSpace() {
		return "itemBrandRecommend";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
