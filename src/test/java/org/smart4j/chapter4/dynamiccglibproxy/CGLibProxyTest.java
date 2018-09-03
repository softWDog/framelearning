package org.smart4j.chapter4.dynamiccglibproxy;

import org.junit.Test;
import org.smart4j.chapter4.common.Hello;
import org.smart4j.chapter4.common.HelloImpl;

/**
 * @author: gethin
 * @create: 2018-08-31 11:34
 * @description:
 **/
public class CGLibProxyTest {
	@Test
	public void testCglib() {
		Hello helloProxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
		helloProxy.say("gethin");
	}
	
}
