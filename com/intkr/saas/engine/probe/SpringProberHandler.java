package com.intkr.saas.engine.probe;

import java.util.Date;

import org.aspectj.lang.JoinPoint;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2017-9-27 下午9:10:16
 * 
 */
public class SpringProberHandler {

	public void before(JoinPoint joinPoint) {
		String target = joinPoint.getTarget().getClass().getName();
		String signature = joinPoint.getSignature().toString();
		String method = target;
		method += signature.substring(signature.lastIndexOf("."));
		CallChain callChain = new CallChain();
		callChain.setMethod(method);
		callChain.setStartTime(new Date().getTime());
		callChain.setArgs(joinPoint.getArgs());
		ProbeEngine.start(callChain);
	}

	public void after() {
		ProbeEngine.end();
	}

}
