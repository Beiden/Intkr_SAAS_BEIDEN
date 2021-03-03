package com.intkr.saas.manager.sms.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemBrandRecommendDAO;
import com.intkr.saas.domain.bo.sms.ItemBrandRecommendBO;
import com.intkr.saas.domain.dbo.sms.ItemBrandRecommendDO;
import com.intkr.saas.manager.sms.ItemBrandRecommendManager;

/**
 * 品牌推荐
 * 
 * @table item_brand_recommend
 * 
 * @author Beiden
 * @date 2020-11-11 22:44:12
 * @version 1.0
 */
@Repository("ItemBrandRecommendManager")
public class ItemBrandRecommendManagerImpl extends BaseManagerImpl<ItemBrandRecommendBO, ItemBrandRecommendDO> implements ItemBrandRecommendManager {

	@Resource
	private ItemBrandRecommendDAO itemBrandRecommendDAO;

	public BaseDAO<ItemBrandRecommendDO> getBaseDAO() {
		return itemBrandRecommendDAO;
	}

}
