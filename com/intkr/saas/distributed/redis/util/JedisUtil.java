package com.intkr.saas.distributed.redis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

/**
 * 
 * @author Beiden
 * @date 2018-1-14
 * @version 1.0
 */
public class JedisUtil {

	protected static Logger logger = LoggerFactory.getLogger(JedisUtil.class);

	public static void close(Jedis jedis) {
		if (jedis == null) {
			return;
		}
		try {
			jedis.close();
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
