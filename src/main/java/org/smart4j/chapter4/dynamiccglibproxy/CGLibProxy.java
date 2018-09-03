package org.smart4j.chapter4.dynamiccglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: gethin
 * @create: 2018-08-31 11:24
 * @description:
 **/
public class CGLibProxy implements MethodInterceptor {
	private static CGLibProxy instance = new CGLibProxy();
	
	private CGLibProxy() {
	}
	
	public static CGLibProxy getInstance() {
		return instance;
	}
	
	public <T> T getProxy(Class<T> cls) {
		return (T) Enhancer.create(cls, this);
	}
	
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		before();
		Object result = methodProxy.invokeSuper(o, args);
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
