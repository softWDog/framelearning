package org.smart4j.chapter4.dynamicjdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: gethin
 * @create: 2018-08-31 11:09
 * @description:
 **/
public class DynamicProxy implements InvocationHandler {
	private Object target;
	
	public DynamicProxy(Object target) {
		this.target = target;
	}
	
	public <T> T getProxy() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object result = method.invoke(target, args);
		after();
		return result;
	}
	
	private void before() {
		System.out.println("Before");
	}
	
	private void after() {
		System.out.println("After");
	}
}
