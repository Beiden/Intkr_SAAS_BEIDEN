package com.intkr.saas.domain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 遵守规范：bo包在do类的包下
 * 
 * @author beidenhuang
 * @datetime 2011-10-27 下午12:49:55
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class Transfer<VO extends BaseVO<VO>, BO extends BaseBO<BO>, DO extends BaseDO<DO>> {

	protected final static Logger logger = LoggerFactory.getLogger(Transfer.class);

	public static <BO, DO> DO toDOByField(BO boObject) {
		if (boObject == null) {
			return null;
		}
		try {
			Class<?> doClazz = getDOClassByBoObject(boObject);
			Object doObject = doClazz.newInstance();
			Class<?> boClazz = boObject.getClass();
			converAllClassProperty(boObject, boClazz, doObject, doClazz);
			return (DO) doObject;
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}

	public static void to(Object srcObject, Object descObject) {
		try {
			converAllClassProperty(srcObject, srcObject.getClass(), descObject, descObject.getClass());
		} catch (IllegalAccessException e) {
			logger.error("", e);
		}
	}

	private static <BO> void converAllClassProperty(BO srcObject, Class<?> srcClazz, Object descObject, Class<?> descClazz) throws IllegalAccessException {
		if (srcClazz.getName().equals(Object.class.getName()) || descClazz.getName().equals(Object.class.getName())) {
			return;
		}
		converAllClassProperty(srcObject, srcClazz.getSuperclass(), descObject, descClazz.getSuperclass());
		converProperty(srcObject, srcClazz.getDeclaredFields(), descObject, descClazz.getDeclaredFields());
	}

	public static <BO, VO> VO toVOByField(BO boObject) {
		if (boObject == null) {
			return null;
		}
		try {
			Class<?> voClazz = getVOClassByBoObject(boObject);
			Object voObject = voClazz.newInstance();
			Class<?> boClazz = boObject.getClass();
			converAllClassProperty(boObject, boClazz, voObject, voClazz);
			return (VO) voObject;
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}

	private static void converProperty(Object srcObject, Field[] srcFields, Object destObject, Field[] destFields) throws IllegalAccessException {
		for (Field srcField : srcFields) {
			for (Field destField : destFields) {
				if ("serialVersionUID".equalsIgnoreCase(srcField.getName())) {
					continue;
				}
				if (srcField.getName().equals(destField.getName())) {
					destField.setAccessible(true);
					srcField.setAccessible(true);
					destField.set(destObject, srcField.get(srcObject));
				}
			}
		}
	}

	public static <BO, DO> BO toBOByField(DO doObject) {
		if (doObject == null) {
			return null;
		}
		try {
			Class<?> boClazz = getBOClassByDoObject(doObject);
			Object boObject = boClazz.newInstance();
			Class<?> doClazz = doObject.getClass();
			converAllClassProperty(doObject, doClazz, boObject, boClazz);
			return (BO) boObject;
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}

	private static Class<?> getVOClassByBoObject(Object boObject) {
		try {
			if (boObject == null) {
				return null;
			}
			String boClassName = boObject.getClass().getName();
			String identify = boClassName.substring(boClassName.lastIndexOf(".") + 1, boClassName.length() - 2);
			String boPackage = boClassName.substring(0, boClassName.lastIndexOf("."));
			String doPackage = boPackage.replace(".bo.", ".vo.");
			doPackage = boPackage.replace(".bo", ".vo");
			String tranferClassName = doPackage + "." + identify + "VO";
			Class<?> clazz = Class.forName(tranferClassName);
			return clazz;
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}

	private static Class<?> getDOClassByBoObject(Object boObject) {
		try {
			if (boObject == null) {
				return null;
			}
			String boClassName = boObject.getClass().getName();
			String identify = boClassName.substring(boClassName.lastIndexOf(".") + 1, boClassName.length() - 2);
			String boPackage = boClassName.substring(0, boClassName.lastIndexOf("."));
			String doPackage = boPackage.replace(".bo.", ".dbo.");
			doPackage = doPackage.replace(".bo", ".dbo");
			String tranferClassName = doPackage + "." + identify + "DO";
			Class<?> clazz = Class.forName(tranferClassName);
			return clazz;
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}

	private static Class<?> getBOClassByDoObject(Object doObject) throws ClassNotFoundException {
		try {
			if (doObject == null) {
				return null;
			}
			String doClassName = doObject.getClass().getName();
			String identify = doClassName.substring(doClassName.lastIndexOf(".") + 1, doClassName.length() - 2);
			String doPackage = doClassName.substring(0, doClassName.lastIndexOf("."));
			String boPackage = doPackage.replace(".dbo.", ".bo.");
			boPackage = boPackage.replace(".dbo", ".bo");
			String tranferClassName = boPackage + "." + identify + "BO";
			Class<?> clazz = Class.forName(tranferClassName);
			return clazz;
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}

	public static <BO> List<BO> toBOListByField(List<?> list) {
		if (list == null || list.isEmpty()) {
			return new ArrayList<BO>();
		}
		List<Object> returnList = new ArrayList<Object>();
		for (Object dtoObject : list) {
			Object boObejct = toBOByField(dtoObject);
			returnList.add(boObejct);
		}
		return (List<BO>) returnList;
	}

	public static <T> List<T> toDOListByField(List<?> boObjectList) {
		if (boObjectList == null || boObjectList.isEmpty()) {
			return new ArrayList<T>();
		}
		List<Object> doObjectList = new ArrayList<Object>();
		for (Object boObject : boObjectList) {
			Object doObject = toDOByField(boObject);
			doObjectList.add(doObject);
		}
		return (List<T>) doObjectList;
	}

	public static <T> List<T> toVOListByField(List<?> boObjectList) {
		if (boObjectList == null || boObjectList.isEmpty()) {
			return new ArrayList<T>();
		}
		List<Object> voObjectList = new ArrayList<Object>();
		for (Object boObject : boObjectList) {
			Object voObject = toVOByField(boObject);
			voObjectList.add(voObject);
		}
		return (List<T>) voObjectList;
	}

}
