package com.intkr.saas.engine;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.sns.MsgTemplateBO;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.sns.MsgSignatureManager;
import com.intkr.saas.manager.sns.MsgTemplateManager;
import com.intkr.saas.util.IpUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.TemplateUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-8 下午1:36:59
 * @version 1.0
 */
public class SmsCheckCodeEngine {

	protected static final Logger logger = LoggerFactory.getLogger(SmsCheckCodeEngine.class);

	/**
	 * key：code；value：phone
	 */
	private static Cache<String, String> phoneVerifyCodeCache = CacheBuilder.newBuilder()//
			.expireAfterAccess(30, TimeUnit.MINUTES).//
			maximumSize(1000).//
			build();

	private static Random random = new Random();

	private static DecimalFormat df = new DecimalFormat("000000");

	private static MsgSignatureManager msgSignatureManager = IOC.get("sns.MsgSignatureManager");

	private static MsgTemplateManager snsMsgTemplateManager = IOC.get("sns.MsgTemplateManager");

	private static OptionManager optionManager = IOC.get("OptionManager");

	// 发送手机验证码
	public static void sendCheckCode(HttpServletRequest request, HttpServletResponse response) {
		if (!CheckCodeEngine.check(request, "checkCode")) {
			request.setAttribute("msg", "验证码错误，请点击验证码刷新，重新输入！");
			request.setAttribute("result", false);
			return;
		}
		String ip = IpUtil.getIp(request);
		if (!Controler.canSend(ip)) {
			request.setAttribute("msg", "验证码发送太频繁，请稍后再试！");
			request.setAttribute("result", false);
			return;
		} else {
			Controler.send(ip);
		}
		request.getSession().removeAttribute("checkCode");
		String phone = RequestUtil.getParam(request, "phone");
		if (phone == null || "".equals(phone) || phone.length() != 11) {
			request.setAttribute("msg", "请输入正确的手机号码！");
			request.setAttribute("result", false);
			return;
		}
		sendVerifyCode(SessionClient.getSaas(request).getId(), phone);
		request.setAttribute("result", true);
		request.setAttribute("msg", "验证码已发送！");
	}

	// 发送验证码
	private static String sendVerifyCode(Long saasId, String phone) {
		if (phone == null || "".equals(phone) || phone.length() != 11) {
			return null;
		}
		if (!Controler.canSend(phone)) {
			return null;
		} else {
			Controler.send(phone);
		}

		String checkCode = createCheckCode(phone);
		Map<String, String> param = new HashMap<String, String>();
		param.put("signature", msgSignatureManager.getDefault(saasId));
		param.put("checkCode", checkCode);
		MsgTemplateBO template = snsMsgTemplateManager.getByCode(saasId, "check-code");
		String content = TemplateUtil.merge(template.getContent(), param);
		SmsEngine.send(saasId, phone, content);
		return content;
	}

	private static String createCheckCode(String phone) {
		String code = df.format(random.nextInt(999999)) + "";
		phoneVerifyCodeCache.put(code, phone);
		return code;
	}

	public static boolean isCodeValidate(String code, String phone) {
		String phoneCache = getPhone(code);
		if (phoneCache == null || "".equals(phoneCache)) {
			return false;
		}
		return phoneCache.equals(phone);
	}

	public static void removeCode(String code) {
		phoneVerifyCodeCache.invalidate(code);
	}

	public static String getPhone(String code) {
		return phoneVerifyCodeCache.getIfPresent(code);
	}

	// 60秒几不允许手机重复发送短信
	static class Controler {
		private static Cache<String, Date> phoneControlerCache = CacheBuilder.newBuilder()//
				.expireAfterAccess(1, TimeUnit.MINUTES).//
				maximumSize(100000).//
				build();

		public static boolean canSend(String phone) {
			Date date = phoneControlerCache.getIfPresent(phone);
			if (date != null) {
				return false;
			}
			return true;
		}

		public static void send(String phone) {
			phoneControlerCache.put(phone, new Date());
		}

	}

}
