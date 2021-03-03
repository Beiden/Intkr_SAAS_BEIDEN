package com.intkr.saas.util.claz;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Beiden
 * @date 2011-11-4 上午10:11:56
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class ClassUtil {

	private static Map<String, Class<?>> classPool = new ConcurrentHashMap<String, Class<?>>();

	private static Map<String, Method> methodPool = new ConcurrentHashMap<String, Method>();

	private static IKClassloader ikClassLoader = new IKClassloader(ClassUtil.class.getClassLoader());

	public static <T> Class<T> getClass(String clazName) throws ClassNotFoundException {
		if (clazName == null) {
			return null;
		}
		if (classPool.containsKey(clazName)) {
			return (Class<T>) classPool.get(clazName);
		} else {
			clazName = clazName.trim();
			Class<?> claz = null;
			if (clazName.startsWith("com.intkr.saas.") || clazName.startsWith("me.beiden.")) {
				claz = Class.forName(clazName, true, ikClassLoader);
			} else {
				claz = Class.forName(clazName);
			}
			classPool.put(clazName, claz);
			return (Class<T>) claz;
		}
	}

	/**
	 * 是否存在类
	 * 
	 * @param clazName
	 * @return
	 */
	public static boolean hasClass(String clazName) {
		if (clazName == null) {
			return false;
		}
		try {
			getClass(clazName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 类是否存在方法
	 * 
	 * @param claz
	 * @param name
	 * @param parameterTypes
	 * @return
	 */
	public static boolean hasMethod(Class<?> claz, String name, Class<?>... parameterTypes) {
		if (claz == null || name == null) {
			return false;
		}
		try {
			getMethod(claz, name, parameterTypes);
			return true;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}

	public static Method getMethod(Class<?> claz, String name, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {
		String methodKey = getMethodKey(claz, name, parameterTypes);
		if (methodPool.containsKey(methodKey)) {
			Method method = methodPool.get(methodKey);
			if (method != null) {
				return method;
			} else {
				throw new NoSuchMethodException(claz.getName() + "." + name + "()");
			}
		} else {
			Method method = claz.getMethod(name, parameterTypes);
			methodPool.put(methodKey, method);
			return method;
		}
	}

	public static Method getMethodR(Class<?> claz, String name, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {
		String methodKey = getMethodKey(claz, name, parameterTypes);
		if (methodPool.containsKey(methodKey)) {
			Method method = methodPool.get(methodKey);
			if (method != null) {
				return method;
			} else {
				throw new NoSuchMethodException(claz.getName() + "." + name + "()");
			}
		} else {
			Method method = MethodUtil.getMethodRF(claz, name, parameterTypes);
			methodPool.put(methodKey, method);
			return method;
		}
	}

	public static String getMethodKey(Class<?> claz, String name, Class<?>... parameterTypes) {
		String methodKey = claz.getName() + "." + name;
		if (parameterTypes != null && parameterTypes.length > 0) {
			methodKey += "(";
			for (Class<?> clazTmp : parameterTypes) {
				methodKey += clazTmp.getName() + ",";
			}
			methodKey = methodKey.substring(0, methodKey.length() - 1);
			methodKey += ")";
		}
		return methodKey;
	}

	public static Object invokeMethod(String className, String methodString, HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Class<?> claz = ClassUtil.getClass(className);
		Object object = IOC.get(claz);
		Method method = ClassUtil.getMethod(claz, methodString, HttpServletRequest.class, HttpServletResponse.class);
		return method.invoke(object, request, response);
	}

	public static Object invokeMethod(String className, String methodString, Object... objs) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Class<?> claz = ClassUtil.getClass(className);
		Object object = IOC.get(claz);
		Class<?>[] classs = new Class[objs.length];
		for (int i = 0; i < objs.length; i++) {
			Class<?> clazz = objs[i].getClass();
			if (javax.servlet.http.HttpServletRequest.class.isAssignableFrom(clazz)) {
				classs[i] = javax.servlet.http.HttpServletRequest.class;
			} else if (javax.servlet.http.HttpServletResponse.class.isAssignableFrom(clazz)) {
				classs[i] = javax.servlet.http.HttpServletResponse.class;
			} else if (javax.servlet.http.HttpSession.class.isAssignableFrom(clazz)) {
				classs[i] = javax.servlet.http.HttpSession.class;
			} else if (com.alibaba.citrus.turbine.Context.class.isAssignableFrom(clazz)) {
				classs[i] = com.alibaba.citrus.turbine.Context.class;
			} else if (Map.class.isAssignableFrom(clazz)) {
				classs[i] = Map.class;
			} else {
				classs[i] = objs[i].getClass();
			}
		}
		Method method = ClassUtil.getMethod(claz, methodString, classs);
		return method.invoke(object, objs);
	}

	/**
	 * 取得某个接口下所有实现这个接口的类
	 * */
	public static List<Class> getAllClassByInterface(Class c) {
		List<Class> returnClassList = null;
		if (c.isInterface()) {
			// 获取当前的包名
			String packageName = c.getPackage().getName();
			// 获取当前包下以及子包下所以的类
			List<Class<?>> allClass = getClasses(packageName);
			if (allClass != null) {
				returnClassList = new ArrayList<Class>();
				for (Class classes : allClass) {
					// 判断是否是同一个接口
					if (c.isAssignableFrom(classes)) {
						// 本身不加入进去
						if (!c.equals(classes)) {
							returnClassList.add(classes);
						}
					}
				}
			}
		}
		return returnClassList;
	}

	/*
	 * 取得某一类所在包的所有类名 不含迭代
	 */
	public static String[] getPackageAllClassName(String classLocation, String packageName) {
		// 将packageName分解
		String[] packagePathSplit = packageName.split("[.]");
		String realClassLocation = classLocation;
		int packageLength = packagePathSplit.length;
		for (int i = 0; i < packageLength; i++) {
			realClassLocation = realClassLocation + File.separator + packagePathSplit[i];
		}
		File packeageDir = new File(realClassLocation);
		if (packeageDir.isDirectory()) {
			String[] allClassName = packeageDir.list();
			return allClassName;
		}
		return null;
	}

	/**
	 * 从包package中获取所有的Class
	 * 
	 * @param pack
	 * @return
	 */
	public static List<Class<?>> getClasses(String packageName) {
		// 第一个class类的集合
		List<Class<?>> classes = new ArrayList<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件
					// 定义一个JarFile
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection()).getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果是以/开头的
							if (name.charAt(0) == '/') {
								// 获取后面的字符串
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx).replace('/', '.');
								}
								// 如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// 如果是一个.class文件 而且不是目录
									if (name.endsWith(".class") && !entry.isDirectory()) {
										// 去掉后面的".class" 获取真正的类名
										String className = name.substring(packageName.length() + 1, name.length() - 6);
										try {
											// 添加到classes
											classes.add(Class.forName(packageName + '.' + className));
										} catch (ClassNotFoundException e) {
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {
					// 添加到集合中去
					classes.add(Class.forName(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static <T> T getFieldValue(Object obj, String fieldName) {
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.getName().equals(fieldName)) {
					field.setAccessible(true);
					T value = (T) field.get(obj);
					return value;
				}
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws Exception {
		getMethod(ClassUtil.class, "findAndAddClassesInPackageByFile", String.class, String.class, boolean.class, List.class);
	}

}
