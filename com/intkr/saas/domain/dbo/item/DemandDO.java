package com.intkr.saas.domain.dbo.item;

import java.util.Date;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-15 上午11:11:10
 * @version 1.0
 */
public class DemandDO extends BaseDO {

	private Long id;

	private Long shopId;

	private Long relatedId;

	/**
	 * 编号
	 */
	private String code;

	/**
	 * 商品类型
	 * 
	 * @ItemType
	 */
	private String type;

	/**
	 * 交易方式
	 * 
	 * @TradeMethod
	 */
	private String tradeMethod;

	private Long tradeMethodId;

	private Integer pinkage;

	private String paymentMethod;

	private Long userId;

	private String name;

	private Long price;

	private Long unitPrice;

	private String location;

	private String province;

	private String city;

	private String area;

	private String mapLocation;

	private Integer suttle;

	private String status;

	private String imgIds;

	private String content;

	private String feature;

	private Long sellCount;

	private Integer viewCount;

	private Integer commentCount;

	private Integer praiseUpCount;

	private Integer praiseDownCount;

	private Integer likeCount;

	private Integer collectCount;

	private Integer attentionCount;

	private Date inProcessStartTime;

	private Date inProcessEndTime;

	private Date preHeatStartTime;

	private Date preHeatEndTime;

	private Long priceStep;

	private String result;

	private String param;

	private Date resultTime;

	private Integer inventory;

	private Integer inventoryCount;

	private Integer evaluateCount;

	private Double goodEvaluateRate;

	private Double middleEvaluateRate;

	private Double badEvaluateRate;

	private Long firstCategoryId;

	private String firstCategoryName;

	private Long secondCategoryId;

	private String secondCategoryName;

	private Long thirdCategoryId;

	private String thirdCategoryName;

	private Long fourthCategoryId;

	private String fourthCategoryName;

	private Long fifthCategoryId;

	private String fifthCategoryName;

	private String mineName;

	private String createMethod;

	private String packageMethod;

	private String invoiceMethod;

	private Double weightError;

	private Double testError;

	private Integer containTax;

	private Integer storeTime;

	private Date deliveryTime;

	private Long supervisorShopId;

	private String supervisorShopName;

	private Long supervisorUserId;

	private String supervisorUserName;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

	public Long getSellCount() {
		return sellCount;
	}

	public void setSellCount(Long sellCount) {
		this.sellCount = sellCount;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getPraiseUpCount() {
		return praiseUpCount;
	}

	public void setPraiseUpCount(Integer praiseUpCount) {
		this.praiseUpCount = praiseUpCount;
	}

	public Integer getPraiseDownCount() {
		return praiseDownCount;
	}

	public void setPraiseDownCount(Integer praiseDownCount) {
		this.praiseDownCount = praiseDownCount;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	public Integer getAttentionCount() {
		return attentionCount;
	}

	public void setAttentionCount(Integer attentionCount) {
		this.attentionCount = attentionCount;
	}

	public Date getInProcessStartTime() {
		return inProcessStartTime;
	}

	public void setInProcessStartTime(Date inProcessStartTime) {
		this.inProcessStartTime = inProcessStartTime;
	}

	public Date getInProcessEndTime() {
		return inProcessEndTime;
	}

	public void setInProcessEndTime(Date inProcessEndTime) {
		this.inProcessEndTime = inProcessEndTime;
	}

	public Date getPreHeatStartTime() {
		return preHeatStartTime;
	}

	public void setPreHeatStartTime(Date preHeatStartTime) {
		this.preHeatStartTime = preHeatStartTime;
	}

	public Date getPreHeatEndTime() {
		return preHeatEndTime;
	}

	public void setPreHeatEndTime(Date preHeatEndTime) {
		this.preHeatEndTime = preHeatEndTime;
	}

	public Long getPriceStep() {
		return priceStep;
	}

	public void setPriceStep(Long priceStep) {
		this.priceStep = priceStep;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Date getResultTime() {
		return resultTime;
	}

	public void setResultTime(Date resultTime) {
		this.resultTime = resultTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTradeMethod() {
		return tradeMethod;
	}

	public void setTradeMethod(String tradeMethod) {
		this.tradeMethod = tradeMethod;
	}

	public Integer getPinkage() {
		return pinkage;
	}

	public void setPinkage(Integer pinkage) {
		this.pinkage = pinkage;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMapLocation() {
		return mapLocation;
	}

	public void setMapLocation(String mapLocation) {
		this.mapLocation = mapLocation;
	}

	public Integer getSuttle() {
		return suttle;
	}

	public void setSuttle(Integer suttle) {
		this.suttle = suttle;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public String getMineName() {
		return mineName;
	}

	public void setMineName(String mineName) {
		this.mineName = mineName;
	}

	public String getCreateMethod() {
		return createMethod;
	}

	public void setCreateMethod(String createMethod) {
		this.createMethod = createMethod;
	}

	public String getPackageMethod() {
		return packageMethod;
	}

	public void setPackageMethod(String packageMethod) {
		this.packageMethod = packageMethod;
	}

	public String getInvoiceMethod() {
		return invoiceMethod;
	}

	public void setInvoiceMethod(String invoiceMethod) {
		this.invoiceMethod = invoiceMethod;
	}

	public Double getWeightError() {
		return weightError;
	}

	public void setWeightError(Double weightError) {
		this.weightError = weightError;
	}

	public Double getTestError() {
		return testError;
	}

	public void setTestError(Double testError) {
		this.testError = testError;
	}

	public Integer getContainTax() {
		return containTax;
	}

	public void setContainTax(Integer containTax) {
		this.containTax = containTax;
	}

	public Integer getStoreTime() {
		return storeTime;
	}

	public void setStoreTime(Integer storeTime) {
		this.storeTime = storeTime;
	}

	public Integer getEvaluateCount() {
		return evaluateCount;
	}

	public void setEvaluateCount(Integer evaluateCount) {
		this.evaluateCount = evaluateCount;
	}

	public Double getGoodEvaluateRate() {
		return goodEvaluateRate;
	}

	public void setGoodEvaluateRate(Double goodEvaluateRate) {
		this.goodEvaluateRate = goodEvaluateRate;
	}

	public Double getMiddleEvaluateRate() {
		return middleEvaluateRate;
	}

	public void setMiddleEvaluateRate(Double middleEvaluateRate) {
		this.middleEvaluateRate = middleEvaluateRate;
	}

	public Double getBadEvaluateRate() {
		return badEvaluateRate;
	}

	public void setBadEvaluateRate(Double badEvaluateRate) {
		this.badEvaluateRate = badEvaluateRate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}

	public Long getTradeMethodId() {
		return tradeMethodId;
	}

	public void setTradeMethodId(Long tradeMethodId) {
		this.tradeMethodId = tradeMethodId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstCategoryName() {
		return firstCategoryName;
	}

	public void setFirstCategoryName(String firstCategoryName) {
		this.firstCategoryName = firstCategoryName;
	}

	public String getSecondCategoryName() {
		return secondCategoryName;
	}

	public void setSecondCategoryName(String secondCategoryName) {
		this.secondCategoryName = secondCategoryName;
	}

	public String getThirdCategoryName() {
		return thirdCategoryName;
	}

	public String getFourthCategoryName() {
		return fourthCategoryName;
	}

	public void setFourthCategoryName(String fourthCategoryName) {
		this.fourthCategoryName = fourthCategoryName;
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

	public Long getFourthCategoryId() {
		return fourthCategoryId;
	}

	public void setFourthCategoryId(Long fourthCategoryId) {
		this.fourthCategoryId = fourthCategoryId;
	}

	public Long getFifthCategoryId() {
		return fifthCategoryId;
	}

	public void setFifthCategoryId(Long fifthCategoryId) {
		this.fifthCategoryId = fifthCategoryId;
	}

	public void setThirdCategoryName(String thirdCategoryName) {
		this.thirdCategoryName = thirdCategoryName;
	}

	public String getFifthCategoryName() {
		return fifthCategoryName;
	}

	public void setFifthCategoryName(String fifthCategoryName) {
		this.fifthCategoryName = fifthCategoryName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Long getSupervisorShopId() {
		return supervisorShopId;
	}

	public void setSupervisorShopId(Long supervisorShopId) {
		this.supervisorShopId = supervisorShopId;
	}

	public String getSupervisorShopName() {
		return supervisorShopName;
	}

	public void setSupervisorShopName(String supervisorShopName) {
		this.supervisorShopName = supervisorShopName;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Long getSupervisorUserId() {
		return supervisorUserId;
	}

	public void setSupervisorUserId(Long supervisorUserId) {
		this.supervisorUserId = supervisorUserId;
	}

	public String getSupervisorUserName() {
		return supervisorUserName;
	}

	public void setSupervisorUserName(String supervisorUserName) {
		this.supervisorUserName = supervisorUserName;
	}

	public Long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(Integer inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

}
