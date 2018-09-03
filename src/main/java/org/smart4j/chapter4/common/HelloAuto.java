package org.smart4j.chapter4.common;

import org.springframework.stereotype.Component;

/**
 * @author: gethin
 * @create: 2018-09-01 15:08
 * @description:
 **/
@Component
public class HelloAuto {
	public void sayHello(String name) {
		System.out.println("Hello," + name);
	}
}
