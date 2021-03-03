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
 * @date 2015-5-11 下午8:20:24
 * @version 1.0
 */
public class EmailVerifyCodeLocalCache {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static Cache<Long, Map<String, String>> emailVerifyCodeCache = CacheBuilder.newBuilder()//
			.expireAfterAccess(30, TimeUnit.MINUTES).//
			maximumSize(1000).//
			build();

	private static Random random = new Random();

	public static String getVerifyEmailCode(Long userId, String email) {
		Map<String, String> map = new HashMap<String, String>();
		String code = random.nextInt(999999) + "";
		map.put("code", code);
		map.put("email", email);
		emailVerifyCodeCache.put(userId, map);
		return code;
	}

	public static boolean isCodeValidate(Long userId, String code) {
		Map<String, String> map = get(userId);
		return map.get("code") != null && map.get("code").equals(code);
	}

	public static void remove(Long userId) {
		emailVerifyCodeCache.invalidate(userId);
	}

	public static void put(Long userId, Map<String, String> map) {
		emailVerifyCodeCache.put(userId, map);
	}

	public static Map<String, String> get(Long userId) {
		try {
			Map<String, String> map = emailVerifyCodeCache.get(userId, new Callable<Map<String, String>>() {
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
