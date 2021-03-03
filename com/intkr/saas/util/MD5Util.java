package com.intkr.saas.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Random;

/**
 * 
 * @author Beiden
 * @date 2011-5-20 上午10:41:54
 * @version 1.0
 */
public class MD5Util {

	private static Random random = new Random();

	public static void main(String[] args) throws Exception {
		// System.out.println(getRandomAdd());
		String id = "1357";
		System.out.println(toMD5(id.getBytes()));
	}

	public static boolean verify(String srcPassword, String add, String destPassword) {
		return encrypt(srcPassword, add).equals(destPassword);
	}

	public static String getRandomAdd() {
		String add = "";
		for (int i = 0; i < 6; i++) {
			add += random.nextInt(10);
		}
		return add;
	}

	public static String encrypt(String password, String add) {
		try {
			return toMD5((toMD5((password).getBytes("GBK")) + add).getBytes("GBK"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String toMD5(byte[] source) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source);
			StringBuffer buf = new StringBuffer();
			for (byte b : md.digest()) {
				buf.append(String.format("%02x", b & 0xff));
			}
			return buf.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
