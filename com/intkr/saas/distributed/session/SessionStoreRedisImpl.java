package com.intkr.saas.distributed.session;

import static java.util.Collections.emptyList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.alibaba.citrus.service.requestcontext.session.SessionConfig;
import com.alibaba.citrus.service.requestcontext.session.SessionStore;
import com.intkr.saas.distributed.redis.facade.RedisFacade;
import com.intkr.saas.util.SerializeUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 存放在Redis中的session attribute的机制。
 * 
 * @author Beiden
 * @date 2017-10-21
 * @version 1.0
 */
public class SessionStoreRedisImpl implements SessionStore {

	private Integer seconds = 60 * 30;

	private RedisFacade cacheClient;

	public void init(String storeName, SessionConfig sessionConfig) {
		cacheClient = IOC.get("CacheClient");
	}

	/** 取得指定session ID的所有值。 */
	public Map<String, Object> getSession(String sessionID) {
		String sessionKey = "session-" + sessionID;
		Map<byte[], byte[]> map = cacheClient.hgetAll(sessionKey.getBytes());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		for (byte[] key : map.keySet()) {
			Object obj = SerializeUtil.unserialize(map.get(key));
			returnMap.put(new String(key), obj);
		}
		cacheClient.expire(sessionKey.getBytes(), seconds);
		return returnMap;
	}

	/** 取得指定session的所有attribute名称。 */
	public Iterable<String> getAttributeNames(String sessionID, StoreContext storeContext) {
		String sessionKey = "session-" + sessionID;
		Map<byte[], byte[]> map = cacheClient.hgetAll(sessionKey.getBytes());
		cacheClient.expire(sessionKey.getBytes(), seconds);
		if (map == null) {
			return emptyList();
		} else {
			Set<String> returnSet = new HashSet<String>();
			for (byte[] key : map.keySet()) {
				returnSet.add(new String(key));
			}
			return returnSet;
		}
	}

	/** 装载指定session的某个attribute。 */
	public Object loadAttribute(String attrName, String sessionID, StoreContext storeContext) {
		try {
			String sessionKey = "session-" + sessionID;
			byte[] value = cacheClient.hget(sessionKey.getBytes(), attrName.getBytes());
			cacheClient.expire(sessionKey.getBytes(), seconds);
			if (value == null) {
				return null;
			} else {
				return SerializeUtil.unserialize(value);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** 丢弃指定session ID的所有内容。 */
	public void invaldiate(String sessionID, StoreContext storeContext) {
		String sessionKey = "session-" + sessionID;
		cacheClient.del(sessionKey.getBytes());
	}

	/** 保存指定session的attributes。attrs为<code>null</code>表示删除。 */
	public void commit(Map<String, Object> modifiedAttrs, String sessionID, StoreContext storeContext) {
		String sessionKey = "session-" + sessionID;
		for (Map.Entry<String, Object> entry : modifiedAttrs.entrySet()) {
			String attrName = entry.getKey();
			Object attrValue = entry.getValue();
			if (attrValue == null) {
				cacheClient.hdel(sessionKey.getBytes(), attrName.getBytes());
			} else {
				cacheClient.hset(sessionKey.getBytes(), attrName.getBytes(), SerializeUtil.serialize(attrValue));
			}
		}
		cacheClient.expire(sessionKey.getBytes(), seconds);
	}

	public String toString() {
		return "SessionStoreRedisImpl[jedis=" + cacheClient + "]";
	}

	public RedisFacade getCacheClient() {
		return cacheClient;
	}

	public void setCacheClient(RedisFacade cacheClient) {
		this.cacheClient = cacheClient;
	}

}
