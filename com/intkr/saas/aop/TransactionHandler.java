package com.intkr.saas.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.intkr.saas.engine.db.MyTransaction;
import com.intkr.saas.util.claz.ClassUtil;

/**
 * 
 * @author Beiden
 */
@Aspect
@Repository("TransactionHandler")
public class TransactionHandler {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("@annotation(com.intkr.saas.aop.StartTransaction)")
	private void allMethods() {
	}

	@Before("allMethods()")
	public void begin(JoinPoint joinPoint) {
		// if (!isTransaction(joinPoint)) {
		// return;
		// }
		MyTransaction.begin();
	}

	private boolean isTransaction(JoinPoint joinPoint) {
		Class claz = joinPoint.getTarget().getClass();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		Object target = joinPoint.getTarget();
		String methodName = method.getName();
		Object[] params = joinPoint.getArgs();
		Class[] paramsClass = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			Object obj = params[i];
			paramsClass[i] = obj.getClass();
		}
		Method methodReal;
		try {
			methodReal = ClassUtil.getMethodR(claz, methodName, paramsClass);
		} catch (Exception e) {
			// logger.error("找不到切入的方法！");
			// return false;
			throw new RuntimeException("", e);
		}
		Annotation[] annotations = methodReal.getDeclaredAnnotations();
		if (annotations == null || annotations.length == 0) {
			return false;
		}
		for (Annotation a : annotations) {
			if (annotations[0].annotationType().equals(StartTransaction.class)) {
				return true;
			}
		}
		return false;
	}

	@After("allMethods()")
	public void commit(JoinPoint joinPoint) {
		// if (!isTransaction(joinPoint)) {
		// return;
		// }
		MyTransaction.commit();
	}

	@AfterThrowing("allMethods()")
	public void rollback(JoinPoint joinPoint) {
		// if (!isTransaction(joinPoint)) {
		// return;
		// }
		MyTransaction.rollback();
	}

}