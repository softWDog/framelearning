package org.smart4j.chapter4.springaop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author: gethin
 * @create: 2018-08-31 13:16
 * @description:
 **/
public class HelloBeforeAdvice implements MethodBeforeAdvice {
	
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("Before");
	}
}
