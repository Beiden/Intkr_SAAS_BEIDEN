package com.intkr.saas.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.toolbox.BaseToolBox;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-27 下午03:14:08
 * @version 1.0
 */
public class MapUtil extends BaseToolBox {

	protected final static Logger logger = LoggerFactory.getLogger(MapUtil.class);

	/**
	 * 对象转map，浅转换，只转一层。
	 * 
	 * @param object
	 * @return
	 */
	public static Map<String, Object> toMap(Object object) {
		if (object == null) {
			return new HashMap<String, Object>();
		} else if (TypeUtil.isPrimitiveType(object)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(object.getClass().getName(), object);
			return map;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Class<?>> clazs = new ArrayList<Class<?>>();
		{
			Class<?> clazz = object.getClass();
			clazs.add(clazz);
			while (!clazz.getName().equals(Object.class.getName())) {
				clazz = clazz.getSuperclass();
				clazs.add(0, clazz);
			}
		}
		for (Class<?> clazz2 : clazs) {
			Field[] fields = clazz2.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					Object value = field.get(object);
					map.put(field.getName(), value);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		return map;
	}

	public Map<String, Object> newMap() {
		return new HashMap<String, Object>();
	}

	public Map<String, Object> toMap(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			map.put(key, request.getParameter(key));
		}
		return map;
	}

}
