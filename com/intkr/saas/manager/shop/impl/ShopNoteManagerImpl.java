package com.intkr.saas.manager.shop.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.shop.ShopNoteDAO;
import com.intkr.saas.domain.bo.shop.ShopNoteBO;
import com.intkr.saas.domain.dbo.shop.ShopNoteDO;
import com.intkr.saas.manager.shop.ShopNoteManager;

/**
 * 店铺公告
 * 
 * @table shop_note
 * 
 * @author Beiden
 * @date 2020-10-30 18:05:17
 * @version 1.0
 */
@Repository("ShopNoteManager")
public class ShopNoteManagerImpl extends BaseManagerImpl<ShopNoteBO, ShopNoteDO> implements ShopNoteManager {

	@Resource
	private ShopNoteDAO shopNoteDAO;

	public BaseDAO<ShopNoteDO> getBaseDAO() {
		return shopNoteDAO;
	}

}
