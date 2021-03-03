package com.intkr.saas.domain.type.fs;

/**
 * 
 * @author Beiden
 * @date 2016-6-4 上午11:16:35
 * @version 1.0
 */
public enum MediaGroupName {

	PaymentEvidence("paymentEvidence", "付款凭证"), //
	SentOutEvidence("sentOutEvidence", "发货凭证"), //
	MerchantAuth("merchantAuth", "商家认证"), //
	LogisticsAuth("logisticsAuth", "物流方认证"), //
	FinancialAuth("financialAuth", "金融方认证"), //
	TestingAuth("testingAuth", "检测方认证"), //
	OrderTimeLine("orderTimeLine", "订单时间轴"), //
	Error("error", "异常");

	public String code;

	public String name;

	private MediaGroupName(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static MediaGroupName getByNameEn(String nameEn) {
		if (nameEn == null || "".equals(nameEn)) {
			return MediaGroupName.Error;
		}
		return MediaGroupName.Error;
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

	public static MediaGroupName getByCode(String code) {
		MediaGroupName[] values = values();
		for (MediaGroupName value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
