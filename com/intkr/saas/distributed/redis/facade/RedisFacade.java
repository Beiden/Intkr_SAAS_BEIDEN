package com.intkr.saas.distributed.redis.facade;

import java.util.Map;

import redis.clients.jedis.Jedis;

import com.intkr.saas.distributed.redis.util.JedisUtil;
import com.intkr.saas.distributed.redis.util.RedisPool;

/**
 * 
 * @author Beiden
 * @date 2017-10-23
 * @version 1.0
 */
public class RedisFacade {

	private String appName;

	private RedisPool redisPool;

	public RedisPool getRedisPool() {
		return redisPool;
	}

	public void setRedisPool(RedisPool redisPool) {
		this.redisPool = redisPool;
	}

	public Map<byte[], byte[]> hgetAll(byte[] key) {
		Jedis jedis = null;
		try {
			jedis = redisPool.getJedis();
			Map<byte[], byte[]> map = jedis.hgetAll(key);
			return map;
		} finally {
			JedisUtil.close(jedis);
		}
	}

	public byte[] hget(byte[] key, byte[] field) {
		Jedis jedis = null;
		try {
			jedis = redisPool.getJedis();
			byte[] result = jedis.hget(key, field);
			return result;
		} finally {
			JedisUtil.close(jedis);
		}
	}

	public String get(String key) {
		Jedis jedis = null;
		try {
			jedis = redisPool.getJedis();
			String result = jedis.get(key);
			return result;
		} finally {
			JedisUtil.close(jedis);
		}
	}

	public Long del(byte[] key) {
		Jedis jedis = null;
		try {
			jedis = redisPool.getJedis();
			Long result = jedis.del(key);
			return result;
		} finally {
			JedisUtil.close(jedis);
		}
	}

	public Long del(String key) {
		Jedis jedis = null;
		try {
			jedis = redisPool.getJedis();
			Long result = jedis.del(key);
			return result;
		} finally {
			JedisUtil.close(jedis);
		}
	}

	public Long expire(byte[] key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = redisPool.getJedis();
			Long result = jedis.expire(key, seconds);
			return result;
		} finally {
			JedisUtil.close(jedis);
		}
	}

	public Long expire(String key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = redisPool.getJedis();
			Long result = jedis.expire(key, seconds);
			return result;
		} finally {
			JedisUtil.close(jedis);
		}
	}

	public Long hdel(byte[] key, byte[] fields) {
		Jedis jedis = null;
		try {
			jedis = redisPool.getJedis();
			Long result = jedis.hdel(key, fields);
			return result;
		} finally {
			JedisUtil.close(jedis);
		}
	}

	public Long hset(byte[] key, byte[] field, byte[] value) {
		Jedis jedis = null;
		try {
			jedis = redisPool.getJedis();
			Long result = jedis.hset(key, field, value);
			return result;
		} finally {
			JedisUtil.close(jedis);
		}
	}

	public String set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = redisPool.getJedis();
			String status = jedis.set(key, value);
			return status;
		} finally {
			JedisUtil.close(jedis);
		}
	}

	public Jedis getJedis() {
		return redisPool.getJedis();
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}
