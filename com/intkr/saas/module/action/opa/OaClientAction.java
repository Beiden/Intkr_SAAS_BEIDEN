package com.intkr.saas.module.action.opa;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.opa.OaClientBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.opa.OaClientManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.AESUtil;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 客户端
 * 
 * @table oa_client
 * 
 * @author Beiden
 * @date 2020-11-04 20:18:06
 * @version 1.0
 */
public class OaClientAction extends BaseAction<OaClientBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OaClientManager oaClientManager = IOC.get(OaClientManager.class);

	public static Cache<String, Long> skeyClientCache = CacheBuilder.newBuilder()//
			.expireAfterAccess(30, TimeUnit.MINUTES).//
			maximumSize(10000).//
			build();

	public OaClientBO getBO(HttpServletRequest request) {
		OaClientBO oaClientBO = getParameter(request);
		return oaClientBO;
	}

	public static OaClientBO getParameter(HttpServletRequest request) {
		OaClientBO oaClientBO = new OaClientBO();
		oaClientBO.setId(RequestUtil.getParam(request, "id", Long.class));
		oaClientBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		oaClientBO.setName(RequestUtil.getParam(request, "name", String.class));
		oaClientBO.setStatus(RequestUtil.getParam(request, "status", String.class));
		oaClientBO.setAppKey(RequestUtil.getParam(request, "appKey", String.class));
		oaClientBO.setAppSecret(RequestUtil.getParam(request, "appSecret", String.class));
		oaClientBO.setNote(RequestUtil.getParam(request, "note", String.class));
		oaClientBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, oaClientBO);
		return oaClientBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return oaClientManager;
	}

	public void doGetAccessToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String appKey = RequestUtil.getParam(request, "appKey", String.class);
		String appSecret = RequestUtil.getParam(request, "appSecret", String.class);
		Long saasId = SessionClient.getSaasId(request);
		OaClientBO query = new OaClientBO();
		query.setSaasId(saasId);
		query.setAppKey(appKey);
		query.setAppSecret(appSecret);
		OaClientBO client = oaClientManager.selectOne(query);
		if (client == null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "认证失败！");
			return;
		}
		String skey = getSkey(client.getId());
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("access_token", skey);
		request.setAttribute("result", true);
		request.setAttribute("data", data);
		request.setAttribute("msg", "认证成功！");
	}

	public static boolean isValidate(Long saasId, String accessToken) {
		return skeyClientCache.getIfPresent(accessToken) != null;
	}

	public static String getSkey(Long clientId) {
		if (clientId == null || "".equals(clientId)) {
			return null;
		}
		Date now = new Date();
		String dateString = DateUtil.format("yyMMddHHmm", now);
		String skey = AESUtil.AESEncode("Intkr", clientId.toString() + "|" + dateString);
		skeyClientCache.put(skey, clientId);
		return skey;
	}

	public static Long getClientId(String accessToken) {
		if (accessToken == null || "".equals(accessToken)) {
			return null;
		}
		return skeyClientCache.getIfPresent(accessToken);
		// return getClientIdByDecode(accessToken);
	}

	private static Long getClientIdByDecode(String accessToken) {
		try {
			String content = AESUtil.AESDncode("Intkr", accessToken);
			String[] values = content.split("\\|");
			if (outOfTime(values[1])) {// skey超时
				return null;
			}
			return Long.valueOf(values[0]);
		} catch (Exception e) {
			return null;
		}
	}

	private static boolean outOfTime(String timeString) {
		Date time = DateUtil.parse(timeString, "yyMMddHHmm");
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.MINUTE, 30);
		Date lastTime = c.getTime();
		Date now = new Date();
		if (now.after(lastTime)) {
			return true;
		}
		return false;
	}

}
