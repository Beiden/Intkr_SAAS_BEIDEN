package com.intkr.saas.domain.bo.item;

import java.util.List;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:42:29
 * @version 1.0
 */
public class ItemSkuBO extends BaseBO {

	private Long saasId;

	// 状态
	private String status;

	// inventoryStatus
	private String inventoryStatus;

	// 就否默认sku
	private Integer isDefault;

	// 商品ID
	private Long itemId;

	// 名称
	private String name;

	// 图片
	private Long imgId;

	// 颜色
	private String color;

	// 库存
	private Integer inventory;

	// 销售价
	private Long price;

	// 权重
	private Double sort;

	// 备注
	private String note;

	// 属性
	private String feature;

	private List<ItemSkuValueBO> skuValues;

	private ItemBO item;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public List<ItemSkuValueBO> getSkuValues() {
		return skuValues;
	}

	public void setSkuValues(List<ItemSkuValueBO> skuValues) {
		this.skuValues = skuValues;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getImgId() {
		return imgId;
	}

	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public ItemBO getItem() {
		return item;
	}

	public void setItem(ItemBO item) {
		this.item = item;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getInventoryStatus() {
		return inventoryStatus;
	}

	public void setInventoryStatus(String inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}

}
