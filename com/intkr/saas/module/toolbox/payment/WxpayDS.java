package com.intkr.saas.module.toolbox.payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.module.action.payment.WxPayAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 */
public class WxpayDS extends BaseToolBox {

	public String callback(HttpServletRequest request, HttpServletResponse response) {
		try {
			WxPayAction action = IOC.get(WxPayAction.class);
			String result = action.doCallback(request, response);
			return result;
		} catch (Exception e) {
			logger.error("微信支付回调异常：", e);
			String resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			return resXml;
		}
	}

}
