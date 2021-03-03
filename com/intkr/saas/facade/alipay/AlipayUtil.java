package com.intkr.saas.facade.alipay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.facade.alipay.lib.AlipayConfig;
import com.intkr.saas.facade.alipay.lib.AlipayNotify;

/**
 * 
 * @author Beiden
 * @date 2011-6-12 下午4:47:23
 * @version 1.0
 */
public class AlipayUtil {

	protected static final Logger logger = LoggerFactory.getLogger(AlipayUtil.class);

	/**
	 * 是否支付宝的正常回调
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAliapyCallback(HttpServletRequest request) {
		String charset = AlipayConfig.input_charset;
		return getParams(request, charset);
	}

	public static boolean isAliapyCallback(HttpServletRequest request, String charset) {
		return getParams(request, charset);
	}

	@SuppressWarnings("unchecked")
	private static boolean getParams(HttpServletRequest request, String charset) {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			try {
				if (charset != null) {
					valueStr = new String(valueStr.getBytes("ISO-8859-1"), charset);
				}
			} catch (Exception e) {
				logger.error(" ", e);
			}
			params.put(name, valueStr);
		}
		return AlipayNotify.verify(params);
	}

}
