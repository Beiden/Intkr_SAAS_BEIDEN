package com.intkr.saas.util.vm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.conf.SystemProperties;

/**
 * 
 * @author Beiden
 * @date 2011-9-30 上午11:45:24
 * @version 1.0
 */
public class VmFileUtil {

	protected static final Logger logger = LoggerFactory.getLogger(VmFileUtil.class);

	/**
	 * 是否存在VM文件
	 * 
	 * @param vmFile
	 * @return
	 */
	public static boolean isExistVM(String vmFile) {
		if (vmFile == null) {
			return false;
		}
		if (vmFile.startsWith("/")) {
			vmFile = vmFile.substring(1);
		}
		File file = new File(SystemProperties.getVmPath() + "/IK/templates/" + vmFile);
		return file.exists();
	}

	/**
	 * 获得VM文件的内容
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getVmContent(String fileName) {
		if (!VmFileUtil.isExistVM(fileName)) {
			return "";
		}
		try {
			FileReader reader;
			reader = new FileReader(SystemProperties.getVmPath() + "/" + fileName);
			BufferedReader br = new BufferedReader(reader);
			StringBuffer sb = new StringBuffer();
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append(str + "/n");
			}
			br.close();
			reader.close();
			return sb.toString();
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param uri
	 * @param config
	 * @return
	 */
	public static String getVmFile(String uri) {
		return uri.replace(".html", ".vm");
	}

}