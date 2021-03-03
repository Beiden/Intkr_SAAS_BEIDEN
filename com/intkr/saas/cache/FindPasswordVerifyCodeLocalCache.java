package com.intkr.saas.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 
 * @author Beiden
 * @date 2015-5-22 下午9:40:00
 * @version 1.0
 */
public class FindPasswordVerifyCodeLocalCache {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static Cache<String, Map<String, String>> findPasswordVerifyCodeCache = CacheBuilder.newBuilder()//
			.expireAfterAccess(30, TimeUnit.MINUTES).//
			maximumSize(1000).//
			build();

	private static Random random = new Random();

	public static String getfindPasswordCode(String email) {
		Map<String, String> map = new HashMap<String, String>();
		String code = random.nextInt(999999) + "";
		map.put("code", code);
		map.put("email", email);
		findPasswordVerifyCodeCache.put(code, map);
		return code;
	}

	public static String getEmail(String code) {
		Map<String, String> map = get(code);
		return map.get("email");
	}

	public static boolean isCodeValidate(String email, String code) {
		Map<String, String> map = get(code);
		return map.get("code") != null && map.get("code").equals(code);
	}

	public static void remove(String code) {
		findPasswordVerifyCodeCache.invalidate(code);
	}

	public static void put(String email, Map<String, String> map) {
		findPasswordVerifyCodeCache.put(email, map);
	}

	public static Map<String, String> get(String email) {
		try {
			Map<String, String> map = findPasswordVerifyCodeCache.get(email, new Callable<Map<String, String>>() {
				public Map<String, String> call() throws Exception {
					return new HashMap<String, String>();
				}
			});
			return map;
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

}
