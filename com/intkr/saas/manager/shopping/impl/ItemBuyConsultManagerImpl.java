package com.intkr.saas.manager.shopping.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.shopping.ItemBuyConsultDAO;
import com.intkr.saas.domain.bo.shopping.ItemBuyConsultBO;
import com.intkr.saas.domain.dbo.shopping.ItemBuyConsultDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.shopping.ItemBuyConsultManager;

/**
 * 
 * @author Beiden
 * @date 2016-5-20 下午9:27:52
 * @version 1.0
 */
@Repository("ItemBuyConsultManager")
public class ItemBuyConsultManagerImpl extends BaseManagerImpl<ItemBuyConsultBO, ItemBuyConsultDO> implements ItemBuyConsultManager {

	@Resource
	private ItemBuyConsultDAO itemBuyConsultDAO;

	public BaseDAO<ItemBuyConsultDO> getBaseDAO() {
		return itemBuyConsultDAO;
	}

}
