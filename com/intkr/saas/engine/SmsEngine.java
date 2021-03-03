package com.intkr.saas.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.type.sns.MsgChannel;
import com.intkr.saas.domain.type.sns.MsgStatus;
import com.intkr.saas.domain.type.sns.MsgType;
import com.intkr.saas.facade.sms.SmsFacadeLuosimaoImpl;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-8 下午1:36:59
 * @version 1.0
 */
public class SmsEngine {

	protected static final Logger logger = LoggerFactory.getLogger(SmsEngine.class);

	private static OptionManager optionManager = IOC.get("OptionManager");

	private static SmsFacadeLuosimaoImpl phoneMsgClientLuosimaoImpl = IOC.get(SmsFacadeLuosimaoImpl.class);

	private static MsgManager msgManager = IOC.get(MsgManager.class);

	// 发送短信
	public static boolean send(Long saasId, String phone, String message) {
		if (phone == null || "".equals(phone) || phone.length() != 11) {
			return false;
		}
		logger.info("phone=" + phone + ";content=" + message);
		if (!"1".equals(optionManager.getValueByName(saasId, "phone_msg_send_api_flag"))) {
			log(saasId, phone, message, MsgStatus.Pending.getCode());
			return true;
		}
		log(saasId, phone, message, MsgStatus.Sended.getCode());
		return sendByLuosimao(saasId, phone, message);
	}

	private static void log(Long saasId, String phone, String message, String status) {
		MsgBO msg = new MsgBO();
		msg.setSaasId(saasId);
		msg.setId(msgManager.getId());
		msg.setChannel(MsgChannel.Phone.getCode());
		msg.setToPhone(phone);
		msg.setContent(message);
		msg.setType(MsgType.System.getCode());
		msg.setStatus(status);
		msgManager.insert(msg);
	}

	private static boolean sendByLuosimao(Long saasId, String phone, String message) {
		String api = optionManager.getValueByName(saasId, "phone_msg_setting_luosimao_api");
		return phoneMsgClientLuosimaoImpl.send(api, phone, message);
	}

}
