package com.intkr.saas.domain.dbo.item;

import java.util.Date;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2011-10-31 下午7:49:16
 * @version 1.0
 */
public class AuctionDO extends BaseDO {

	private Long id;

	private Long relatedId;

	/**
	 * 商品类型
	 * 
	 * @ItemType
	 */
	private String type;

	private Long userId;

	private String name;

	private Long startPrice;

	private String status;

	private String imgIds;

	private String content;

	private String feature;

	private Integer viewCount;

	private Integer attentionCount;

	private Date inProcessStartTime;

	private Date inProcessEndTime;

	private Date preHeatStartTime;

	private Date preHeatEndTime;

	private Long priceStep;

	private String resultId;

	private String str1;

	private String str2;

	private String str3;

	private String str4;

	private String str5;

	private String str6;

	private String str7;

	private String str8;

	private String str9;

	private String text1;

	private String text2;

	private String text3;

	private Double dou1;

	private Double dou2;

	private Double dou3;

	private Integer integer1;

	private Integer integer2;

	private Integer integer3;

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

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
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

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
	}

	public String getStr4() {
		return str4;
	}

	public void setStr4(String str4) {
		this.str4 = str4;
	}

	public String getStr5() {
		return str5;
	}

	public void setStr5(String str5) {
		this.str5 = str5;
	}

	public String getStr6() {
		return str6;
	}

	public void setStr6(String str6) {
		this.str6 = str6;
	}

	public String getStr7() {
		return str7;
	}

	public void setStr7(String str7) {
		this.str7 = str7;
	}

	public String getStr8() {
		return str8;
	}

	public void setStr8(String str8) {
		this.str8 = str8;
	}

	public String getStr9() {
		return str9;
	}

	public void setStr9(String str9) {
		this.str9 = str9;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public String getText3() {
		return text3;
	}

	public void setText3(String text3) {
		this.text3 = text3;
	}

	public Double getDou1() {
		return dou1;
	}

	public void setDou1(Double dou1) {
		this.dou1 = dou1;
	}

	public Double getDou2() {
		return dou2;
	}

	public void setDou2(Double dou2) {
		this.dou2 = dou2;
	}

	public Double getDou3() {
		return dou3;
	}

	public void setDou3(Double dou3) {
		this.dou3 = dou3;
	}

	public Integer getInteger1() {
		return integer1;
	}

	public void setInteger1(Integer integer1) {
		this.integer1 = integer1;
	}

	public Integer getInteger2() {
		return integer2;
	}

	public void setInteger2(Integer integer2) {
		this.integer2 = integer2;
	}

	public Integer getInteger3() {
		return integer3;
	}

	public void setInteger3(Integer integer3) {
		this.integer3 = integer3;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Long startPrice) {
		this.startPrice = startPrice;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

}
