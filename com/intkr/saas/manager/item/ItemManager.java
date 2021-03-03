package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.order.ItemCustServBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.domain.bo.order.OrderEvaluateBO;
import com.intkr.saas.domain.bo.shop.ItemSellRecordBO;
import com.intkr.saas.domain.bo.shop.ShopAccuBO;
import com.intkr.saas.domain.bo.shop.ShopComplaintBO;
import com.intkr.saas.domain.bo.shopping.ItemBuyConsultBO;
import com.intkr.saas.domain.bo.shopping.ItemCollectBO;
import com.intkr.saas.domain.bo.shopping.ItemCompareBO;
import com.intkr.saas.domain.bo.shopping.ItemFootprintBO;
import com.intkr.saas.domain.bo.shopping.ShoppingCartBO;
import com.intkr.saas.domain.bo.sms.ItemCouponActivityBO;
import com.intkr.saas.domain.dbo.item.ItemDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2011-10-31 下午7:52:13
 * @version 1.0
 */
public interface ItemManager extends BaseManager<ItemBO, ItemDO> {

	public ItemBO getFull(Long itemId);

	// basic
	public List<?> fill(List<?> list);

	// shopping
	public ItemCollectBO fill(ItemCollectBO collect);

	public ItemFootprintBO fill(ItemFootprintBO footprint);

	public ItemCompareBO fill(ItemCompareBO compare);

	public ItemBuyConsultBO fill(ItemBuyConsultBO itemBuyConsult);

	public ShopAccuBO fill(ShopAccuBO shopAccu);

	public ShopComplaintBO fill(ShopComplaintBO shopComplaint);

	public ItemSellRecordBO fill(ItemSellRecordBO itemSellRecord);

	public ShoppingCartBO fill(ShoppingCartBO shoppingCart);

	// order
	public ItemCustServBO fill(ItemCustServBO itemCustServ);

	public OrderEvaluateBO fill(OrderEvaluateBO evaluate);

	public OrderDetailBO fill(OrderDetailBO orderDetail);

	public OrderBO fill(OrderBO order);
	
	public ItemCouponActivityBO fill(ItemCouponActivityBO activity);

}
