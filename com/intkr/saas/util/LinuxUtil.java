package com.intkr.saas.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-8 上午10:52:03
 * @version 1.0
 */
public class LinuxUtil {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static ExecutorService exec = Executors.newCachedThreadPool();

	public LinuxResponse executeShell(String shell) {
		try {
			String[] cmd = new String[] { "/bin/sh", "-c", shell };
			LinuxResponse response = execute(cmd);
			return response;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public LinuxResponse executeCmd(String[] cmd) {
		try {
			LinuxResponse response = execute(cmd);
			return response;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private LinuxResponse execute(String[] cmd) throws IOException, InterruptedException {
		Future<LinuxResponse> future = exec.submit(new Executor(cmd));
		try {
			return future.get(60, TimeUnit.SECONDS);
		} catch (ExecutionException e) {
			return linuxErrorResponse(cmd, e);
		} catch (TimeoutException e) {
			return linuxTimeoutResponse(cmd, e);
		}
	}

	private LinuxResponse linuxTimeoutResponse(String[] cmd, TimeoutException e) {
		return warpExceptionResponse(cmd, e, "Linux timeout Exception!");
	}

	private LinuxResponse linuxErrorResponse(String[] cmd, ExecutionException e) {
		return warpExceptionResponse(cmd, e, "Thread ExecutionException!");
	}

	private LinuxResponse warpExceptionResponse(String[] cmd, Exception e, String errorMsg) {
		LinuxResponse response = new LinuxResponse();
		response.setCmd(cmd);
		response.setExitCode(1);
		List<String> stdout = new ArrayList<String>();
		stdout.add(errorMsg);
		stdout.add(e.getMessage());
		response.setStdout(stdout);
		return response;
	}

	public static LinuxResponse warpResponse(String[] cmd) {
		LinuxResponse response = new LinuxResponse();
		response.setCmd(cmd);
		return response;
	}

	public static class Executor implements Callable<LinuxResponse> {

		protected final Logger logger = LoggerFactory.getLogger(this.getClass());

		private String[] cmd;

		public Executor(String[] cmd) {
			this.cmd = cmd;
		}

		public LinuxResponse call() throws Exception {
			LinuxResponse response = LinuxUtil.warpResponse(cmd);
			String cmdTmp = "";
			for (String str : cmd) {
				cmdTmp += str + " ";
			}
			logger.error("cmd = {}", cmdTmp);
			logger.error("linux cmd execute before time = " + dateTime(new Date()));
			Process process = Runtime.getRuntime().exec(cmd, null, null);
			logger.error("linux cmd execute after ; process.waitFor before time = " + dateTime(new Date()));
			response.setExitCode(process.waitFor());
			logger.error("process.waitFor() after ; getStdout(process) before time = " + dateTime(new Date()));
			response.setStdout(getStdout(process));
			logger.error("getStdout(process) after time = " + dateTime(new Date()));
			return response;
		}

		private List<String> getStdout(Process process) throws IOException {
			LineNumberReader input = new LineNumberReader(new InputStreamReader(process.getInputStream()));
			List<String> stdout = new ArrayList<String>();
			String line;
			while ((line = input.readLine()) != null) {
				stdout.add(line);
			}
			input.close();
			return stdout;
		}

		private String dateTime(Date date) {
			if (date == null) {
				return "";
			}
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(date);
		}
	}

	public static class LinuxResponse {

		/**
		 * 执行的linux命令
		 */
		private String[] cmd;

		/**
		 * linux返回码
		 */
		private Integer exitCode = 0;

		/**
		 * 标准输出结果
		 */
		private List<String> stdout = new ArrayList<String>();

		public String[] getCmd() {
			return cmd;
		}

		public void setCmd(String[] cmd) {
			this.cmd = cmd;
		}

		public List<String> getStdout() {
			return stdout;
		}

		public void setStdout(List<String> stdout) {
			this.stdout = stdout;
		}

		public Integer getExitCode() {
			return exitCode;
		}

		public void setExitCode(Integer exitCode) {
			this.exitCode = exitCode;
		}

		public String getCmdString() {
			String printCmd = "";
			if (cmd != null) {
				for (String subCmd : cmd) {
					printCmd += subCmd + " ";
				}
			}
			return printCmd;
		}

		public String getStdoutString() {
			String resultString = "";
			if (stdout != null) {
				for (String subResult : stdout) {
					resultString += subResult + "\n";
				}
			}
			return resultString;
		}

		public String getConsole() {
			String cmdDiv = "cmd: \n" + getCmdString() + " \n";
			String exitCodeDiv = "exitCode: " + getExitCode() + "" + " \n";
			String stdoutDiv = "stdout: \n" + getStdoutString();
			return cmdDiv + exitCodeDiv + stdoutDiv;
		}
	}
}
