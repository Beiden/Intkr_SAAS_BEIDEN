package com.intkr.saas.domain.type.sns;

/**
 * 
 * @author Beiden
 * @date 2016-4-14 上午8:19:48
 * @version 1.0
 */
public enum PaticipateType {

	Article("article", "文章"), //
	Item("item", "商品"), //
	Demand("demand", "需求"), //
	Auction("auction", "竞拍"), //
	AuctionHit("auctionHit", "竞拍中标"), //
	Bids("bids", "招标"), //
	TestBids("testBids", "检测招标"), //
	LogisticsBids("logisticsBids", "物流招标"), //
	BidsHit("bidsHit", "招标中标"), //
	Page("page", "页面"), //
	Error("error", "异常"); //

	private PaticipateType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	private String code;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static PaticipateType getByCode(String code) {
		PaticipateType[] values = values();
		for (PaticipateType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
