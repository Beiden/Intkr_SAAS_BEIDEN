package com.intkr.saas.engine.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.intkr.saas.engine.webx.WebContext;

/**
 * 
 * @author beidenhuang
 */
public class Config {

	// URL权限配置
	public static List<AuthRule> urlRightRules = new ArrayList<AuthRule>();

	// Action权限配置
	public static List<AuthRule> actionRightRules = new ArrayList<AuthRule>();

	// PC登录页面
	public static String pcLoginUrl = "http://www.intkr.com/admin/sign/signIn.html";

	// App登录页面
	public static String appLoginUrl = "http://www.intkr.com/admin/sign/app/signIn.html";

	// 权限配置原始值
	public static volatile String authorityValve = null;

	private static volatile AtomicBoolean init = new AtomicBoolean(false);

	public static void init() {
		if (init.get()) {
			return;
		}
		synchronized (init) {
			if (init.get()) {
				return;
			}
			String authorityValve = (String) WebContext.contextParam.get("authorityValve");
			init(authorityValve);
			init.set(true);
		}
	}

	public static void init(String authorityValve) {
		Config.authorityValve = authorityValve;
		if (authorityValve != null) {
			String[] urlRights = authorityValve.split("\\n");
			for (String urlRight : urlRights) {
				if (urlRight == null || "".equals(urlRight.trim())) {
					continue;
				}
				if (urlRight.indexOf("#") != -1) {
					urlRight = urlRight.substring(0, urlRight.lastIndexOf("#"));
				}
				if (!urlRight.contains("=")) {
					continue;
				}
				String[] confs = urlRight.split("=");
				String key = confs[0].trim();
				if (key.startsWith("u:")) {
					AuthRule authRule = new AuthRule();
					authRule.setUri(key.substring(2));
					String conf = confs[1].trim();
					if (conf.contains("->")) {
						String[] confTmps = conf.split("->");
						authRule.setConf(confTmps[0].trim());
						authRule.setFalseConf(confTmps[1].trim());
					} else {
						authRule.setConf(conf);
					}
					Config.urlRightRules.add(authRule);
				} else if (key.startsWith("a:")) {
					AuthRule authRule = new AuthRule();
					authRule.setAction(key.substring(2));
					String conf = confs[1].trim();
					if (conf.contains("->")) {
						String[] confTmps = conf.split("->");
						authRule.setConf(confTmps[0].trim());
						authRule.setFalseConf(confTmps[1].trim());
					} else {
						authRule.setConf(conf);
					}
					Config.actionRightRules.add(authRule);
				} else if (key.equals("login-url")) {
					pcLoginUrl = confs[1].trim();
				} else if (key.equals("app-login-url")) {
					appLoginUrl = confs[1].trim();
				}
			}
		}
	}

}
