package org.smart4j.chapter4.springaop;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * @author: gethin
 * @create: 2018-08-31 16:09
 * @description:
 **/
public class HelloThrowAdvice implements ThrowsAdvice {
	public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
		System.out.println("------Throw Exception------");
		System.out.println("Target Class: " + target.getClass().getName());
		System.out.println("Method name: " + method.getName());
		System.out.println("Exception Message :" + e.getMessage());
		System.out.println("---------------------------");
	}
}
