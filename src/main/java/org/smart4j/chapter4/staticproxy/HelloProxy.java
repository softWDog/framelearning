package org.smart4j.chapter4.staticproxy;

import org.smart4j.chapter4.common.Hello;
import org.smart4j.chapter4.common.HelloImpl;

/**
 * @author: gethin
 * @create: 2018-08-31 10:29
 * @description:
 **/
public class HelloProxy implements Hello {
	private Hello hello;
	
	public HelloProxy() {
		hello = new HelloImpl();
	}
	
	public void say(String name) {
		before();
		hello.say(name);
		after();
	}
	
	private void before() {
		System.out.println("Before");
	}
	
	private void after() {
		System.out.println("After");
	}
	
}
