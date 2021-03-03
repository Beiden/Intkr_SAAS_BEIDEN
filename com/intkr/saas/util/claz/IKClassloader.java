package com.intkr.saas.util.claz;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.conf.SystemProperties;
import com.intkr.saas.util.FileUtil;

public class IKClassloader extends ClassLoader {

	private Map<String, Class> loadedClassPool = new HashMap<String, Class>();

	private ClassLoader parent = null;

	public IKClassloader(ClassLoader parent) {
		this.parent = parent;
	}

	@Override
	public synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		Class claz = null;
		if (loadedClassPool.containsKey(name)) {
			claz = this.loadedClassPool.get(name);
		} else {
			try {
				if (claz == null) {
					claz = parent.loadClass(name);
				}
			} catch (ClassNotFoundException e) {
			}
			if (claz == null) {
				claz = loadByCustomClassLoader(name);
			}
			if (claz != null) {
				this.loadedClassPool.put(name, claz);
			}
		}
		if (resolve) {
			resolveClass(claz);
		}
		return claz;
	}

	private Class loadByCustomClassLoader(String name) {
		Class claz = null;
		try {
			byte[] rawData = null;
			try {
				rawData = loadClassBytes(name);
			} catch (Exception e1) {
			}
			if (rawData == null) {
				try {
					rawData = loadClassBytes2(name);
				} catch (Exception e1) {
				}
			}
			if (rawData == null) {
				try {
					rawData = loadClassBytes3(name);
				} catch (Exception e1) {
				}
			}
			if (rawData == null) {
				try {
					rawData = loadClassBytes4(name);
				} catch (Exception e1) {
				}
			}
			if (claz == null) {
				try {
					byte[] classData = decrypt(rawData);
					claz = defineClass(name, classData, 0, classData.length);
				} catch (ClassFormatError e) {
				}
			}
			if (claz == null) {
				try {
					byte[] classData = decrypt1(rawData);
					claz = defineClass(name, classData, 0, classData.length);
				} catch (ClassFormatError e) {
				}
			}
			if (claz == null) {
				try {
					byte[] classData = decrypt2(rawData);
					claz = defineClass(name, classData, 0, classData.length);
				} catch (ClassFormatError e) {
				}
			}
			if (claz == null) {
				try {
					byte[] classData = decrypt3(rawData);
					claz = defineClass(name, classData, 0, classData.length);
				} catch (ClassFormatError e) {
				}
			}
		} catch (Exception e) {
			claz = null;
		}
		return claz;
	}

	private byte[] loadClassBytes4(String name) throws IOException {
		try {
			name = name.substring(name.lastIndexOf(".") + 1);
			File file = FileUtil.find(new File(SystemProperties.getVmPath()), "AA" + name, 1);
			InputStream is = new FileInputStream(file);
			byte[] rawData = new byte[is.available()];
			is.read(rawData);
			is.close();
			return rawData;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private byte[] loadClassBytes3(String name) throws IOException {
		try {
			name = name.substring(name.lastIndexOf(".") + 1);
			File file = FileUtil.find(new File(SystemProperties.getVmPath()), name, 1);
			InputStream is = new FileInputStream(file);
			byte[] rawData = new byte[is.available()];
			is.read(rawData);
			is.close();
			return rawData;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private byte[] loadClassBytes2(String name) throws IOException {
		try {
			String baseDir = SystemProperties.getVmPath();
			name = name.substring(name.lastIndexOf(".") + 1);
			name = baseDir + File.separator + "asset" + File.separator + "lib" + File.separator + "IntKrAdmin" + File.separator + "class" + File.separator + name;

			InputStream is = new FileInputStream(new File(name));
			byte[] rawData = new byte[is.available()];
			is.read(rawData);
			is.close();
			return rawData;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private byte[] loadClassBytes(String name) throws IOException {
		try {
			InputStream is = this.getParent().getResourceAsStream(name);
			byte[] rawData = new byte[is.available()];
			is.read(rawData);
			is.close();
			return rawData;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private byte[] decrypt(byte[] rawData) {
		return rawData;
	}

	private byte[] decrypt1(byte[] rawData) {
		for (int i = 0; i < rawData.length; i++) {
			rawData[i] = (byte) (rawData[i] - 1);
		}
		return rawData;
	}

	private byte[] decrypt2(byte[] rawData) {
		return rawData;
	}

	private byte[] decrypt3(byte[] rawData) {
		return rawData;
	}

	public void encrypt1() {
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("d:/workbench/ciphertool/bin/com/aatest/Hello.class")));
			byte[] data = new byte[bis.available()];
			bis.read(data);
			bis.close();
			for (int i = 0; i < data.length; i++) {
				data[i] = (byte) (data[i] + 1);
			}
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("d:/workbench/ciphertool/bin/com/aatest/Hello.class"));
			bos.write(data);
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void encrypt2() {
		try {

			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("d:/workbench/ciphertool/bin/com/aatest/Hello.class")));
			byte[] data = new byte[bis.available()];
			bis.read(data);
			bis.close();
			for (int i = 0; i < data.length; i++) {
				data[i] = (byte) (data[i] + 1);
			}
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("d:/workbench/ciphertool/bin/com/aatest/Hello.class"));
			bos.write(data);
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
