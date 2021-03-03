package com.intkr.saas.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MacUtil {

	public static void main(String[] args) {
		System.out.println(getLocalMac());
	}

	public static String getLocalMac() {
		String mac = getLocalMacWindowImpl();
		if (mac == null || "".equals(mac)) {
			mac = getLocalMacLinuxImpl();
		}
		return mac;
	}

	private static String getLocalMacWindowImpl() {
		try {
			InetAddress ia = InetAddress.getLocalHost();
			byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
			StringBuffer sb = new StringBuffer("");
			for (int i = 0; i < mac.length; i++) {
				if (i != 0) {
					sb.append("-");
				}
				int temp = mac[i] & 0xff;
				String str = Integer.toHexString(temp);
				if (str.length() == 1) {
					sb.append("0" + str);
				} else {
					sb.append(str);
				}
			}
			return sb.toString().toUpperCase();
		} catch (Exception e) {
		}
		return "";
	}

	private static String getLocalMacLinuxImpl() {
		String mac = "";
		try {
			Process p = new ProcessBuilder("ifconfig").start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				Pattern pat = Pattern.compile("\\b\\w+:\\w+:\\w+:\\w+:\\w+:\\w+\\b");
				Matcher mat = pat.matcher(line);
				if (mat.find()) {
					mac = mat.group(0);
				}
			}
			br.close();
		} catch (IOException e) {
		}
		return mac;
	}

}
