package com.intkr.saas.module.toolbox.conf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.conf.OptionBO;
import com.intkr.saas.domain.bo.order.PaymentBO;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.order.PaymentManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.JsonUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class OptionDS extends BaseToolBox {

	private OptionManager optionManager = IOC.get("OptionManager");

	private PaymentManager paymentManager = IOC.get("PaymentManager");

	public OptionBO get(Long saasId, String code) {
		return optionManager.getByName(saasId, code);
	}

	public String getValue(Long saasId, String code) {
		OptionBO option = optionManager.getByName(saasId, code);
		if (option != null) {
			return option.getValue();
		}
		return null;
	}

	public OptionBO get(ServletRequest request, String code) {
		if (SessionClient.getSaas(request) == null) {
			return null;
		}
		return optionManager.getByName(SessionClient.getSaas(request).getId(), code);
	}

	public OptionBO get(String code) {
		return optionManager.getByName(-1l, code);
	}

	public String getValue(ServletRequest request, String code) {
		OptionBO option = get(request, code);
		if (option == null) {
			return null;
		}
		return option.getValue();
	}

	public String getValue(String code) {
		OptionBO option = get(code);
		if (option == null) {
			return null;
		}
		return option.getValue();
	}

	public String getValue(ServletRequest request, String code, String defaultValue) {
		String value = getValue(request, code);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	public Object getJsonValue(ServletRequest request, String code) {
		Object returnDate = null;
		String value = getValue(request, code);
		try {
			if (value.startsWith("{")) {
				returnDate = JsonUtil.parse(value, Map.class);
			} else {
				returnDate = JsonUtil.parse(value, List.class);
			}
		} catch (Exception e) {
			return value;
		}
		return returnDate;
	}

	public String getValue(String code, String defaultValue) {
		String value = getValue(code);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	public Map<String, PaymentBO> getPaymentConf(HttpServletRequest request) {
		Long saasId = SessionClient.getSaasId(request);
		Map<String, PaymentBO> map = new HashMap<String, PaymentBO>();
		map.put("alipayConf", paymentManager.getAlipay(saasId));
		map.put("jdpayConf", paymentManager.getJdpay(saasId));
		map.put("wxpayConf", paymentManager.getWxpay(saasId));
		map.put("yuepayConf", paymentManager.getYuePay(saasId));
		map.put("offLinePayConf", paymentManager.getOffLinePay(saasId));
		map.put("bankPayConf", paymentManager.getBankPay(saasId));
		return map;
	}

}
