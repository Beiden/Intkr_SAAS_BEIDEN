package com.intkr.saas.valve;

import com.intkr.saas.valve.auth.AuthorityValve;
import com.intkr.saas.valve.auth.login.LoginByCookieValve;
import com.intkr.saas.valve.auth.login.LoginBySkeyValve;
import com.intkr.saas.valve.auth.login.LoginByTokenValve;
import com.intkr.saas.valve.auth.login.LoginByUserIdValve;
import com.intkr.saas.valve.auth.login.LoginByWxCodeValve;
import com.intkr.saas.valve.util.ParamValve;

/**
 * 
 * @author Beiden
 * @date 2011-11-4 上午11:40:06
 * @version 1.0
 */
public class ValveEngineSaasImpl extends ValveEngine {

	public ValveEngineSaasImpl() {
		ValveContext.valves.clear();
		ValveContext.valves.add(new ParamValve());
		ValveContext.valves.add(new SaasValve());
		ValveContext.valves.add(new LoginBySkeyValve());
		ValveContext.valves.add(new LoginByUserIdValve());
		ValveContext.valves.add(new LoginByCookieValve());
		ValveContext.valves.add(new LoginByTokenValve());
		ValveContext.valves.add(new LoginByWxCodeValve());
		ValveContext.valves.add(new AuthorityValve());
	}

}
