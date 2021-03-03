package com.intkr.saas.manager.shopping;

import java.util.List;

import com.intkr.saas.domain.bo.shopping.ShoppingCartBO;
import com.intkr.saas.domain.dbo.shopping.ShoppingCartDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2011-4-29 下午9:18:11
 * @version 1.0
 */
public interface ShoppingCartManager extends BaseManager<ShoppingCartBO, ShoppingCartDO> {

	public List<ShoppingCartBO> selectByUserId(Long userId);

}
