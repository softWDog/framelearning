package org.smart4j.chapter4.springaop;

import org.junit.Test;
import org.smart4j.chapter4.common.Hello;
import org.smart4j.chapter4.common.HelloImpl;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @author: gethin
 * @create: 2018-08-31 14:36
 * @description:
 **/
public class AopTest {
	ProxyFactory proxyFactory=new ProxyFactory();
	@Test
	public void beforeAndAfterTest(){
		proxyFactory.setTarget(new HelloImpl());
		proxyFactory.addAdvice(new HelloBeforeAdvice());
		proxyFactory.addAdvice(new HelloAfterAdvice());
		Hello hello= (Hello) proxyFactory.getProxy();
		hello.say("gethin");
	}
	
	@Test
	public void aroundTest(){
		proxyFactory.setTarget(new HelloImpl());
		proxyFactory.addAdvice(new HelloBeforeAndAfterAdvice());
		Hello hello= (Hello) proxyFactory.getProxy();
		hello.say("sinan");
	}
	
}
