package com.intkr.saas.util;

/**
 * 
 * @author Beiden
 * @date 2011-6-15 上午8:27:59
 * @version 1.0
 */
public class TypeUtil {

	public static boolean isPrimitiveType(Object object) {
		if (object instanceof Long) {
			return true;
		}
		if (object instanceof Integer) {
			return true;
		}
		if (object instanceof Boolean) {
			return true;
		}
		if (object instanceof Double) {
			return true;
		}
		if (object instanceof Float) {
			return true;
		}
		if (object instanceof String) {
			return true;
		}
		if (object instanceof Short) {
			return true;
		}
		if (object instanceof Byte) {
			return true;
		}
		return false;
	}

}
