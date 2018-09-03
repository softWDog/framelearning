package org.smart4j.chapter4.dynamicjdkproxy;

import org.smart4j.chapter4.common.Hello;
import org.smart4j.chapter4.common.HelloImpl;

/**
 * @author: gethin
 * @create: 2018-08-31 11:19
 * @description:
 **/
public class DynamicProxyTest {
	public static void main(String[] args) {
		DynamicProxy dynamicProxy = new DynamicProxy(new HelloImpl());
		Hello helloProxy = dynamicProxy.getProxy();
		helloProxy.say("Gethin");
	}
}
