package com.intkr.saas.manager.sms.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemRecommendDAO;
import com.intkr.saas.domain.bo.sms.ItemRecommendBO;
import com.intkr.saas.domain.dbo.sms.ItemRecommendDO;
import com.intkr.saas.manager.sms.ItemRecommendManager;

/**
 * 商品推荐
 * 
 * @table item_recommend
 * 
 * @author Beiden
 * @date 2020-11-11 23:10:14
 * @version 1.0
 */
@Repository("ItemRecommendManager")
public class ItemRecommendManagerImpl extends BaseManagerImpl<ItemRecommendBO, ItemRecommendDO> implements ItemRecommendManager {

	@Resource
	private ItemRecommendDAO itemRecommendDAO;

	public BaseDAO<ItemRecommendDO> getBaseDAO() {
		return itemRecommendDAO;
	}

}
