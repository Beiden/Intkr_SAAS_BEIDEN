package com.intkr.saas.manager.item.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.AuctionDetailDAO;
import com.intkr.saas.domain.bo.item.AuctionDetailBO;
import com.intkr.saas.domain.dbo.item.AuctionDetailDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.AuctionDetailManager;

/**
 * 
 * @author Beiden
 * @date 2016-4-13 下午1:51:37
 * @version 1.0
 */
@Repository("AuctionDetailManager")
public class AuctionDetailManagerImpl extends BaseManagerImpl<AuctionDetailBO, AuctionDetailDO> implements AuctionDetailManager {

	@Resource
	private AuctionDetailDAO auctionDetailDAO;

	public BaseDAO<AuctionDetailDO> getBaseDAO() {
		return auctionDetailDAO;
	}

}
