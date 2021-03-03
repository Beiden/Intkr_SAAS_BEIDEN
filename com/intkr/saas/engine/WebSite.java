package com.intkr.saas.engine;


import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.conf.impl.OptionManagerImpl;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-28 下午2:04:03
 * @version 1.0
 */
public class WebSite {

	public static OptionManager optionManager = IOC.get("OptionManager");

}
