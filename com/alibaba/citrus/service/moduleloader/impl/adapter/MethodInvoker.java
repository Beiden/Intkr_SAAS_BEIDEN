/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.citrus.service.moduleloader.impl.adapter;

import static com.alibaba.citrus.util.Assert.assertNotNull;
import static com.alibaba.citrus.util.ClassUtil.getPrimitiveDefaultValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import net.sf.cglib.reflect.FastMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.citrus.service.dataresolver.DataResolver;
import com.alibaba.citrus.service.moduleloader.SkipModuleExecutionException;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.intkr.saas.aop.ParamCheck;
import com.intkr.saas.aop.ParamChecker;
import com.intkr.saas.aop.StartTransaction;
import com.intkr.saas.engine.db.MyTransaction;

class MethodInvoker {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final FastMethod fastMethod;
	private final DataResolver[] resolvers;
	private final boolean skippable;

	public MethodInvoker(FastMethod fastMethod, DataResolver[] resolvers, boolean skippable) {
		this.fastMethod = assertNotNull(fastMethod, "fastMethod");
		this.resolvers = resolvers == null ? new DataResolver[0] : resolvers;
		this.skippable = skippable;
	}

	public Object invoke(Object moduleObject, Logger log) throws Exception {
		Object[] args = new Object[resolvers.length];

		for (int i = 0; i < args.length; i++) {
			Object value;

			try {
				value = resolvers[i].resolve();
			} catch (SkipModuleExecutionException e) {
				if (skippable) {
					log.debug("Module execution has been skipped. Method: {}, {}", fastMethod, e.getMessage());
					return null;
				}

				value = e.getValueForNonSkippable();
			}

			// 特别处理：防止对primitive类型设置null
			Class<?> paramType = fastMethod.getJavaMethod().getParameterTypes()[i];

			if (value == null && paramType.isPrimitive()) {
				value = getPrimitiveDefaultValue(paramType);
			}

			args[i] = value;
		}

		try {
			System.out.println("调用代码：" + fastMethod);
			if (hasAnnotation(ParamCheck.class)) {
				Method method = fastMethod.getJavaMethod();
				ParamCheck check = method.getAnnotation(ParamCheck.class);
				String jsonSchema = check.schema();
				boolean result = ParamChecker.check((ServletRequest) args[0], jsonSchema);
				if (!result) {
					return null;
				}
			}
			return process(moduleObject, args);
		} catch (InvocationTargetException e) {
			HttpServletRequest request = (HttpServletRequest) args[0];
			TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
			String target = runData.getTarget();
//			if (target != null && target.endsWith(".json")) {
				logger.error("params=" + request.getParameterMap(), e);
				request.setAttribute("result", false);
				request.setAttribute("msg", "系统异常，请稍后再试！");
//			} else {
//				throwExceptionOrError(e.getCause());
//			}
			return null;
		}
	}

	private Object process(Object moduleObject, Object[] args) throws InvocationTargetException {
		if (hasTransactionAnnotation()) {
			return processTransaction(moduleObject, args);
		} else {
			return fastMethod.invoke(moduleObject, args);
		}
	}

	private Object processTransaction(Object moduleObject, Object[] args) throws InvocationTargetException {
		try {
			MyTransaction.begin();
			Object object = fastMethod.invoke(moduleObject, args);
			MyTransaction.commit();
			return object;
		} catch (Throwable e) {
			MyTransaction.rollback();
			throw new InvocationTargetException(e.getCause());
		}
	}

	public boolean isAction() {
		return fastMethod.getJavaMethod().getDeclaringClass().getName().endsWith("Action");
	}

	private boolean hasTransactionAnnotation() {
		return hasAnnotation(StartTransaction.class);
	}

	private boolean hasAnnotation(Class claz) {
		Method method = fastMethod.getJavaMethod();
		return method.isAnnotationPresent(claz);
	}

	@Override
	public String toString() {
		return fastMethod.toString();
	}
}
