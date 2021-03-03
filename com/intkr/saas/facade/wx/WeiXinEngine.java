package com.intkr.saas.facade.wx;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 
 * @author Beiden
 * @date 2016-6-8 上午11:48:24
 * @version 1.0
 */
public class WeiXinEngine {

	private static Cache<Long, String> tractionCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(3, TimeUnit.MINUTES).build();

	public static Long getTractionId() {
		Integer tractionId = new Random().nextInt(999999);
		tractionCache.put(tractionId.longValue(), "waiting");
		return tractionId.longValue();
	}

	public static String getTractionValue(Long tractionId) {
		if (tractionId == null) {
			return null;
		}
		String value = tractionCache.getIfPresent(tractionId);
		return value;
	}

	public static void updateTractionValue(Long tractionId, String value) {
		if (tractionId == null || value == null) {
			return;
		}
		tractionCache.put(tractionId, value);
	}

	public static boolean isTractionFinish(Long tractionId) {
		if (tractionId == null) {
			return false;
		}
		String value = getTractionValue(tractionId);
		return value != null && !"waiting".equals(value);
	}

}
