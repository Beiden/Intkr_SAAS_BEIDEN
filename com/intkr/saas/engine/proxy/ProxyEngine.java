package com.intkr.saas.engine.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

/**
 * 
 * @author Beiden
 * @date 2018-4-1
 * @version 1.0
 */
public class ProxyEngine {

	public static Map<String, Object> proxyCache = new HashMap<String, Object>();

	public static Object getInstance(InvocationHandler handler, Class[] cls) {
		String key = handler.getClass().getName();
		if (cls != null && cls.length > 0) {
			for (Class clas : cls) {
				key += "-" + clas.getName();
			}
		}
		if (proxyCache.containsKey(key) && proxyCache.get(key) != null) {
			return (T) proxyCache.get(key);
		}
		Object newProxyInstance = Proxy.newProxyInstance(ProxyEngine.class.getClassLoader(), cls, handler);
		proxyCache.put(key, newProxyInstance);
		return newProxyInstance;
	}

	public static <T> T getInstance(InvocationHandler handler, Class<T> cls) {
		return (T) getInstance(handler, new Class[] { cls });
	}

}