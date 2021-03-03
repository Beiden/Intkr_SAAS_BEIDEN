package com.intkr.saas.facade.wxpay;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.order.PaymentBO;
import com.intkr.saas.facade.wxpay.WXPayConstants.SignType;
import com.intkr.saas.manager.order.PaymentManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 开发文档：https://pay.weixin.qq.com/wiki/doc/api/index.html
 * 
 * @author Beiden
 */
public class WxPayEngine {

	public static Logger logger = LoggerFactory.getLogger(WxPayEngine.class);

	public static Map<Long, WXPay> saasIdWxPayMap = new HashMap<Long, WXPay>();

	public static WXPay getWxPay(Long saasId) {
		if (!saasIdWxPayMap.containsKey(saasId)) {
			initWxPay(saasId);
		}
		WXPay wxpay = saasIdWxPayMap.get(saasId);
		if (wxpay.getConfig() == null) {
			logger.error("saasId=" + saasId + ":微信支付 未配置！");
			return null;
		}
		return wxpay;
	}

	private static void initWxPay(Long saasId) {
		try {
			WXPayConfig config = new WXPayConfig();
			{
				PaymentManager paymentManager = IOC.get("PaymentManager");
				PaymentBO payment = paymentManager.getWxpay(saasId);
				if (payment == null) {
					saasIdWxPayMap.put(saasId, new WXPay(null));
				}
				config.setAppId((String) payment.getFeature("app_id"));
				config.setAppSecret((String) payment.getFeature("app_secret"));
				config.setMchId((String) payment.getFeature("partner"));
				config.setMchKey((String) payment.getFeature("partner_key"));

				// config.setAppId("wx6887c749b105fcd8");
				// config.setAppSecret("gfdsafdsafdgfdsafdsagfdsafdsagdf");
				// config.setMchId("1302655001");
				// config.setMchKey("gfdsafdsafdgfdsafdsagfdsafdsagdf");
			}
			WXPayRequest wxPayRequest = new WXPayRequest(config);
			WXPay wxPay = new WXPay(config, wxPayRequest);
			saasIdWxPayMap.put(saasId, wxPay);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static class PayRequest {
		public Long saasId;
		// 用户ID
		public String openid;
		// 商品描述
		public String body;
		// 商家订单号
		public String out_trade_no;
		// 设备信息
		public String device_info = "";
		// 支付币种
		public String fee_type = "CNY";
		// 支付金额：单位：分
		public Long total_fee;
		//
		public String spbill_create_ip = "127.0.0.1";
		// 回调URL
		public String notify_url = "http://demo.shop.intkr.com/payment/wxpay/wxpayCallbackAsync.html";
		// 交易类型
		public String trade_type = "NATIVE";
		// 商品ID
		public String product_id;
		// 场景信息
		public String scene_info;
	}

	public static class PayResponse {
		public String appid;
		public String mch_id;
		public String device_info;
		public String nonce_str;
		public String sign;

		public String trade_type;

		// 返回码
		public String result_code;

		// return_msg
		public String return_msg;
		// err_code
		public String err_code;
		// err_code_des
		public String err_code_des;

		// 扫码支付url
		public String code_url;
		// Jsapi支付prepay_id
		public String prepay_id;
		// H5支付 mweb_url
		public String mweb_url;

		public boolean isSuccess() {
			return "SUCCESS".equals(result_code);
		}

		public JsapiPayInfo jsapiPayInfo;
	}

	public static class JsapiPayInfo {
		public String appId;
		public String timeStamp;
		public String nonceStr;
		public String packagez;
		public String signType;
		public String paySign;
	}

	public static PayResponse getH5PayInfo(PayRequest request) {
		request.trade_type = "MWEB";
		request.scene_info = "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"https://pay.qq.com\",\"wap_name\": \"腾讯充值\"}}";
		return getPayInfo(request);
	}

	public static PayResponse getNativePayInfo(PayRequest request) {
		request.trade_type = "NATIVE";
		return getPayInfo(request);
	}

	public static PayResponse getJsapiPayInfo(PayRequest request) {
		request.trade_type = "JSAPI";
		PayResponse response = getPayInfo(request);
		if (!response.isSuccess()) {
			return response;
		}
		try {
			WXPay wxpay = getWxPay(request.saasId);
			WXPayConfig config = wxpay.getConfig();

			Map<String, String> payInfo = new HashMap<String, String>();
			payInfo.put("appId", response.appid);
			payInfo.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
			payInfo.put("nonceStr", WXPayUtil.generateNonceStr());
			payInfo.put("package", "prepay_id=" + response.prepay_id);
			payInfo.put("signType", "HMACSHA256");
			String paySign = WXPayUtil.generateSignature(payInfo, config.getMchKey(), SignType.HMACSHA256);
			payInfo.put("paySign", paySign);

			response.jsapiPayInfo = new JsapiPayInfo();
			response.jsapiPayInfo.appId = payInfo.get("appId");
			response.jsapiPayInfo.timeStamp = payInfo.get("timeStamp");
			response.jsapiPayInfo.nonceStr = payInfo.get("nonceStr");
			response.jsapiPayInfo.packagez = payInfo.get("package");
			response.jsapiPayInfo.signType = payInfo.get("signType");
			response.jsapiPayInfo.paySign = payInfo.get("paySign");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return response;
	}

	private static PayResponse getPayInfo(PayRequest request) {
		try {
			WXPay wxpay = getWxPay(request.saasId);
			Map<String, String> param = new HashMap<String, String>();
			if (request.openid != null) {
				param.put("openid", request.openid);
			}
			if (request.body != null) {
				param.put("body", request.body);
			}
			if (request.out_trade_no != null) {
				param.put("out_trade_no", request.out_trade_no);
			}
			if (request.device_info != null) {
				param.put("device_info", request.device_info);
			}
			if (request.fee_type != null) {
				param.put("fee_type", request.fee_type);
			}
			if (request.total_fee != null) {
				param.put("total_fee", request.total_fee + "");
			}
			if (request.spbill_create_ip != null) {
				param.put("spbill_create_ip", request.spbill_create_ip);
			}
			if (request.notify_url != null) {
				param.put("notify_url", request.notify_url);
			}
			if (request.trade_type != null) {
				param.put("trade_type", request.trade_type);
			}
			if (request.product_id != null) {
				param.put("product_id", request.product_id);
			}
			Map<String, String> result = wxpay.unifiedOrder(param);
			PayResponse response = new PayResponse();
			response.result_code = result.get("result_code");
			response.err_code = result.get("err_code");
			response.err_code_des = result.get("err_code_des");
			response.return_msg = result.get("return_msg");

			response.appid = result.get("appid");
			response.mch_id = result.get("mch_id");
			response.device_info = result.get("device_info");
			response.nonce_str = result.get("nonce_str");
			response.sign = result.get("sign");
			response.trade_type = result.get("trade_type");

			response.code_url = result.get("code_url");
			response.prepay_id = result.get("prepay_id");
			response.mweb_url = result.get("mweb_url");
			return response;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		nativePayTest();
		jsapiPayTest();
		h5PayTest();
	}

	private static void nativePayTest() {
		PayRequest payRequest = new PayRequest();
		payRequest.body = "商品描述测试";// 订单描述
		payRequest.out_trade_no = "12345678"; // 订单ID
		payRequest.total_fee = 1L;// 价格
		payRequest.product_id = "12345678";// 商品ID
		PayResponse result = WxPayEngine.getNativePayInfo(payRequest);
		System.out.println(result);
	}

	private static void jsapiPayTest() {
		PayRequest payRequest = new PayRequest();
		payRequest.openid = "o9DRLwPU-uz72H-2E4-DQ7PbGakI";// 用户ID
		payRequest.body = "商品描述测试";// 订单描述
		payRequest.out_trade_no = "12345678"; // 订单ID
		payRequest.total_fee = 1L;// 价格
		payRequest.product_id = "12345678";// 商品ID
		PayResponse result = WxPayEngine.getJsapiPayInfo(payRequest);
		System.out.println(result);
	}

	private static void h5PayTest() {
		PayRequest payRequest = new PayRequest();
		payRequest.openid = "o9DRLwPU-uz72H-2E4-DQ7PbGakI";// 用户ID
		payRequest.body = "商品描述测试";// 订单描述
		payRequest.out_trade_no = "12345678"; // 订单ID
		payRequest.total_fee = 1L;// 价格
		payRequest.product_id = "12345678";// 商品ID
		PayResponse result = WxPayEngine.getJsapiPayInfo(payRequest);
		System.out.println(result);
	}

}
