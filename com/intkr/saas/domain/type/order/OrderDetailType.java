package com.intkr.saas.domain.type.order;

/**
 * 
 * @author Beiden
 * @date 2016-3-6 下午5:48:06
 * @version 1.0
 */
public enum OrderDetailType {

	Article("article", "文章"), //
	Logistics("logistics", "物流服务"), //
	Item("item", "商品"), //
	Bids("bids", "招标"), //
	Auction("auction", "竞拍"), //
	Demand("denamd", "需求"), //
	Page("page", "页面"), //
	FundRecharge("fundRecharge", "充值"), //
	MerchantFreezeDeposit("merchantFreezeDeposit", "交易会员资金冻结"), //
	LogisticsFreezeDeposit("logisticsFreezeDeposit", "物流方资金冻结"), //
	TestingFreezeDeposit("testingFreezeDeposit", "检测方资金冻结"), //
	FincialFreezeDeposit("fincialFreezeDeposit", "金融方资金冻结"), //
	Error("errot", "异常"); //

	private OrderDetailType(String code, String name) {
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
	
	public static OrderDetailType getByCode(String code) {
		OrderDetailType[] values = values();
		for (OrderDetailType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
