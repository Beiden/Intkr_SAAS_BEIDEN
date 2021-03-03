package com.intkr.saas.module.action.payment;

import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.domain.type.order.OrderSubStatus;
import com.intkr.saas.domain.type.user.MoneyAccount;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.user.MoneyAccountFlowManager;
import com.intkr.saas.manager.user.WxAccountManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-4-27 下午3:09:44
 * @version 1.0
 */
public class WxPayAction {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private MoneyAccountFlowManager moneyFlowManager = IOC.get("MoneyAccountFlowManager");

	private OptionManager optionManager = IOC.get("OptionManager");

	private OrderManager orderManager = IOC.get(OrderManager.class);

	private WxAccountManager wxAccountManager = IOC.get("WxAccountManager");

	/**
	 * 微信支付的回调函数
	 */
	public String doCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 把如下代码贴到的你的处理回调的servlet 或者.do 中即可明白回调操作
		logger.info("微信支付回调数据开始");
		String orderId = RequestUtil.getParam(request, "orderId");
		logger.warn("orderId=" + orderId);
		String notityXml = readResponseXml(request);
		Map<String, String> map = parseXmlToList2(notityXml);
		logger.info(notityXml);
		String resXml = "";
		if ("SUCCESS".equals(map.get("result_code").toString())) {
			resXml = processSuccess(request, map);
		} else {
			resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		}
		logger.info("微信支付回调数据结束");
		logger.info(resXml);
		return resXml;
	}

	private String processSuccess(HttpServletRequest request, Map<String, String> responseMap) {
		String orderId = RequestUtil.getParam(request, "orderId");
		OrderBO orderBO = orderManager.get(orderId);
		if (orderBO != null) {
			if (OrderStatus.WaitPay.getCode().equals(orderBO.getStatus())) {
				orderBO.setStatus(OrderStatus.WaitSendOut.getCode());
			}
			orderBO.setSubStatus(OrderSubStatus.WeiXinAsynConfirmPay.getCode());
			if (orderBO.getPayTime() == null) {
				orderBO.setPayTime(new Date());
				orderBO.setPayment(MoneyAccount.Wxpay.getCode());
			}
			orderBO.setFeature("transaction_id", responseMap.get("transaction_id"));
			orderBO.setFeature("out_trade_no", responseMap.get("out_trade_no"));
			orderBO.setFeature("mch_id", responseMap.get("mch_id"));
			orderBO.setFeature("nonce_str", responseMap.get("nonce_str"));
			orderManager.update(orderBO);
		}
		// 支付成功
		String resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
		return resXml;
	}

	private String readResponseXml(HttpServletRequest request) {
		// 示例报文
		// String xml =
		// "<xml>
		// <appid><![CDATA[wxb4dc385f953b356e]]></appid>
		// <bank_type><![CDATA[CCB_CREDIT]]></bank_type>
		// <cash_fee><![CDATA[1]]></cash_fee>
		// <fee_type><![CDATA[CNY]]></fee_type>
		// <is_subscribe><![CDATA[Y]]></is_subscribe>
		// <mch_id><![CDATA[1228442802]]></mch_id>
		// <nonce_str><![CDATA[1002477130]]></nonce_str>
		// <openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid>
		// <out_trade_no><![CDATA[1000000000051249]]></out_trade_no>
		// <result_code><![CDATA[SUCCESS]]></result_code>
		// <return_code><![CDATA[SUCCESS]]></return_code>
		// <sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign>
		// <time_end><![CDATA[20150324100405]]></time_end>
		// <total_fee>1</total_fee>
		// <trade_type><![CDATA[JSAPI]]></trade_type>
		// <transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id>
		// </xml>";
		String inputLine;
		String notityXml = "";
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notityXml;
	}

	/**
	 * description: 解析微信通知xml
	 */
	private static Map<String, String> parseXmlToList2(String xml) {
		Map<String, String> retMap = new HashMap<String, String>();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc = (Document) sb.build(source);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					retMap.put(element.getName(), element.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

}
