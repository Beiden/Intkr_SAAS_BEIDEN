package com.intkr.saas.domain.type.order;

/**
 * 订单明细类型
 * 
 * @author Beiden
 * @date 2011-8-22 上午8:09:57
 * @version 1.0
 */
public enum OrderType {

	Item("item", "商品购买"), //
	FundRecharge("fundRecharge", "充值"), //
	MerchantFreezeDeposit("merchantFreezeDeposit", "交易会员资金冻结"), //
	LogisticsFreezeDeposit("logisticsFreezeDeposit", "物流方资金冻结"), //
	TestingFreezeDeposit("testingFreezeDeposit", "检测方资金冻结"), //
	FincialFreezeDeposit("fincialFreezeDeposit", "金融方资金冻结"), //
	Demand("demand", "需求"), //
	Error("error", "异常");

	public String code;

	public String name;

	private OrderType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static OrderType getByNameEn(String nameEn) {
		if (nameEn == null || "".equals(nameEn)) {
			return OrderType.Error;
		}
		return OrderType.Error;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static OrderType getByCode(String code) {
		OrderType[] values = values();
		for (OrderType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
