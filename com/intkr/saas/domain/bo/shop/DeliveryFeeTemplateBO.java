package com.intkr.saas.domain.bo.shop;

import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.util.JsonUtil;

/**
 * 运费模版
 * 
 * @table delivery_fee_template
 * 
 * @author Beiden
 * @date 2021-01-18 18:32:16
 * @version 1.0
 */
public class DeliveryFeeTemplateBO extends BaseBO<DeliveryFeeTemplateBO> {

	private static final long serialVersionUID = 1L;

	//
	private Long saasId;

	// 店铺ID
	private Long shopId;

	// 名称
	private String name;

	// 宝贝地址
	private String address;

	// 发货时间
	private Integer sendTime;

	// 是否包邮
	private Integer freeShipping;

	// 计价方式
	private String valuateType;

	// 运送方式
	private String deliveryType;

	//
	private String feature;

	//
	private Double sort;

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getSendTime() {
		return sendTime;
	}

	public void setSendTime(Integer sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getFreeShipping() {
		return freeShipping;
	}

	public void setFreeShipping(Integer freeShipping) {
		this.freeShipping = freeShipping;
	}

	public String getValuateType() {
		return valuateType;
	}

	public void setValuateType(String valuateType) {
		this.valuateType = valuateType;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
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

	public String getProvince() {
		if (address == null) {
			return null;
		}
		if (!address.contains(",")) {
			return null;
		}
		String[] datas = address.split(",");
		if (datas.length != 3) {
			return null;
		}
		return datas[0];
	}

	public String getCity() {
		if (address == null) {
			return null;
		}
		if (!address.contains(",")) {
			return null;
		}
		String[] datas = address.split(",");
		if (datas.length != 3) {
			return null;
		}
		return datas[1];
	}

	public String getArea() {
		if (address == null) {
			return null;
		}
		if (!address.contains(",")) {
			return null;
		}
		String[] datas = address.split(",");
		if (datas.length != 3) {
			return null;
		}
		return datas[2];
	}

	public Double caculate(Integer count) {
		if (freeShipping == 1) {
			return 0d;
		}

		String start = deliveryType;
		String type = "";
		if ("count".equals(valuateType)) {
			type = "Count";
		} else if ("weight".equals(deliveryType)) {
			type = "Weight";
		} else if ("volumn".equals(deliveryType)) {
			type = "Volumn";
		}
		Double startCount = (Double) getFeature(start + "DefaultFeeStart" + type);
		Double startCountPrice = (Double) getFeature(start + "DefaultFeeStart" + type + "Price");
		Double countStep = (Double) getFeature(start + "DefaultFee" + type + "Step");
		Double countStepPrice = (Double) getFeature(start + "DefaultFee" + type + "StepPrice");
		if (count <= startCount) {
			return startCountPrice;
		}
		return startCountPrice + Math.ceil((count - startCount) / countStep) * countStepPrice;
	}

}
