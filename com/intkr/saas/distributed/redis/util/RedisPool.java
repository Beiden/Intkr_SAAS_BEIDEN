package com.intkr.saas.distributed.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @author Beiden
 * @date 2018-1-12
 * @version 1.0
 */
public class RedisPool {

	private JedisPool pool;

	private String redisHost;

	private Integer redisPort;

	private String redisPassword;

	private Integer timeout = 60 * 30;

	// 设置最大连接数
	private Integer maxTotal = 100;

	// 设置最大阻塞时间，记住是毫秒数milliseconds
	private Integer maxWaitMillis = 1000;

	// 设置空间连接
	private Integer maxIdle = 10;

	private synchronized void initReal() {
		if (pool != null) {
			return;
		}
		JedisPoolConfig config = new JedisPoolConfig();// 建立连接池配置参数
		config.setMaxTotal(maxTotal);
		config.setMaxWaitMillis(maxWaitMillis);
		config.setMaxIdle(maxIdle);
		pool = new JedisPool(config, redisHost, redisPort, timeout, redisPassword);// 创建连接池
	}

	private void init() {
		if (pool == null) {
			initReal();
		}
	}

	public Jedis getJedis() {
		init();
		Jedis jedis = pool.getResource();
		if (redisPassword != null && !"".equals(redisPassword)) {
			jedis.auth(redisPassword);
		}
		return jedis;
	}

	public JedisPool getPool() {
		return pool;
	}

	public void setPool(JedisPool pool) {
		this.pool = pool;
	}

	public String getRedisHost() {
		return redisHost;
	}

	public void setRedisHost(String redisHost) {
		this.redisHost = redisHost;
	}

	public Integer getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(Integer redisPort) {
		this.redisPort = redisPort;
	}

	public String getRedisPassword() {
		return redisPassword;
	}

	public void setRedisPassword(String redisPassword) {
		this.redisPassword = redisPassword;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Integer getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	public Integer getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public void setMaxWaitMillis(Integer maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public Integer getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

}
