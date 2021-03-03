package com.intkr.saas.manager.shop;

import java.util.List;

import com.intkr.saas.domain.bo.shop.ShopActivityBO;
import com.intkr.saas.domain.bo.sns.AttentionBO;
import com.intkr.saas.domain.dbo.shop.ShopActivityDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午10:11:52
 * @version 1.0
 */
public interface ShopActivityManager extends BaseManager<ShopActivityBO, ShopActivityDO> {

	public AttentionBO fill(AttentionBO attention);

	public List<?> fill(List<?> list);

}
