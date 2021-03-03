package com.intkr.saas.module.toolbox.theme;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.intkr.saas.engine.url.UrlParamEngine;
import com.intkr.saas.module.action.conf.theme.ThemeAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.vm.VmFileUtil;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class LayoutDS extends BaseToolBox {

	private static Cache<String, String> layoutCache = CacheBuilder.newBuilder()//
			.expireAfterAccess(30, TimeUnit.MINUTES).//
			maximumSize(10000).//
			build();

	public String getLayout(HttpServletRequest request) {
		return getThemeLayout(request);
	}

	public String getThemeLayout(HttpServletRequest request) {
		String url = request.getRequestURI();
		if ("/".equals(url) || "".equals(url)) {
			url = "/index.html";
		}
		url = UrlParamEngine.removeParams(url);
		String themeName = ThemeAction.getThemeName(request);
		final String key = themeName + ":" + url;
		try {
			return layoutCache.get(key, new Callable<String>() {
				public String call() throws Exception {
					return get(key);
				}
			});
		} catch (Exception e) {
			logger.error("", e);
		}
		return "";
	}

	private String get(String key) {
		try {
			String themeName = key.split(":")[0];
			String uri = key.split(":")[1];

			String fileName = uri.substring(0, uri.lastIndexOf("."));
			if (fileName.startsWith("/")) {
				fileName = fileName.substring(1);
			}
			String onePageLayoutFile = "/screen/themes/" + themeName + "/" + fileName + "_layout.vm";
			if (themeName == null || "".equals(themeName) || "null".equalsIgnoreCase(themeName)) {
				onePageLayoutFile = "/screen/" + fileName + "_layout.vm";
			}
			if (VmFileUtil.isExistVM(onePageLayoutFile)) {
				return onePageLayoutFile;
			}

			String baseLocation = "/screen/themes/" + themeName;
			if (themeName == null || "".equals(themeName) || "null".equalsIgnoreCase(themeName)) {
				baseLocation = "/screen";
			}
			String layout = getLayout(uri, baseLocation);

			return layout == null ? "" : layout;
		} catch (Exception e) {
			logger.error("key=" + key, e);
			return "";
		}
	}

	private static String getLayout(String uri, String baseLocation) {
		String directory = getDirectory(uri);
		do {
			String layoutFile = baseLocation + directory + "/_layout.vm";
			if (VmFileUtil.isExistVM(layoutFile)) {
				return layoutFile;
			} else if ("".equals(directory)) {
				break;
			}
			directory = directory.substring(0, directory.lastIndexOf("/"));
		} while (true);
		return null;
	}

	private static String getDirectory(String uri) {
		String directory = null;
		if (uri.lastIndexOf("/") > 0) {
			directory = uri.substring(0, uri.lastIndexOf("/"));
		} else {
			return "";
		}
		return directory;
	}

}
