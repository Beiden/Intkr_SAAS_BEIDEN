package com.intkr.saas.module.action.conf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.conf.CmsConf;
import com.intkr.saas.domain.bo.conf.OptionBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.JsonUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-5-7 下午12:46:55
 * @version 1.0
 */
public class OptionAction extends BaseAction<OptionBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OptionManager optionManager = IOC.get(OptionManager.class);

	public OptionBO getBO(HttpServletRequest request) {
		OptionBO optionBO = getParameter(request);
		return optionBO;
	}

	private static String getKey(HttpServletRequest request) {
		if (RequestUtil.existParam(request, "key")) {
			return RequestUtil.getParam(request, "key");
		}
		if (RequestUtil.existParam(request, "code")) {
			return RequestUtil.getParam(request, "code");
		}
		if (RequestUtil.existParam(request, "name")) {
			return RequestUtil.getParam(request, "name");
		}
		return null;
	}

	public static OptionBO getParameter(HttpServletRequest request) {
		OptionBO optionBO = new OptionBO();
		optionBO.setId(RequestUtil.getParam(request, "id", Long.class));
		optionBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		optionBO.setType(RequestUtil.getParam(request, "type", String.class));
		optionBO.setName(getKey(request));
		optionBO.setValue(RequestUtil.getParam(request, "value"));
		PagerUtil.initPage(request, optionBO);
		return optionBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return optionManager;
	}

	public void doRecover(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long saasId = SessionClient.getSaasId(request, -1L);
		String name = getKey(request);
		OptionBO option = optionManager.getByName(saasId, name);
		if (option != null) {
			optionManager.delete(option.getId());
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "恢复成功");
	}

	public void doSetValue(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long saasId = SessionClient.getSaasId(request, -1L);
		String type = "value";
		String name = getKey(request);
		String value = RequestUtil.getParam(request, "value");
		if (name == null || "".equals(name)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请设置code值！");
		}
		set(saasId, type, name, value);
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

	public void doGetValue(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = getKey(request);
		OptionBO option = optionManager.getByName(SessionClient.getSaas(request).getId(), name);
		Map<String, Object> data = new HashMap<String, Object>();
		if (option != null) {
			data.put("id", option.getId());
			data.put("saasId", option.getSaasId());
			data.put("type", option.getType());
			data.put("name", option.getName());
			data.put("value", option.getValue());
			data.put("autoload", option.getAutoload());
			data.put("gmtCreated", DateUtil.formatDateTime(option.getGmtCreated()));
			data.put("gmtModified", DateUtil.formatDateTime(option.getGmtModified()));
		}
		request.setAttribute("data", data);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功");
	}

	public void doSetJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long saasId = SessionClient.getSaasId(request, -1L);
		String type = "json";
		String name = getKey(request);
		String value = RequestUtil.getParam(request, "value");
		if (name == null || "".equals(name)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请设置code值！");
		}
		set(saasId, type, name, value);
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

	public void doGetJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = getKey(request);
		OptionBO option = optionManager.getByName(SessionClient.getSaas(request).getId(), name);
		Map<String, Object> data = new HashMap<String, Object>();
		if (option != null) {
			data.put("id", option.getId());
			data.put("saasId", option.getSaasId());
			data.put("type", option.getType());
			data.put("name", option.getName());
			if (option.getType() == null || "".equals(option.getType()) || "value".equals(option.getType())) {
				data.put("value", option.getValue());
			} else {
				try {
					if (option.getValue().startsWith("{")) {
						Map value = JsonUtil.parse(option.getValue(), Map.class);
						data.put("value", value);
					} else {
						List value = JsonUtil.parse(option.getValue(), List.class);
						data.put("value", value);
					}
				} catch (Exception e) {
					data.put("value", option.getValue());
				}
			}
			data.put("autoload", option.getAutoload());
			data.put("gmtCreated", DateUtil.formatDateTime(option.getGmtCreated()));
			data.put("gmtModified", DateUtil.formatDateTime(option.getGmtModified()));
		}
		request.setAttribute("data", data);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功");
	}

	/**
	 * 设置
	 */
	public void doSet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] names = RequestUtil.existParam(request, "code") ? request.getParameterValues("code") : request.getParameterValues("name");
		if (names != null && names.length > 0) {
			for (String name : names) {
				String value = RequestUtil.getParam(request, name);
				set(SessionClient.getSaasId(request, -1L), "value", name, value);
			}
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

	/**
	 * 权限验证是否打开
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doSetRightAuth(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OptionBO option = optionManager.getByName(SessionClient.getSaas(request).getId(), "authority_open");
		if (option == null) {
			option = new OptionBO();
			option.setSaasId(SessionClient.getSaasId(request));
			option.setId(optionManager.getId());
			option.setName("authority_open");
			option.setValue("1");
			option.setAutoload(1);
			optionManager.insert(option);
		}
		if ("1".equals(option.getValue())) {
			option.setValue("2");
		} else {
			option.setValue("1");
		}
		optionManager.update(option);
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

	/**
	 * 是否自动采集权限点
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doSetRightAutoCollect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OptionBO option = optionManager.getByName(SessionClient.getSaas(request).getId(), "auto_collect_right");
		if (option == null) {
			option = new OptionBO();
			option.setId(optionManager.getId());
			option.setName("auto_collect_right");
			option.setSaasId(SessionClient.getSaas(request).getId());
			option.setValue("2");
			option.setAutoload(1);
			optionManager.insert(option);
		}
		if ("1".equals(option.getValue())) {
			option.setValue("2");
		} else {
			option.setValue("1");
		}
		optionManager.update(option);
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

	/**
	 * 手机消息接口是否打开
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doSetSendPhoneMsgFlag(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OptionBO option = optionManager.getByName(SessionClient.getSaas(request).getId(), "phone_msg_send_api_flag");
		if (option == null) {
			option = new OptionBO();
			option.setSaasId(SessionClient.getSaasId(request));
			option.setId(optionManager.getId());
			option.setName("phone_msg_send_api_flag");
			option.setValue("2");
			option.setAutoload(1);
			optionManager.insert(option);
		}
		if ("1".equals(option.getValue())) {
			option.setValue("2");
		} else {
			option.setValue("1");
		}
		optionManager.update(option);
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

	private OptionBO set(Long saasId, String type, String name, String value) {
		OptionBO option = optionManager.getByName(saasId, name);
		if (option != null) {
			option.setValue(value);
			optionManager.update(option);
		} else {
			option = new OptionBO();
			option.setSaasId(saasId);
			option.setType(type);
			option.setName(name);
			option.setValue(value);
			optionManager.insert(option);
		}
		return option;
	}

	public void doGetByName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = RequestUtil.existParam(request, "code") ? RequestUtil.getParam(request, "code") : RequestUtil.getParam(request, "name");
		OptionBO option = optionManager.getByName(SessionClient.getSaas(request).getId(), name);
		request.setAttribute("option", option);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功");
	}

	public void doDeleteByName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = RequestUtil.existParam(request, "code") ? RequestUtil.getParam(request, "code") : RequestUtil.getParam(request, "name");
		if (name == null || !name.startsWith("theme_")) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "name不符合规范，格式：theme_*");
			return;
		}
		OptionBO option = optionManager.getByName(SessionClient.getSaas(request).getId(), name);
		if (option != null) {
			optionManager.delete(option.getId());
			request.setAttribute("option", option);
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "删除成功");
	}

	/**
	 * 日志监控
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doSetSystemLogFalg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OptionBO option = optionManager.getByName(SessionClient.getSaasId(request), "system_log");
		if (option == null) {
			option = new OptionBO();
			option.setSaasId(SessionClient.getSaasId(request));
			option.setId(optionManager.getId());
			option.setName("system_log");
			option.setValue("1");
			option.setAutoload(1);
			optionManager.insert(option);
		}
		if ("1".equals(option.getValue())) {
			option.setValue("2");
		} else {
			option.setValue("1");
		}
		optionManager.update(option);
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

	/**
	 * 用户日志监控
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doSetUserLogFalg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OptionBO option = optionManager.getByName(SessionClient.getSaasId(request), "user_log");
		if (option == null) {
			option = new OptionBO();
			option.setSaasId(SessionClient.getSaasId(request));
			option.setId(optionManager.getId());
			option.setName("user_log");
			option.setValue("1");
			option.setAutoload(1);
			optionManager.insert(option);
		}
		if ("1".equals(option.getValue())) {
			option.setValue("2");
		} else {
			option.setValue("1");
		}
		optionManager.update(option);
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

	public void doSetAdminPhone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String phone = RequestUtil.getParam(request, "phone", "");
		OptionBO option = optionManager.getByName(-1l, CmsConf.adminPhone);
		if (option == null) {
			option = new OptionBO();
			option.setSaasId(-1l);
			option.setId(optionManager.getId());
			option.setName(CmsConf.adminPhone);
			option.setValue("");
			option.setAutoload(1);
			optionManager.insert(option);
		}
		option.setValue(phone);
		optionManager.update(option);
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

	public void doGetAdminPhone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String phone = optionManager.getValueByName(-1l, CmsConf.adminPhone);
		request.setAttribute("data", phone);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功");
	}

}
