package org.smart4j.chapter4.springaop;

import org.junit.Before;
import org.junit.Test;
import org.smart4j.chapter4.common.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: gethin
 * @create: 2018-08-31 15:13
 * @description:
 **/
public class SpringAopTest {
	
	private ApplicationContext context;
	
	@Before
	public void setUp() {
		context = new ClassPathXmlApplicationContext("org/smart4j/chapter4/springaop/spring-config.xml");
	}
	
	@Test
	public void springAopTest() {
		Hello helloProxy = (Hello) context.getBean("helloProxy");
		helloProxy.say("gethin");
	}
	
	@Test
	public void enhanceClassAopTest() {
		Hello helloProxy = (Hello) context.getBean("helloProxy1");
		helloProxy.say("gethin");
		Apology apology= (Apology) helloProxy;
		apology.saySorry("gethin");
	}
	@Test
	public void advisorTest(){
		HelloImpl helloProxy= (HelloImpl) context.getBean("helloProxy2");
		//say方法不拦截
		helloProxy.say("gethin");
		//只拦截good开头的方法
		helloProxy.goodMorning("gethin");
		helloProxy.goodNight("gethin");
	}
	
	@Test
	public void autoProxyByBeanNameTest(){
		HelloAuto helloAuto= (HelloAuto) context.getBean("helloAuto");
		helloAuto.sayHello("gethin");
		LogAuto logAuto= (LogAuto) context.getBean("logAuto");
		logAuto.printLog();
	}
}
