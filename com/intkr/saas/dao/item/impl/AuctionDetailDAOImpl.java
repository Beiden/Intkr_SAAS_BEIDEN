package com.intkr.saas.dao.item.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.AuctionDetailDAO;
import com.intkr.saas.domain.dbo.item.AuctionDetailDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-13 下午1:48:28
 * @version 1.0
 */
@Repository("AuctionDetailDAO")
public class AuctionDetailDAOImpl extends BaseDAOImpl<AuctionDetailDO> implements AuctionDetailDAO {

	public String getNameSpace() {
		return "auctionDetail";
	}

}
