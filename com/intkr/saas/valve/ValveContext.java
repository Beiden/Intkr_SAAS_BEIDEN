package com.intkr.saas.valve;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Beiden
 * @date 2018-11-21
 * @version 1.0
 */
public class ValveContext {

	public static volatile List<Valve> valves = new ArrayList<Valve>();

	static {
		// ValveContext.valves.clear();
		// valves.add(new ParamValve());
	}

}
