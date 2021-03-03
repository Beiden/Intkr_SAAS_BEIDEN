package com.intkr.saas.domain.bo.item;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.fs.ImgBO;
import com.intkr.saas.domain.bo.order.BidsBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.domain.bo.tag.TagBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.util.JsonUtil;
import com.intkr.saas.util.map.FourMap;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午10:15:32
 * @version 1.0
 */
public class DemandBO extends BaseBO {
	
	private Long saasId;

	private Long id;

	private Long shopId;

	private Long userId;

	private Long relatedId;

	private Integer inventory;

	private Integer inventoryCount;

	private String code;

	private String type;

	private String tradeMethod;

	private Long tradeMethodId;

	private Integer pinkage;

	private String paymentMethod;

	private String name;

	private String location;

	private String province;

	private String city;

	private String area;

	private String mapLocation;

	private Integer suttle;

	private Long price;

	private Long unitPrice;

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

	private List<ImgBO> imgs;

	private List<ItemFcategoryBO> categorys;

	private List<TagBO> tags;

	private AuctionBO auction;

	private BidsBO bids;

	private ShopBO shop;

	private UserBO user;

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

	public List<ImgBO> getImgs() {
		return imgs;
	}

	public void setImgs(List<ImgBO> imgs) {
		this.imgs = imgs;
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

	public Object getFeature(String key) {
		Map<String, Object> map = JsonUtil.parse(feature, Map.class);
		if (map == null) {
			return null;
		}
		return map.get(key);
	}

	public void setFeature(String key, Object value) {
		Map<String, Object> map = JsonUtil.parse(feature, Map.class);
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put(key, value);
		this.feature = JsonUtil.format(map);
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

	public List<ItemFcategoryBO> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<ItemFcategoryBO> categorys) {
		this.categorys = categorys;
	}

	public List<TagBO> getTags() {
		return tags;
	}

	public void setTags(List<TagBO> tags) {
		this.tags = tags;
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

	public List<FourMap> getFactor() {
		List<String> factors = (List<String>) getFeature("kww_factors");
		if (factors == null) {
			return new ArrayList<FourMap>();
		}
		List<FourMap> factorMap = new ArrayList<FourMap>();
		for (String factor : factors) {
			FourMap<String, String, String, Integer> map = new FourMap<String, String, String, Integer>();
			String[] values = factor.split("\\|");
			map.one = values[0];
			map.two = values[1];
			map.three = values[2];
			map.four = Integer.valueOf(values[3]);
			factorMap.add(map);
		}
		return factorMap;
	}

	public List<FourMap> getFactor2() {
		List<FourMap> returnFactorMap = new ArrayList<FourMap>();
		List<FourMap> factorMap = getFactor();
		for (FourMap<String, String, String, Integer> map : factorMap) {
			if (!map.two.endsWith("kww_factor_moisture") && !map.two.endsWith("kww_factor_granularity")) {
				returnFactorMap.add(map);
			}
		}
		return returnFactorMap;
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

	public AuctionBO getAuction() {
		return auction;
	}

	public void setAuction(AuctionBO auction) {
		this.auction = auction;
	}

	public BidsBO getBids() {
		return bids;
	}

	public void setBids(BidsBO bids) {
		this.bids = bids;
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

	public ShopBO getShop() {
		return shop;
	}

	public void setShop(ShopBO shop) {
		this.shop = shop;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public UserBO getUser() {
		return user;
	}

	public void setUser(UserBO user) {
		this.user = user;
	}

	public Long getFirstCategoryId() {
		return firstCategoryId;
	}

	public void setFirstCategoryId(Long firstCategoryId) {
		this.firstCategoryId = firstCategoryId;
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

	public void setThirdCategoryName(String thirdCategoryName) {
		this.thirdCategoryName = thirdCategoryName;
	}

	public String getFourthCategoryName() {
		return fourthCategoryName;
	}

	public void setFourthCategoryName(String fourthCategoryName) {
		this.fourthCategoryName = fourthCategoryName;
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

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
