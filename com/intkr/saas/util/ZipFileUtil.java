package com.intkr.saas.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 
 * @author Beiden
 * @date 2011-7-3 下午5:25:56
 * @version 1.0
 */
public class ZipFileUtil {

	public static void close(ZipFile close) {
		try {
			if (close != null) {
				close.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void close(Closeable close) {
		try {
			if (close != null) {
				close.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void compress(InputStream is, OutputStream os, String fileName) {
		ZipOutputStream zipOut = null;
		try {
			zipOut = new ZipOutputStream(os);
			zipOut.putNextEntry(new ZipEntry(fileName));
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = is.read(buffer)) != -1) {
				zipOut.write(buffer, 0, i);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(zipOut);
		}
	}

	public static void compress(InputStream is, String fileAbsoluteName, String fileName) {
		OutputStream os = null;
		try {
			File file = new File(fileAbsoluteName);
			os = new FileOutputStream(file);
			compress(is, os, fileName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(os);
		}
	}

	public static void compress(String fileAbsoluteName, OutputStream os) {
		InputStream is = null;
		try {
			File file = new File(fileAbsoluteName);
			is = new FileInputStream(file);
			String fileName = file.getName();
			compress(is, os, fileName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(is);
		}
	}

	public static void compress(String fileAbsoluteName, String zipPath) {
		InputStream is = null;
		OutputStream os = null;
		try {
			File file = new File(fileAbsoluteName);
			is = new FileInputStream(file);
			String fileName = file.getName();
			os = new FileOutputStream(new File(zipPath));
			compress(is, os, fileName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(is);
			close(os);
		}
	}

	/**
	 * 一次性压缩多个文件，文件存放至一个文件夹中
	 * 
	 * @param directoryPath
	 * @param zipPath
	 */
	public static void compressDirectory(String directoryPath, String zipPath) {
		InputStream input = null;
		ZipOutputStream zipOut = null;
		try {
			File file = new File(directoryPath);
			File zipFile = new File(zipPath);
			zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; ++i) {
					input = new FileInputStream(files[i]);
					zipOut.putNextEntry(new ZipEntry(file.getName() + File.separator + files[i].getName()));
					byte[] buffer = new byte[1024];
					int k = -1;
					while ((k = input.read(buffer)) != -1) {
						zipOut.write(buffer, 0, k);
					}
					input.close();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(zipOut);
			close(input);
		}
	}

	/**
	 * 一次性压缩多个文件，文件存放至一个文件夹中
	 * 
	 * @param directoryPath
	 * @param zipPath
	 */
	public static void compress(List<String> filePathList, String zipPath) {
		InputStream input = null;
		ZipOutputStream zipOut = null;
		try {
			zipOut = new ZipOutputStream(new FileOutputStream(new File(zipPath)));
			for (String filePath : filePathList) {
				File file = new File(filePath);
				input = new FileInputStream(file);
				zipOut.putNextEntry(new ZipEntry(file.getName()));
				byte[] buffer = new byte[1024];
				int i = -1;
				while ((i = input.read(buffer)) != -1) {
					zipOut.write(buffer, 0, i);
				}
				input.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(zipOut);
			close(input);
		}
	}

	/**
	 * 一次性压缩多个文件，文件存放至一个文件夹中
	 * 
	 * @param directoryPath
	 * @param zipPath
	 */
	public static void compress(List<String> filePathList, OutputStream outputStream) {
		InputStream input = null;
		ZipOutputStream zipOut = null;
		try {
			zipOut = new ZipOutputStream(outputStream);
			for (String filePath : filePathList) {
				File file = new File(filePath);
				input = new FileInputStream(file);
				zipOut.putNextEntry(new ZipEntry(file.getName()));
				byte[] buffer = new byte[1024];
				int i = -1;
				while ((i = input.read(buffer)) != -1) {
					zipOut.write(buffer, 0, i);
				}
				input.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(zipOut);
			close(input);
		}
	}

	/**
	 * 解压缩（解压缩单个文件）
	 * 
	 * @param zipPath
	 * @param outFileName
	 * @param fileName
	 */
	public static void decompress(String zipPath, String fileName, String outFileName) {
		InputStream input = null;
		OutputStream output = null;
		ZipFile zipFile = null;
		try {
			File file = new File(zipPath);// 压缩文件路径和文件名
			File outFile = new File(outFileName);// 解压后路径和文件名
			zipFile = new ZipFile(file);
			ZipEntry entry = zipFile.getEntry(fileName);// 所解压的文件名
			input = zipFile.getInputStream(entry);
			output = new FileOutputStream(outFile);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = input.read(buffer)) != -1) {
				output.write(buffer, 0, i);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(output);
			close(input);
			close(zipFile);
		}
	}

	public static void decompress(String zipPath, String fileName, OutputStream output) {
		InputStream input = null;
		ZipFile zipFile = null;
		try {
			File file = new File(zipPath);// 压缩文件路径和文件名
			zipFile = new ZipFile(file);
			ZipEntry entry = getEntry(fileName, zipFile);
			input = zipFile.getInputStream(entry);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = input.read(buffer)) != -1) {
				output.write(buffer, 0, i);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(input);
			close(zipFile);
		}
	}

	/**
	 * 文件名大小写的问题
	 * 
	 * @param fileName
	 * @param zipFile
	 * @return
	 */
	private static ZipEntry getEntry(String fileName, ZipFile zipFile) {
		ZipEntry entry = zipFile.getEntry(fileName);// 所解压的文件名
		if (entry != null) {
			return entry;
		}
		for (Enumeration<? extends ZipEntry> e = zipFile.entries(); e.hasMoreElements();) {
			entry = e.nextElement();
			if (fileName.equalsIgnoreCase(entry.getName())) {
				return entry;
			}
		}
		return entry;
	}

	/**
	 * 解压缩（压缩文件中包含多个文件）可代替上面的方法使用。 ZipInputStream类
	 * 当我们需要解压缩多个文件的时候，ZipEntry就无法使用了， 如果想操作更加复杂的压缩文件，我们就必须使用ZipInputStream类
	 * 
	 * @param zipPath
	 * @param outZipPath
	 */
	public static void decompress(String zipPath, String outZipPath) {
		ZipFile zipFile = null;
		ZipInputStream zipInput = null;
		try {
			File file = new File(zipPath);
			File outFile = null;
			zipFile = new ZipFile(file);
			zipInput = new ZipInputStream(new FileInputStream(file));
			ZipEntry entry = null;
			InputStream input = null;
			OutputStream output = null;
			while ((entry = zipInput.getNextEntry()) != null) {
				System.out.println("解压缩" + entry.getName() + "文件");
				outFile = new File(outZipPath + File.separator + entry.getName());
				if (!outFile.getParentFile().exists()) {
					outFile.getParentFile().mkdir();
				}
				if (!outFile.exists() && !entry.isDirectory()) {
					outFile.createNewFile();
				} else if (!outFile.exists() && entry.isDirectory()) {
					outFile.mkdir();
					continue;
				}
				input = zipFile.getInputStream(entry);
				output = new FileOutputStream(outFile);
				byte[] buffer = new byte[1024];
				int i = -1;
				while ((i = input.read(buffer)) != -1) {
					output.write(buffer, 0, i);
				}
				input.close();
				output.close();
			}
			zipInput.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(zipInput);
			close(zipFile);
		}
	}

	public static void main(String[] args) {
		compress("G:/test.stl", "G:/test.zip");
	}

}
