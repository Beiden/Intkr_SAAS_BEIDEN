package com.intkr.saas.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.util.FileUtil;
import com.intkr.saas.util.TomcatUtil;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2014-9-23 上午09:41:57
 * @version 1.0
 */
public class SystemProperties {

	protected static final Logger logger = LoggerFactory.getLogger(SystemProperties.class);

	static String uploadPath;

	static String webappPath;

	static String assetPath;

	static String vmPath;

	static Map<String, String> properties = new HashMap<String, String>();

	static {
		Initer.init();
	}

	public static String getProperty(String property) {
		return properties.get(property);
	}

	public static String getProperty(String property, String defaultValue) {
		String value = getProperty(property);
		if (value == null || "".equals(value)) {
			value = defaultValue;
		}
		return value;
	}

	public static String getRunEnvironment() {
		return properties.get("runEnvironment");
	}

	public static boolean isProductEnvironment() {
		return "product".equals(getRunEnvironment());
	}

	public static String getUploadPath() {
		return uploadPath;
	}

	public static void setUploadPath(String uploadPath) {
		SystemProperties.uploadPath = uploadPath;
	}

	public static String getWebappPath() {
		return webappPath;
	}

	public static void setWebappPath(String webappPath) {
		SystemProperties.webappPath = webappPath;
	}

	public static String getAssetPath() {
		return assetPath;
	}

	public static void setAssetPath(String assetPath) {
		SystemProperties.assetPath = assetPath;
	}

	public static String getVmPath() {
		return vmPath;
	}

	public static void setVmPath(String vmPath) {
		SystemProperties.vmPath = vmPath;
	}

	public static void main(String[] args) {
		System.out.println("test");
	}

	public static void init() {
		Initer.init();
	}

}

/**
 * 初始化
 * 
 * @author Beiden
 * @date 2016-2-20 下午12:28:20
 * @version 1.0
 */
class Initer {

	protected static final Logger logger = LoggerFactory.getLogger(Initer.class);

	private static volatile AtomicBoolean init = new AtomicBoolean(false);

	static synchronized void init() {
		if (init.get()) {
			return;
		}
		Properties props = new Properties();
		try {
			InputStream in = getResourceAsStream();
			props.load(in);
			Enumeration<?> en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = props.getProperty(key);
				SystemProperties.properties.put(key, Property);
				Field[] fields = SystemProperties.class.getDeclaredFields();
				for (Field field : fields) {
					if (field.getName().equals(key)) {
						setFinalStatic(field, props.getProperty(key).trim());
					}
				}
			}
			in.close();
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		} finally {
			initWebPath();
		}
		init.set(true);
	}

	/**
	 * 初始化Web工程的各种目录
	 */
	private static void initWebPath() {
		if (!"${vmPath}".equals(SystemProperties.getVmPath())) {// 自定义目录
			return;
		}
		File tomcat = TomcatUtil.getTomcat();
		if (tomcat != null) {// tomcat启动方式
			File root = TomcatUtil.getROOT();
			SystemProperties.setVmPath(root.getAbsolutePath());
			SystemProperties.setUploadPath(root.getAbsolutePath());
			SystemProperties.setWebappPath(root.getAbsolutePath());
			SystemProperties.setAssetPath(root.getAbsolutePath());
			return;
		}
		// 直接代码启动方式
		File webApp = FileUtil.findDirectory(new File("getLocalBinDirectory").getAbsoluteFile().getParentFile(), "webapp", 1);
		if (webApp == null) {
			return;
		}
		SystemProperties.setVmPath(webApp.getAbsolutePath());
		SystemProperties.setUploadPath(webApp.getAbsolutePath());
		SystemProperties.setWebappPath(webApp.getAbsolutePath());
		SystemProperties.setAssetPath(webApp.getAbsolutePath());
	}

	private synchronized static InputStream getResourceAsStream() throws IOException {
		String resource = "system.properties";
		InputStream in = null;
		if (in == null) {
			in = getResourceAsStreamByFile(resource);
		}
		if (in == null) {
			in = getResourceAsStreamByClassloader(resource);
		}
		if (in == null) {
			in = getResourceAsStreamByClass(resource);
		}
		if (in == null) {
			File file = Resources.getResourceAsFile(resource);
			logger.info("read " + file);
			in = Resources.getResourceAsStream(resource);
		}
		return in;
	}

	private static InputStream getResourceAsStreamByClassloader(String resource) {
		try {
			InputStream in;
			in = SystemProperties.class.getClassLoader().getResourceAsStream(resource);
			if (in != null) {
				URL url = Initer.class.getClassLoader().getResource(resource);
				logger.info("read " + url);
			}
			return in;
		} catch (Exception e) {
		}
		return null;
	}

	private static InputStream getResourceAsStreamByFile(String resource) {
		try {
			File file = new File(resource);
			if (!file.exists()) {
				return null;
			}
			InputStream in = new FileInputStream(file);
			logger.info("read " + file);
			return in;
		} catch (Exception e) {
		}
		return null;
	}

	private static InputStream getResourceAsStreamByClass(String resource) {
		try {
			InputStream in;
			in = SystemProperties.class.getResourceAsStream(resource);
			if (in != null) {
				URL url = Initer.class.getResource(resource);
				logger.info("read " + url);
			}
			return in;
		} catch (Exception e) {
		}
		return null;
	}

	private static void setFinalStatic(Field field, Object newValue) throws Exception {
		field.setAccessible(true);
		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
		field.set(null, newValue);
	}

}