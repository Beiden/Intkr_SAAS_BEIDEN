package com.intkr.saas.manager.shop.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.shop.ShopComplaintDAO;
import com.intkr.saas.domain.bo.shop.ShopComplaintBO;
import com.intkr.saas.domain.dbo.shop.ShopComplaintDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.shop.ShopComplaintManager;

/**
 * 
 * @author Beiden
 * @date 2017-09-07 18:09:37
 * @version 1.0
 */
@Repository("ShopComplaintManager")
public class ShopComplaintManagerImpl extends BaseManagerImpl<ShopComplaintBO, ShopComplaintDO> implements ShopComplaintManager {

	@Resource
	private ShopComplaintDAO shopComplaintDAO;

	public BaseDAO<ShopComplaintDO> getBaseDAO() {
		return shopComplaintDAO;
	}

}
