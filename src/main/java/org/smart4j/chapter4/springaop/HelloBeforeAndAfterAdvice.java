package org.smart4j.chapter4.springaop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: gethin
 * @create: 2018-08-31 14:29
 * @description:
 **/
@Component
public class HelloBeforeAndAfterAdvice implements MethodBeforeAdvice,AfterReturningAdvice {
	
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("After");
	}
	
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("Before");
	}
}
