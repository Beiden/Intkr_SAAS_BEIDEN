package com.intkr.saas.manager.shop;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.order.ItemCustServBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderEvaluateBO;
import com.intkr.saas.domain.bo.order.OrderReturnedBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.domain.bo.shopping.ShopCollectBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.dbo.shop.ShopDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2016-4-22 上午10:14:31
 * @version 1.0
 */
public interface ShopManager extends BaseManager<ShopBO, ShopDO> {

	public ShopCollectBO fill(ShopCollectBO shopCollect);

	public OrderReturnedBO fill(OrderReturnedBO orderReturn);

	public ItemCustServBO fill(ItemCustServBO itemCustServ);

	public OrderEvaluateBO fill(OrderEvaluateBO evaluate);

	public OrderBO fill(OrderBO order);

	public ItemBO fill(ItemBO item);

	public UserBO fill(UserBO account);

	public List<?> fill(List<?> list);

	public ShopBO getByUserId(Long userId);

}
