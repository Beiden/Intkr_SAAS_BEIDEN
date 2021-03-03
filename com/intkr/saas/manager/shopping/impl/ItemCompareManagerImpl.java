package com.intkr.saas.manager.shopping.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.shopping.ItemCompareDAO;
import com.intkr.saas.domain.bo.shopping.ItemCompareBO;
import com.intkr.saas.domain.dbo.shopping.ItemCompareDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.shopping.ItemCompareManager;

/**
 * 
 * @author Beiden
 * @date 2017-09-02 15:36:08
 * @version 1.0
 */
@Repository("ShopCompareManager")
public class ItemCompareManagerImpl extends BaseManagerImpl<ItemCompareBO, ItemCompareDO> implements ItemCompareManager {

	@Resource
	private ItemCompareDAO shopCompareDAO;

	public BaseDAO<ItemCompareDO> getBaseDAO() {
		return shopCompareDAO;
	}

}
