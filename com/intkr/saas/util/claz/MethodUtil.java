package com.intkr.saas.util.claz;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodUtil {

	public static class ParamsType {
		public int index;
		public Class<?> paramsType;
		public List<Class<?>> paramsTypeList;

		public void setType(Class<?> claz) {
			paramsTypeList = new ArrayList<Class<?>>();
			paramsTypeList.add(claz);
			claz = claz.getSuperclass();
			while (!claz.equals(Object.class)) {
				paramsTypeList.add(claz);
				claz = claz.getSuperclass();
			}
		}
	}

	public static List<ParamsType> getParamsType(Class<?>... parameterTypes) {
		if (parameterTypes == null || parameterTypes.length == 0) {
			return new ArrayList<MethodUtil.ParamsType>();
		}
		List<ParamsType> list = new ArrayList<MethodUtil.ParamsType>();
		for (Class<?> claz : parameterTypes) {
			ParamsType type = new ParamsType();
			type.setType(claz);
			list.add(type);
		}
		return list;
	}

	public static Method getMethodR(Class<?> claz, String name, Class<?>... parameterTypes) {
		List<Class<?>[]> params = getMethodParams(parameterTypes);

		return null;
	}

	public static Method getMethodRF(Class<?> claz, String name, Class<?>... parameterTypes) throws NoSuchMethodException {
		List<Class<?>[]> paramsList = getMethodParams(parameterTypes);
		for (Class<?>[] params : paramsList) {
			try {
				Method method = claz.getMethod(name, params);
				return method;
			} catch (NoSuchMethodException e) {
			}
		}
		return getMethodRF(claz.getSuperclass(), name, paramsList);
	}

	public static Method getMethodRF(Class<?> claz, String name, List<Class<?>[]> paramsList) throws NoSuchMethodException {
		if (claz.equals(Object.class)) {
			throw new NoSuchMethodException(claz.getName() + "." + name);
		}
		for (Class<?>[] params : paramsList) {
			try {
				Method method = claz.getMethod(name, params);
				return method;
			} catch (NoSuchMethodException e) {
			}
		}
		return getMethodRF(claz.getSuperclass(), name, paramsList);
	}

	private static List<Class<?>[]> getMethodParams(Class<?>... parameterTypes) {
		List<ParamsType> paramTypeList = getParamsType(parameterTypes);
		List<List<Class<?>>> params = new ArrayList<List<Class<?>>>();
		for (int i = 0; i < paramTypeList.size(); i++) {
			ParamsType type = paramTypeList.get(i);
			if (params.isEmpty()) {
				init(params, type);
			} else {
				params = add(params, type);
			}
		}
		List<Class<?>[]> returnParams = new ArrayList<Class<?>[]>();
		for (List<Class<?>> tmp : params) {
			Class<?>[] pa = new Class<?>[tmp.size()];
			for (int i = 0; i < tmp.size(); i++) {
				pa[i] = tmp.get(i);
			}
			returnParams.add(pa);
		}
		return returnParams;
	}

	private static List<List<Class<?>>> add(List<List<Class<?>>> params, ParamsType type) {
		List<List<Class<?>>> returnParams = new ArrayList<List<Class<?>>>();
		for (Class<?> tmp : type.paramsTypeList) {
			for (List<Class<?>> list : params) {
				List<Class<?>> tmpList = new ArrayList<Class<?>>();
				tmpList.addAll(list);
				tmpList.add(tmp);
				returnParams.add(tmpList);
			}
		}
		return returnParams;
	}

	private static void init(List<List<Class<?>>> params, ParamsType type) {
		for (Class<?> tmp : type.paramsTypeList) {
			List<Class<?>> tmpList = new ArrayList<Class<?>>();
			tmpList.add(tmp);
			params.add(tmpList);
		}
	}

}
