package com.intkr.saas.module.screen.payment.jdpay;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;

import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.domain.type.order.OrderSubStatus;
import com.intkr.saas.domain.type.user.MoneyAccount;
import com.intkr.saas.facade.wx.model.AsynNotificationReqDto;
import com.intkr.saas.facade.wx.util.BASE64;
import com.intkr.saas.facade.wx.util.DESUtil;
import com.intkr.saas.facade.wx.util.JsonUtil;
import com.intkr.saas.facade.wx.util.PropertyUtils;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-13 下午7:02:26
 * @version 1.0
 */
public class JdpayCallbackAsync {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderManager orderManager = IOC.get(OrderManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info("**********接收异步通知开始。**********");
			// 获取通知原始信息
			String resp = request.getParameter("resp");

			logger.info("异步通知原始数据:" + resp);
			if (null == resp) {
				request.setAttribute("result", false);
				return;
			}
			// 获取配置密钥
			String desKey = PropertyUtils.getProperty("wepay.merchant.desKey");
			String md5Key = PropertyUtils.getProperty("wepay.merchant.md5Key");
			;
			logger.info("desKey:" + desKey);
			logger.info("md5Key:" + md5Key);
			try {
				// 首先对Base64编码的数据进行解密
				byte[] decryptBASE64Arr = BASE64.decode(resp);
				// 解析XML
				AsynNotificationReqDto dto = parseXML(decryptBASE64Arr);
				logger.info("解析XML得到对象:" + JsonUtil.write2JsonStr(dto));
				// 验证签名
				String ownSign = generateSign(dto.getVersion(), dto.getMerchant(), dto.getTerminal(), dto.getData(), md5Key);
				logger.info("根据传输数据生成的签名:" + ownSign);
				if (!dto.getSign().equals(ownSign)) {
					// 验签不对
					logger.info("签名验证错误!");
					throw new RuntimeException();
				} else {
					logger.info("签名验证正确!");
				}
				// 验签成功，业务处理
				// 对Data数据进行解密
				byte[] rsaKey = decryptBASE64(desKey);
				String decryptArr = DESUtil.decrypt(dto.getData(), rsaKey, "utf-8");

				processSuccess(decryptArr);

				// 解密出来的数据也为XML文档，可以用dom4j解析
				logger.info("对<DATA>进行解密得到的数据:" + decryptArr);
				dto.setData(decryptArr);
				logger.info("最终数据:" + JsonUtil.write2JsonStr(dto));
				// 这里处理业务逻辑

				logger.info("**********接收异步通知结束。**********");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
			}
		} catch (Exception e) {
			logger.error("", e);
			request.setAttribute("result", "fail");
		}
	}

	private void processSuccess(String decryptArr) {
		org.jsoup.nodes.Document doc = Jsoup.parse(decryptArr);
		String orderId = doc.select("DATA TRADE ID").text();
		String status = doc.select("DATA TRADE STATUS").text();
		if (!"0".equals(status)) {
			return;
		}
		OrderBO orderBO = orderManager.get(orderId);
		if (orderBO != null) {
			if (OrderStatus.WaitPay.getCode().equals(orderBO.getStatus())) {
				orderBO.setStatus(OrderStatus.WaitSendOut.getCode());
			}
			orderBO.setSubStatus(OrderSubStatus.JdpayAsynConfirmPay.getCode());
			if (orderBO.getPayTime() == null) {
				orderBO.setPayTime(new Date());
				orderBO.setPayment(MoneyAccount.Jdpay.getCode());
			}
			orderManager.update(orderBO);
		}
	}

	// XML解析为Java对象
	private static AsynNotificationReqDto parseXML(byte[] xmlString) {
		Document document = null;
		try {
			InputStream is = new ByteArrayInputStream(xmlString);
			SAXReader sax = new SAXReader(false);
			document = sax.read(is);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		AsynNotificationReqDto dto = new AsynNotificationReqDto();
		Element rootElement = document.getRootElement();
		if (null == rootElement) {
			return dto;
		}
		Element versionEliment = rootElement.element("VERSION");
		if (null != versionEliment) {
			dto.setVersion(versionEliment.getText());
		}
		Element merchantEliment = rootElement.element("MERCHANT");
		if (null != merchantEliment) {
			dto.setMerchant(merchantEliment.getText());
		}
		Element terminalEliment = rootElement.element("TERMINAL");
		if (null != terminalEliment) {
			dto.setTerminal(terminalEliment.getText());
		}
		Element datalEliment = rootElement.element("DATA");
		if (null != datalEliment) {
			dto.setData(datalEliment.getText());
		}
		Element signEliment = rootElement.element("SIGN");
		if (null != signEliment) {
			dto.setSign(signEliment.getText());
		}
		return dto;
	}

	// 对Base64进行解密
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * 签名
	 */
	public static String generateSign(String version, String merchant, String terminal, String data, String md5Key) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(version);
		sb.append(merchant);
		sb.append(terminal);
		sb.append(data);
		String sign = "";
		sign = md5(sb.toString(), md5Key);
		return sign;
	}

	public static String md5(String text, String salt) throws Exception {
		byte[] bytes = (text + salt).getBytes();

		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(bytes);
		bytes = messageDigest.digest();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				sb.append("0");
			}
			sb.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return sb.toString().toLowerCase();
	}

}
