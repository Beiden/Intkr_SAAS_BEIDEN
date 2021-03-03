package com.intkr.saas.facade.item;

import java.util.List;
import java.util.Random;

import com.intkr.saas.domain.bo.fs.ImgBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.item.ItemSkuManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.util.claz.IOC;

public class ItemDispather {

	private static ItemManager itemManager = IOC.get("ItemManager");
	private static ItemSkuManager itemSkuManager = IOC.get("ItemSkuManager");
	private static ImgManager imgManager = IOC.get("ImgManager");
	private static ShopManager shopManager = IOC.get("ShopManager");

	private static List<ShopBO> shopList;
	static {
		ShopBO query = new ShopBO();
		query.set_pageSize(100000);
		shopList = shopManager.select(query);
	}

	public static ShopBO getRandomShop() {
		Integer index = new Random().nextInt(shopList.size());
		return shopList.get(index);
	}

	public static void main(String[] args) {
		ItemBO query = new ItemBO();
		query.searchDb();
		query.set_pageSize(100);
		query.setShopId(100301L);
		query = itemManager.selectAndCount(query);
		while (query.get_count() > 1000) {
			for (Object itemTmp : query.getDatas()) {
				ItemBO item = (ItemBO) itemTmp;
				process(item);
			}
			System.out.println(query.get_page());
			query = itemManager.selectAndCount(query);
		}
	}

	private static void process(ItemBO item) {
		itemSkuManager.fill(item);
		imgManager.fills(item);
		ShopBO shop = getRandomShop();
		item.setShopId(shop.getId());
		itemManager.update(item);
		for (ImgBO img : item.getImgs()) {
			img.setUserId(shop.getUserId());
			img.setShopId(shop.getId());
			imgManager.update(img);
		}
	}

}
