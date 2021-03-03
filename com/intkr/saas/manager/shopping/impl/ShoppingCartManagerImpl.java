package com.intkr.saas.manager.shopping.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.shopping.ShoppingCartDAO;
import com.intkr.saas.domain.bo.shopping.ShoppingCartBO;
import com.intkr.saas.domain.dbo.shopping.ShoppingCartDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.shopping.ShoppingCartManager;

/**
 * 
 * @author Beiden
 * @date 2011-7-28 上午10:27:29
 * @version 1.0
 */
@Repository("ShoppingCartManager")
public class ShoppingCartManagerImpl extends BaseManagerImpl<ShoppingCartBO, ShoppingCartDO> implements ShoppingCartManager {

	@Resource
	private ShoppingCartDAO favoriteDAO;

	public BaseDAO<ShoppingCartDO> getBaseDAO() {
		return favoriteDAO;
	}

	public List<ShoppingCartBO> selectByUserId(Long userId) {
		ShoppingCartBO query = new ShoppingCartBO();
		query.set_pageSize(1000);
		query.setUserId(userId);
		return select(query);
	}

}
