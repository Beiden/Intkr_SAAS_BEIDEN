package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemDAO;
import com.intkr.saas.distributed.search.client.ItemSearchClient;
import com.intkr.saas.domain.bo.fs.ImgBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemSkuBO;
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
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.dbo.item.ItemDO;
import com.intkr.saas.domain.type.item.ItemBuyConsultType;
import com.intkr.saas.domain.type.item.ShopAccuType;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.item.ItemSkuManager;
import com.intkr.saas.manager.item.ItemSpuManager;

/**
 * 
 * @author Beiden
 * @date 2011-10-31 下午7:52:46
 * @version 1.0
 */
@Repository("ItemManager")
public class ItemManagerImpl extends BaseManagerImpl<ItemBO, ItemDO> implements ItemManager {

	@Resource
	private ItemDAO itemDAO;

	@Resource
	private ImgManager imgManager;

	@Resource
	private ItemSkuManager itemSkuManager;

	@Resource
	private ItemSpuManager itemSpuManager;

	@Resource
	private ItemSearchClient itemSearchClient;

	public BaseDAO<ItemDO> getBaseDAO() {
		return itemDAO;
	}

	public ItemBO getFull(Long itemId) {
		ItemBO item = get(itemId);
		imgManager.fills(item);
		itemSkuManager.fill(item);
		return item;
	}

	public OrderDetailBO fill(OrderDetailBO orderDetail) {
		if (orderDetail == null) {
			return null;
		}
		ItemSkuBO sku = itemSkuManager.get(orderDetail.getSkuId());
		if (sku != null) {
			orderDetail.setItem(get(sku.getItemId()));
		}
		return orderDetail;
	}

	public ItemFootprintBO fill(ItemFootprintBO footprint) {
		if (footprint == null) {
			return null;
		}
		footprint.setItem(get(footprint.getItemId()));
		return footprint;
	}

	public ItemCompareBO fill(ItemCompareBO compare) {
		if (compare == null) {
			return null;
		}
		compare.setItem(get(compare.getItemId()));
		return compare;
	}

	public ItemCollectBO fill(ItemCollectBO collect) {
		if (collect == null) {
			return null;
		}
		collect.setItem(get(collect.getItemId()));
		return collect;
	}

	public OrderEvaluateBO fill(OrderEvaluateBO evaluate) {
		if (evaluate == null) {
			return null;
		}
		evaluate.setItem(get(evaluate.getItemId()));
		return evaluate;
	}

	public ItemCustServBO fill(ItemCustServBO itemCustServ) {
		if (itemCustServ == null) {
			return null;
		}
		itemCustServ.setItem(get(itemCustServ.getItemId()));
		return itemCustServ;
	}

	public OrderBO fill(OrderBO order) {
		if (order == null || order.getOrderDetails() == null) {
			return order;
		}
		for (OrderDetailBO detail : order.getOrderDetails()) {
			fill(detail);
		}
		return order;
	}

	public ItemCouponActivityBO fill(ItemCouponActivityBO activity) {
		if (activity == null || activity.getFeature("itemIds") == null) {
			return activity;
		}
		List<String> itemIds = (List<String>) activity.getFeature("itemIds");
		if (itemIds == null || itemIds.isEmpty()) {
			return activity;
		}
		ItemBO query = new ItemBO();
		query.setQuery("ids", itemIds);
		query.set_pageSize(itemIds.size());
		activity.setItems(select(query));
		return activity;
	}

	public ShoppingCartBO fill(ShoppingCartBO shoppingCart) {
		if (shoppingCart == null) {
			return shoppingCart;
		}
		if (!IdEngine.isValidate(shoppingCart.getSkuId())) {
			return shoppingCart;
		}
		shoppingCart.setItem(get(shoppingCart.getSkuId()));
		return shoppingCart;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<MsgBO>();
		}
		for (Object obj : list) {
			if (obj instanceof OrderBO) {
				fill((OrderBO) obj);
			} else if (obj instanceof ShoppingCartBO) {
				fill((ShoppingCartBO) obj);
			} else if (obj instanceof OrderDetailBO) {
				fill((OrderDetailBO) obj);
			} else if (obj instanceof ItemBuyConsultBO) {
				fill((ItemBuyConsultBO) obj);
			} else if (obj instanceof ItemSellRecordBO) {
				fill((ItemSellRecordBO) obj);
			} else if (obj instanceof ItemFootprintBO) {
				fill((ItemFootprintBO) obj);
			} else if (obj instanceof ItemCompareBO) {
				fill((ItemCompareBO) obj);
			} else if (obj instanceof ItemCollectBO) {
				fill((ItemCollectBO) obj);
			} else if (obj instanceof OrderEvaluateBO) {
				fill((OrderEvaluateBO) obj);
			} else if (obj instanceof ItemCustServBO) {
				fill((ItemCustServBO) obj);
			} else if (obj instanceof ShopComplaintBO) {
				fill((ShopComplaintBO) obj);
			} else if (obj instanceof ShopAccuBO) {
				fill((ShopAccuBO) obj);
			} else if (obj instanceof ItemCouponActivityBO) {
				fill((ItemCouponActivityBO) obj);
			}
		}
		return list;
	}

	public ItemBuyConsultBO fill(ItemBuyConsultBO itemBuyConsult) {
		if (itemBuyConsult == null) {
			return itemBuyConsult;
		}
		if (!ItemBuyConsultType.Item.getCode().equals(itemBuyConsult.getType())) {
			return itemBuyConsult;
		}
		itemBuyConsult.setItem(get(itemBuyConsult.getItemId()));
		return itemBuyConsult;
	}

	public ShopAccuBO fill(ShopAccuBO shopAccu) {
		if (shopAccu == null) {
			return shopAccu;
		}
		if (!ShopAccuType.Item.getCode().equals(shopAccu.getType())) {
			return shopAccu;
		}
		shopAccu.setItem(get(shopAccu.getItemId()));
		return shopAccu;
	}

	public ShopComplaintBO fill(ShopComplaintBO complaint) {
		if (complaint == null) {
			return complaint;
		}
		if (!ShopAccuType.Item.getCode().equals(complaint.getType())) {
			return complaint;
		}
		complaint.setItem(get(complaint.getItemId()));
		return complaint;
	}

	public ItemSellRecordBO fill(ItemSellRecordBO itemSellRecord) {
		if (itemSellRecord == null) {
			return itemSellRecord;
		}
		itemSellRecord.setItem(get(itemSellRecord.getItemId()));
		return itemSellRecord;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			ItemManagerImpl manager = new ItemManagerImpl();
			ItemBO item = manager.get(404);
			item.setId(manager.getId());
			manager.insert(item);
		}
	}

	public List<ItemBO> select(ItemBO query) {
		try {
			if (!query.isSearchDb()) {
				query = itemSearchClient.search(query);
				return query.getDatas();
			}
		} catch (Exception e) {
			return super.select(query);
		}
		return super.select(query);
	}

	public ItemBO selectAndCount(ItemBO query) {
		try {
			if (!query.isSearchDb()) {
				return itemSearchClient.search(query);
			}
		} catch (Exception e) {
			return super.selectAndCount(query);
		}
		return super.selectAndCount(query);
	}

	public long insert(ItemBO object) {
		long result = super.insert(object);
		return result;
	}

	public long insert(List<ItemBO> boList) {
		long result = super.insert(boList);
		return result;
	}

	public int delete(Object itemIdObj) {
		if (itemIdObj == null) {
			return -1;
		}
		Long itemId = Long.valueOf(itemIdObj.toString());
		ItemBO item = getFull(itemId);
		for (ImgBO img : item.getImgs()) {
			imgManager.delete(img.getId());
		}
		for (ItemSkuBO sku : item.getSkus()) {
			itemSkuManager.delete(sku.getId());
		}
		int result = super.delete(itemId);
		itemSearchClient.delete(itemId);
		return result;
	}

	public void deleteAll() {
		super.deleteAll();
	}

	public int deleteAll(List<Object> ids) {
		int result = super.deleteAll(ids);
		return result;
	}

	public int update(ItemBO item) {
		int result = super.update(item);
		itemSearchClient.dump(item.getId());
		return result;
	}
}
