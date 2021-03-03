package com.intkr.saas.engine.proxy;

import java.lang.reflect.Method;

import com.intkr.saas.util.claz.ClassUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2018-4-1
 * @version 1.0
 */
public class RpcBeInvocationHandler {

	public Object invoke(String clsName, String methodString, Object[] args) throws Throwable {
		Class<?> clas = Class.forName(clsName);
		Object invokerObject = IOC.get(clas);
		Method method = null;
		if (args != null) {
			Class<?>[] argsClas = new Class<?>[args.length];
			for (int i = 0; i < args.length; i++) {
				argsClas[i] = args[i].getClass();
			}
			method = ClassUtil.getMethod(clas, methodString, argsClas);
		} else {
			method = ClassUtil.getMethod(clas, methodString);
		}
		Object returnObject = method.invoke(invokerObject, args);
		return returnObject;
	}

	public Object invoke(Object object, Method method, Object[] args) throws Throwable {
		Object returnObject = method.invoke(object, args);
		return returnObject;
	}

}