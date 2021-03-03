package com.intkr.saas.conf;

/**
 * 
 * @author Beiden
 */
public class Conf {

	// 启动方式：CMS ; SAAS_CMS ; SAAS_ALL
	private static String app = "CMS";

	private static String isSaas = "Yes";

	// 是否使用主题模版
	private static String useTheme = "Yes";

	static {
		isSaas = SystemProperties.getProperty("system.isSaas", "No");
		useTheme = SystemProperties.getProperty("system.useTheme", "No");
		app = SystemProperties.getProperty("system.app", "CMS");
	}

	public static String getApp() {
		return app;
	}

	public static boolean isSaas() {
		return isSaas.equals("Yes");
	}

	public static boolean useTheme() {
		return useTheme.equals("Yes");
	}

}
