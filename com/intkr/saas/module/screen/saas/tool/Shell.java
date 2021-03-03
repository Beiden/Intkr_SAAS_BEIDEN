package com.intkr.saas.module.screen.saas.tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.conf.SystemProperties;
import com.intkr.saas.util.LinuxUtil;
import com.intkr.saas.util.LinuxUtil.LinuxResponse;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-6-5 上午8:10:20
 * @version 1.0
 */
public class Shell {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LinuxUtil linuxUtil = IOC.get(LinuxUtil.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("dataPath", SystemProperties.getWebappPath());
			if (RequestUtil.existParam(request, "cmd")) {
				String cmd = RequestUtil.getParam(request, "cmd");
				String[] cmds = null;
				if (cmd.contains(" ")) {
					cmds = cmd.split(" ");
				} else {
					cmds = new String[] { cmd };
				}
//				LinuxResponse linuxResponse = linuxUtil.executeCmd(cmds);
//				request.setAttribute("linuxResponse", linuxResponse);
			} else if (RequestUtil.existParam(request, "shell")) {
				String shell = RequestUtil.getParam(request, "shell");
//				LinuxResponse linuxResponse = linuxUtil.executeShell(shell);
//				request.setAttribute("linuxResponse", linuxResponse);
			}
		} catch (Exception e) {
			logger.error("", e);
			request.setAttribute("msg", e.getMessage());
		}
	}

}
