package com.intkr.saas.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.intkr.saas.module.toolbox.BaseToolBox;

/**
 * 
 * @author Beiden
 * @date 2011-7-6 上午11:22:49
 * @version 1.0
 */
public class JsonUtil extends BaseToolBox {

	protected static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private static Gson gson = new Gson();

	public static <T extends Object> T parse(String json, Class<T> clasz) {
		return toObject(json, clasz);
	}

	public static <T extends Object> T toObject(String json, Class<T> clasz) {
		return gson.fromJson(json, clasz);
	}

	public static String format(Object obj) {
		return toJson(obj);
	}

	public static String toJson(Object obj) {
		try {
			return gson.toJson(obj);
		} catch (Throwable e) {
			return null;
		}
	}

	public static boolean isJsonString(String json, Class clasz) {
		try {
			toObject(json, clasz);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
