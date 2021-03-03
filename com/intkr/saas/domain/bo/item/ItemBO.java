package com.intkr.saas.domain.bo.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.fs.ImgBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.util.JsonUtil;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午10:15:32
 * @version 1.0
 */
public class ItemBO extends BaseBO {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// 店铺ID
	private Long shopId;

	// 交易类型：ItemTradeMethod
	private String tradeMethod;

	// 商品类型：ItemType
	private String type;

	// 一级类目
	private Long firstCategoryId;

	// 二级类目
	private Long secondCategoryId;

	// 三级类目
	private Long thirdCategoryId;

	// 商品标签ID，用;分隔，以;开头，以;结尾
	private String tagIds;

	// sku类型：1：单SKU；2：属性组合SKU；3：自定义SKU
	private Integer skuType;

	// 商品名称
	private String name;

	// 标语
	private String slogan;

	// 价格：单位（分）
	private Long price;

	// 状态：ItemStatus
	private String status;

	// 状态：inventoryStatus
	private String inventoryStatus;

	// 商品焦点图ID
	private String imgIds;

	// 描述内容
	private String content;

	// 权重：用于排序
	private Double sort;

	private Long deliveryFeeId;

	// 拓展字段
	private String feature;

	// 商品焦点图
	private List<ImgBO> imgs;

	// 一级类目
	private ItemCategoryBO firstCategory;

	// 二级类目
	private ItemCategoryBO secondCategory;

	// 三级类目
	private ItemCategoryBO thirdCategory;

	// 商品标签
	private List<ItemTagBO> tags;

	// 店铺
	private ShopBO shop;

	// 标准属性
	private List<ItemSpuBO> spus;

	// 销售属性
	private List<ItemSkuBO> skus;

	// 属性列表
	private List<ItemSkuPropertyBO> skuPropertys;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getImgIds() {
		return imgIds;
	}

	public void setImgIds(String imgIds) {
		this.imgIds = imgIds;
	}

	public List<ImgBO> getImgs() {
		return imgs;
	}

	public void setImgs(List<ImgBO> imgs) {
		this.imgs = imgs;
	}

	public Object getFeature(String key) {
		Map<String, Object> map = JsonUtil.toObject(feature, Map.class);
		if (map == null) {
			return null;
		}
		return map.get(key);
	}

	public void setFeature(String key, Object value) {
		Map<String, Object> map = JsonUtil.toObject(feature, Map.class);
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put(key, value);
		this.feature = JsonUtil.toJson(map);
	}

	public List<ItemTagBO> getTags() {
		return tags;
	}

	public void setTags(List<ItemTagBO> tags) {
		this.tags = tags;
	}

	public ShopBO getShop() {
		return shop;
	}

	public void setShop(ShopBO shop) {
		this.shop = shop;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public List<ItemSpuBO> getSpus() {
		return spus;
	}

	public void setSpus(List<ItemSpuBO> spus) {
		this.spus = spus;
	}

	public List<ItemSkuBO> getSkus() {
		return skus;
	}

	public void setSkus(List<ItemSkuBO> skus) {
		this.skus = skus;
	}

	public boolean isSingleSku() {
		if (this.skus == null) {
			return false;
		}
		return this.skus.size() == 1;
	}

	public ItemSkuBO getSku() {
		if (this.skus == null) {
			return null;
		}
		return this.skus.get(0);
	}

	public Integer getSkuType() {
		return skuType;
	}

	public void setSkuType(Integer skuType) {
		this.skuType = skuType;
	}

	public List<ItemSkuPropertyBO> getSkuPropertys() {
		return skuPropertys;
	}

	public void setSkuPropertys(List<ItemSkuPropertyBO> skuPropertys) {
		this.skuPropertys = skuPropertys;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public ItemCategoryBO getFirstCategory() {
		return firstCategory;
	}

	public void setFirstCategory(ItemCategoryBO firstCategory) {
		this.firstCategory = firstCategory;
	}

	public ItemCategoryBO getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(ItemCategoryBO secondCategory) {
		this.secondCategory = secondCategory;
	}

	public ItemCategoryBO getThirdCategory() {
		return thirdCategory;
	}

	public void setThirdCategory(ItemCategoryBO thirdCategory) {
		this.thirdCategory = thirdCategory;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTradeMethod() {
		return tradeMethod;
	}

	public void setTradeMethod(String tradeMethod) {
		this.tradeMethod = tradeMethod;
	}

	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public boolean containsTagId(Long tagId) {
		if (tagIds == null) {
			return false;
		}
		return tagIds.contains(";" + tagId + ";");
	}

	public void addTagId(Long tagId) {
		if (tagId == null) {
			return;
		}
		if (tagIds == null || "".equals(tagIds)) {
			tagIds = ";";
		}
		tagIds += tagId + ";";
	}

	public void removeTagId(Long tagId) {
		if (containsTagId(tagId)) {
			tagIds = tagIds.replace(tagId + ";", "");
		}
		while (tagIds.contains(";;")) {
			tagIds = tagIds.replace(";;", ";");
		}
	}

	public boolean isSearchLucene() {
		return "lucene".equalsIgnoreCase(getQuery("searchData") + "");
	}

	public void searchLucene() {
		setQuery("searchData", "lucene");
	}

	public boolean isSearchDb() {
		return "db".equalsIgnoreCase(getQuery("searchData") + "");
	}

	public void searchDb() {
		setQuery("searchData", "db");
	}

	public Long getFirstCategoryId() {
		return firstCategoryId;
	}

	public void setFirstCategoryId(Long firstCategoryId) {
		this.firstCategoryId = firstCategoryId;
	}

	public Long getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(Long secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public Long getThirdCategoryId() {
		return thirdCategoryId;
	}

	public void setThirdCategoryId(Long thirdCategoryId) {
		this.thirdCategoryId = thirdCategoryId;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public String getInventoryStatus() {
		return inventoryStatus;
	}

	public void setInventoryStatus(String inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}

	public Long getDeliveryFeeId() {
		return deliveryFeeId;
	}

	public void setDeliveryFeeId(Long deliveryFeeId) {
		this.deliveryFeeId = deliveryFeeId;
	}

}
