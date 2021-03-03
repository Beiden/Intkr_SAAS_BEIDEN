package com.intkr.saas.util.claz;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.dao.conf.IdDAO;
import com.intkr.saas.engine.spring.SpringEngine;

/**
 * 
 * @author Beiden
 * @date 2011-7-8 下午3:25:18
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class IOC {

	protected static final Logger logger = LoggerFactory.getLogger(IOC.class);

	public static Map<String, Object> objectPool = new ConcurrentHashMap<String, Object>();

	public static void main(String[] args) {
		IdDAO obj = IOC.get("IkIdDAO");
		System.out.println(obj);
		System.out.println(obj.select().size());
	}

	public static <T> T tryGet(String clazName) {
		Throwable springException = null;
		try {
			T t = null;
			try {
				t = SpringEngine.getBean(clazName);
			} catch (Throwable springExceptionTmp) {
				springException = springExceptionTmp;
			}
			if (t != null) {
				objectPool.put(clazName, t);
				return t;
			}
			Class<?> claz = ClassUtil.getClass(clazName);
			return (T) get(claz);
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> T get(String clazName) {
		Throwable springException = null;
		try {
			T t = null;
			try {
				t = SpringEngine.getBean(clazName);
			} catch (Throwable springExceptionTmp) {
				springException = springExceptionTmp;
			}
			if (t != null) {
				objectPool.put(clazName, t);
				return t;
			}
			Class<?> claz = ClassUtil.getClass(clazName);
			return (T) get(claz);
		} catch (Exception e) {
			if (springException != null) {
				logger.error("clazName=" + clazName, springException);
			}
			logger.error("clazName=" + clazName, e);
			throw new RuntimeException(e);
		}
	}

	public static <T> T get(Class<T> claz) {
		Throwable springException = null;
		String className = claz.getName();
		try {
			T t = SpringEngine.getBean(claz);
			if (t != null) {
				objectPool.put(className, t);
				return t;
			}
		} catch (Exception e) {
			springException = e;
		}
		if (objectPool.containsKey(className)) {
			return (T) objectPool.get(className);
		} else {
			try {
				T t = claz.newInstance();
				objectPool.put(className, t);
				return t;
			} catch (Exception e) {
				if (springException != null) {
					logger.error("clazName=" + claz.getName(), springException);
				}
				logger.error("clazName=" + claz.getName(), e);
				throw new RuntimeException("claz=" + claz.getName(), e);
			}
		}
	}

	public static <T> T tryGet(Class<T> claz) {
		Throwable springException = null;
		String className = claz.getName();
		try {
			T t = SpringEngine.getBean(claz);
			if (t != null) {
				objectPool.put(className, t);
				return t;
			}
		} catch (Exception e) {
			springException = e;
		}
		if (objectPool.containsKey(className)) {
			return (T) objectPool.get(className);
		} else {
			try {
				T t = claz.newInstance();
				objectPool.put(className, t);
				return t;
			} catch (Exception e) {
				return null;
			}
		}
	}

}
