package com.intkr.saas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SshUtil {

	public static void doSshTunnel(String sshHost, int sshPort, String sshUser, String sshPassword, int localPort, String remoteHost, int remotePort) throws JSchException {
		final JSch jsch = new JSch();
		Session session = jsch.getSession(sshUser, sshHost, sshPort);
		session.setPassword(sshPassword);
		final Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		session.setPortForwardingL(localPort, remoteHost, remotePort);
	}

	private static void test2() throws Exception {
		String HOST = "112.74.127.47";
		int DEFAULT_SSH_PORT = 22;
		String USER = "root";
		String PASSWORD = "IntKr!@34";
		JSch jsch = new JSch();
		Session session = jsch.getSession(USER, HOST, DEFAULT_SSH_PORT);
		session.setPassword(PASSWORD);
		session.connect(30000); // making a connection with timeout.

//		{
//			Channel channel = session.openChannel("exec");
//			((ChannelExec) channel).setCommand("ls");
//		}

		{
			Channel channel = session.openChannel("shell");
			channel.setInputStream(System.in);
			channel.setOutputStream(System.out);
			channel.connect(3 * 1000);
		}
	}

	public static void main(String[] args) throws Exception {
		test2();
	}

	private static void test() {
		try {
			String sshHost = "10.123.14.55";
			int sshPort = 36000;
			String sshUser = "root";
			String sshPassword = "edu@devnet";

			int localPort = 3367;
			String remoteHost = "10.198.30.120";
			int remotePort = 4100;

			SshUtil.doSshTunnel(sshHost, sshPort, sshUser, sshPassword, localPort, remoteHost, remotePort);

			Class.forName("com.mysql.jdbc.Driver");
			String dbUser = "harisxu";
			String dbPassword = "1234";
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:" + localPort, dbUser, dbPassword);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

}